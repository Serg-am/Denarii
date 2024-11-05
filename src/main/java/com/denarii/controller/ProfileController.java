package com.denarii.controller;

import com.denarii.entity.UserWebApp;
import com.denarii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public String profile(Model model) {
        UserWebApp user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "profile";
    }

}
