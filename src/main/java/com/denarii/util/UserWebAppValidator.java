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

        validateUsername(userWebApp.getUsername(), errors);
        validateEmail(userWebApp.getEmail(), errors);
    }

    private void validateUsername(String username, Errors errors) {
        try {
            userWebAppDetailService.loadUserByName(username);
            errors.rejectValue("username", "duplicate.username", "Такое имя пользователя уже существует");
        } catch (UsernameNotFoundException ignored) {
            // Если не найден, все в порядке
        }
    }

    private void validateEmail(String email, Errors errors) {
        try {
            userWebAppDetailService.loadUserByUsername(email);
            errors.rejectValue("email", "duplicate.email", "Такой email уже существует");
        } catch (UsernameNotFoundException ignored) {
            // Если не найден, все в порядке
        }
    }
}


