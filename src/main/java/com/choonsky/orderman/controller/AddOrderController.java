package com.choonsky.orderman.controller;

import com.choonsky.orderman.model.*;
import com.choonsky.orderman.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class AddOrderController {

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

    @RequestMapping("/admin-add-order")
    public String someMethod(@RequestParam("productName") List<String> productNames,
                             @RequestParam("productUoc") List<String> productUocs,
                             @RequestParam("productAmount") List<String> productAmounts,
                             @RequestParam("toSend") Optional<Boolean> toSend,
                             @RequestParam("messageContent") Optional<String> messageContent) {

        User user = userRepository.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        State state = stateRepository.getOne(1);

        Order newOrder = new Order(user, state);
        newOrder = orderRepository.save(newOrder);

        for (int i = 0; i < productNames.size(); i++) {
            Optional<Product> product = productRepository.findByProductName(productNames.get(i));
            if (!product.isPresent()) {
                Product newProduct = new Product(productNames.get(i), productUocs.get(i), "default");
                newProduct = productRepository.save(newProduct);
            }
            Product newProduct = productRepository.findByProductName(productNames.get(i)).get();
            OrderLine newOrderLine = new OrderLine(newOrder, newProduct, new BigDecimal(productAmounts.get(i)), productUocs.get(i));
            newOrderLine = orderLineRepository.save(newOrderLine);
        }

        saveAction(LocalDateTime.now(), newOrder, user, stateRepository.getOne(2), "");
        if (toSend.isPresent()) saveAction(LocalDateTime.now(), newOrder, user, stateRepository.getOne(3), null);
        if (messageContent.isPresent() && !messageContent.get().isEmpty() && !messageContent.get().equals(""))
            saveAction(LocalDateTime.now(), newOrder, user, null, messageContent.get());

        return "redirect:/admin";
    }

    void saveAction(LocalDateTime time, Order order, User user, State state, String msg) {
        Action act = new Action();
        act.setTime(time);
        act.setOrder(order);
        act.setUser(user);
        if (state == null) {
            Message message = new Message();
            message.setContent(msg);
            System.out.println("\nIt is message prepared : " + message +"\n");
            message = messageRepository.save(message);
            System.out.println("\nIt is message saved : " + message +"\n");
            act.setMessage(message);
        } else {
            act.setState(state);
        }
        System.out.println("\nIt is action prepared : " + act +"\n");
        act = actionRepository.save(act);
        System.out.println("\nIt is action saved : " + act +"\n");
    }

}
