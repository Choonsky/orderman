package com.choonsky.orderman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.*;

@Controller
public class MainController {

    @RequestMapping("/success")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_CHIEF")) {
            return "redirect:/chief";
        } else if (request.isUserInRole("ROLE_SUPPLIER")) {
            return "redirect:/supplier";
        }
        return "redirect:/user";
    }
}