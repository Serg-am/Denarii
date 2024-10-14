package com.denarii.controller;

import com.denarii.entity.UserWebApp;
import com.denarii.service.RegistrationService;
import com.denarii.service.UserService;
import com.denarii.util.UserWebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    private final UserWebAppValidator userWebAppValidator;

    private final UserService userService;


    @Autowired
    public AuthController(RegistrationService registrationService, UserWebAppValidator userWebAppValidator, UserService userService) {
        this.registrationService = registrationService;
        this.userWebAppValidator = userWebAppValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userwebapp") UserWebApp userWebApp) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userwebapp") @Valid UserWebApp userWebApp,
                                      BindingResult bindingResult,
                                      Model model) {
        userWebAppValidator.validate(userWebApp, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        try {
            registrationService.register(userWebApp);
        } catch (Exception e) {
            // Обработка других возможных ошибок
            model.addAttribute("registrationError", "Произошла ошибка при регистрации. Пожалуйста, попробуйте снова.");
            return "auth/registration";
        }

        return "redirect:/auth/login";
    }
}


