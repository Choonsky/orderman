package com.choonsky.orderman.controller;

import com.choonsky.orderman.model.*;
import com.choonsky.orderman.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ModifyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private StateRepository stateRepository;

    @RequestMapping(value = {"/modify", "/modify/{id}"}, method = RequestMethod.POST)
    public String modifyOrder(@PathVariable(value = "id") Optional<Integer> id,
                              @RequestParam(value = "productName", required = false) List<String> productNames,
                              @RequestParam(value = "productUoc", required = false) List<String> productUocs,
                              @RequestParam(value = "productAmount", required = false) List<String> productAmounts,
                              @RequestParam(value = "state") Optional<String> toState,
                              @RequestParam(value = "messageContent") Optional<String> messageContent,
                              HttpServletRequest request) {

        User user = userRepository.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Order order;
        String stateName;
        if(toState.isPresent()) stateName = toState.get();
            else stateName = "CREATED";

        if (id.isPresent()) {
            // Existing order modifying
            System.out.println("\n Request: id = " + id.get() + ", productName = " + productNames + ", toState = " + toState.get() + ".\n");
            order = orderRepository.getOne(id.get());
            switch(stateName) {
                case("SAVED") :
                case("SENT") :
                    ArrayList<OrderLine> orderLines = new ArrayList<>(order.getOrderLines());
                    orderLineRepository.deleteInBatch(orderLines);
                case("APPROVED") :
                case("EXECUTING") :
                case("FINISHED") :
                    State state = stateRepository.findByStateName(stateName).get();
                    saveAction(LocalDateTime.now(), order, user, state, "");
                    order.setState(state);
                    break;
            }
        } else {
            // New order
            State state = stateRepository.findByStateName(stateName).get();
            order = new Order(user, state);
            order = orderRepository.save(order);
            saveAction(LocalDateTime.now(), order, user, state, "");
            stateName = "SAVED";
            state = stateRepository.findByStateName(stateName).get();
            order.setState(state);
            saveAction(LocalDateTime.now(), order, user, state, "");
        }
        order = orderRepository.save(order);

        if (stateName.equals("SAVED") || stateName.equals("SENT"))
            saveOrderLines(order, productNames, productUocs, productAmounts);

        if (messageContent.isPresent() && !messageContent.get().isEmpty() && !messageContent.get().equals(""))
            saveAction(LocalDateTime.now(), order, user, null, messageContent.get());

        if (request.isUserInRole("ADMIN")) return "redirect:/admin";
        else if (request.isUserInRole("CHIEF")) return "redirect:/chief";
        else if (request.isUserInRole("SUPPLIER")) return "redirect:/supplier";
        else return "redirect:/user";
    }

    private void saveOrderLines(Order order, List<String> productNames, List<String> productUocs, List<String> productAmounts) {
        for (int i = 0; i < productNames.size(); i++) {
            Optional<Product> product = productRepository.findByProductName(productNames.get(i));
            if (!product.isPresent()) {
                Product newProduct = new Product(productNames.get(i), productUocs.get(i), "default");
                productRepository.save(newProduct);
            }
            Product newProduct = productRepository.findByProductName(productNames.get(i)).get();
            OrderLine newOrderLine = new OrderLine(order, newProduct, new BigDecimal(productAmounts.get(i)), productUocs.get(i));
            orderLineRepository.save(newOrderLine);
        }
    }

    private Action saveAction(LocalDateTime time, Order order, User user, State state, String msg) {
        Action act = new Action();
        act.setTime(time);
        act.setOrder(order);
        act.setUser(user);
        System.out.println("\n Saving Action: " + act + "\n");
        if (state == null) {
            Message message = new Message();
            message.setContent(msg);
            message = messageRepository.save(message);
            act.setMessage(message);
        } else {
            act.setState(state);
        }
        return actionRepository.save(act);
    }

}
