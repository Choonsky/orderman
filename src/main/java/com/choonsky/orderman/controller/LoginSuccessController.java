package com.choonsky.orderman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.*;

@Controller
public class LoginSuccessController {

    @RequestMapping("/success")
    public String defaultAfterLogin(HttpServletRequest request) {
        System.out.println("\nSuccess, redirecting to...");
        if (request.isUserInRole("ROLE_ADMIN")) {
            System.out.println("admin\n");
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_CHIEF")) {
            System.out.println("chief\n");
            return "redirect:/chief";
        } else if (request.isUserInRole("ROLE_SUPPLIER")) {
            System.out.println("supplier\n");
            return "redirect:/supplier";
        }
        System.out.println("user\n");
        return "redirect:/user";
    }
}