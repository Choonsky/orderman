package com.choonsky.orderman.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView getAdmin() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("serverTime", dateFormat.format(new Date()));
        return modelAndView;
    }
}