package com.greatlearning.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/welcome")
    public String displayWelcomePage() {
        return "welcome-page";
    }

    @RequestMapping("/")
    public String rootContextPathAccess() {
        return "redirect:/student/list";
    }

}