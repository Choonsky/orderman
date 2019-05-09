package com.choonsky.orderman.controller;

import com.choonsky.orderman.model.*;

import com.choonsky.orderman.repository.OrderRepository;
import com.choonsky.orderman.repository.UserRepository;
import com.choonsky.orderman.web.OrderTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/admin")
    public ModelAndView getAdmin() {

        ModelAndView modelAndView = new ModelAndView("admin");

// sending date and time...
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm, dd-MM-yyyy, разница с Лондоном Z");
        modelAndView.addObject("serverTime", dateFormat.format(new Date()));

// sending user name...
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserLogin);
        String currentUserName = "_Кто-то в сером_";
        if (currentUser.isPresent()) currentUserName = currentUser.get().getName();
        modelAndView.addObject("currentUserName", currentUserName);

// fetching all legal orders...
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(order -> orders.add(order));
//        modelAndView.addObject("orders", orders);

// populating order templates...
        ArrayList<OrderTemplate> ordersReady = new ArrayList<>();
        orders.forEach(order -> {
            ArrayList<OrderLine> orderLines = new ArrayList<>(order.getOrderLines());
            ArrayList<Action> actions = new ArrayList<>(order.getActions());
            String state = order.getState().getStateName();
            ArrayList<Message> messages = new ArrayList<>();
            actions.forEach(action -> {
                if (action.getIdMessage() != null) messages.add(action.getMessage());
            });
            ordersReady.add(new OrderTemplate(order.getId(), actions.get(0).getTime(), orderLines.get(0).getProduct().getProductName(),
                    orderLines.size(), state.equals("SENT"), state.equals("APPROVED"), state.equals("EXECUTING"),
                    state.equals("FINISHED"), messages.size()));
        });
        modelAndView.addObject("orders", ordersReady);

// let's see what we have done...
        return modelAndView;
    }
}