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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    @RequestMapping(value = {"/modify/{id}", "/modify/{id}/", "/modify" }, method = RequestMethod.POST)
    public String modifyOrder(@PathVariable("id") Optional<Integer> id, @RequestParam("productName") List<String> productNames,
                             @RequestParam("productUoc") List<String> productUocs,
                             @RequestParam("productAmount") List<String> productAmounts,
                             @RequestParam("toSend") Optional<Boolean> toSend,
                             @RequestParam("messageContent") Optional<String> messageContent) {

        User user = userRepository.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Order order;

        if (id.isPresent()) {
            order = orderRepository.getOne(id.get());
            ArrayList<OrderLine> orderLines = new ArrayList<>(order.getOrderLines());
            System.out.println("\nIt is orderlines list : " + orderLines +"\n");
            orderLineRepository.deleteInBatch(orderLines);
            System.out.println("\nIt is orderlines list now : " + order.getOrderLines() +"\n");

        } else {
            State state = stateRepository.getOne(1);
            order = new Order(user, state);
        }
        order = orderRepository.save(order);

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

        saveAction(LocalDateTime.now(), order, user, stateRepository.getOne(2), "");
        if (toSend.isPresent()) saveAction(LocalDateTime.now(), order, user, stateRepository.getOne(3), null);
        if (messageContent.isPresent() && !messageContent.get().isEmpty() && !messageContent.get().equals(""))
            saveAction(LocalDateTime.now(), order, user, null, messageContent.get());

        return "redirect:/admin";
    }

    private void saveAction(LocalDateTime time, Order order, User user, State state, String msg) {
        Action act = new Action();
        act.setTime(time);
        act.setOrder(order);
        act.setUser(user);
        if (state == null) {
            Message message = new Message();
            message.setContent(msg);
            message = messageRepository.save(message);
            act.setMessage(message);
        } else {
            act.setState(state);
        }
        act = actionRepository.save(act);
    }

}
