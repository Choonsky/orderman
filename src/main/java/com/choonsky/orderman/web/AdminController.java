package com.choonsky.orderman.web;

import com.choonsky.orderman.model.User;

import com.choonsky.orderman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    /*
        @RequestMapping("/admin")

        public @ResponseBody Iterable<User> getAllUsers() {
            // This returns a JSON or XML with the users
            return userRepository.findAll();
        }
    */
    @RequestMapping("/admin")
    public ModelAndView getAdmin() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("serverTime", dateFormat.format(new Date()));
        List<User> users = userRepository.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}