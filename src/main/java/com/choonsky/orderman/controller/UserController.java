package com.choonsky.orderman.controller;
import com.choonsky.orderman.model.*;
import com.choonsky.orderman.repository.OrderRepository;
import com.choonsky.orderman.repository.ProductRepository;
import com.choonsky.orderman.repository.UserRepository;
import com.choonsky.orderman.web.OrderTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/user")
    public ModelAndView getUser() {

        ModelAndView modelAndView = new ModelAndView("user");

// fetching user name...
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserLogin);
        String currentUserName = "_Кто-то в сером_";
        if (currentUser.isPresent()) currentUserName = currentUser.get().getName();
        modelAndView.addObject("currentUserName", currentUserName);

// fetching all products...
        List<Product> products = new ArrayList<>(productRepository.findAll());
        Collections.sort(products, Comparator.comparing(Product::getProductName));
        modelAndView.addObject("products", products);

// fetching all orders...
        List<Order> orders = new ArrayList<>(orderRepository.findAllByUser(currentUser.get()));
        Collections.sort(orders, Comparator.comparing(Order::getId).reversed());

// populating order templates...
        ArrayList<OrderTemplate> ordersReady = new ArrayList<>();
        orders.forEach(order -> {
            ArrayList<OrderLine> orderLines = new ArrayList<>(order.getOrderLines());

            ArrayList<Action> actions = new ArrayList<>(order.getActions());
            Collections.sort(actions, Comparator.comparing(Action::getId));

            String state = order.getState().getStateName();

            ArrayList<Message> messages = new ArrayList<>();
            actions.forEach(action -> {
                if (action.getMessage() != null) messages.add(action.getMessage());
            });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            ordersReady.add(new OrderTemplate(order.getId(), actions.get(0).getTime().format(formatter),
                    orderLines.get(0).getProduct().getProductName(), orderLines.size(),
                    state.equals("SENT") || state.equals("APPROVED") || state.equals("EXECUTING") || state.equals("FINISHED"),
                    state.equals("APPROVED") || state.equals("EXECUTING") || state.equals("FINISHED"),
                    state.equals("EXECUTING") || state.equals("FINISHED"),
                    state.equals("FINISHED"), messages.size(), orderLines, actions));
        });
        modelAndView.addObject("orders", ordersReady);

// let's see what we have done...
        return modelAndView;
    }
}