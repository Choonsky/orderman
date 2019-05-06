package com.choonsky.orderman.controller;

import com.choonsky.orderman.model.Order;
import com.choonsky.orderman.model.User;

import com.choonsky.orderman.repository.OrderRepository;
import com.choonsky.orderman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm, dd-MM-yyyy, разница с Лондоном Z");
        modelAndView.addObject("serverTime", dateFormat.format(new Date()));

        List<Order> orders = orderRepository.findAll();
        modelAndView.addObject("orders", orders);

        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = userRepository.findByEmail(currentUserName);
        String currentUserFullName = "_Кто-то в сером_";
        if (currentUser.isPresent()) currentUserFullName = currentUser.get().getName();
        modelAndView.addObject("userFullName", currentUserFullName);

        return modelAndView;
    }
}