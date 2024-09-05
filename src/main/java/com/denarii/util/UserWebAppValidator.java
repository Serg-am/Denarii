package com.denarii.util;

import com.denarii.entity.UserWebApp;
import com.denarii.service.UserWebAppDetailService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserWebAppValidator implements Validator {

    private final UserWebAppDetailService userWebAppDetailService;

    public UserWebAppValidator(UserWebAppDetailService userWebAppDetailService) {
        this.userWebAppDetailService = userWebAppDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserWebApp.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserWebApp userWebApp = (UserWebApp) target;

        try{
            userWebAppDetailService.loadUserByUsername(userWebApp.getUsername());
        } catch (UsernameNotFoundException ignored){
            return; // Если не найден, все в порядке
        }

        errors.rejectValue("username", "", "Такое имя пользователя уже существует");

        try {
            userWebAppDetailService.loadUserByUsername(userWebApp.getEmail());
        } catch (UsernameNotFoundException ignored) {
            return;  // Если не найден, все в порядке
        }

        errors.rejectValue("email", "", "Такой email уже существует");
    }
}


