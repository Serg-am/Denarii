package com.denarii.service;

import com.denarii.entity.Role;
import com.denarii.entity.UserWebApp;
import com.denarii.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Role defaultRole = Role.USER; // Роль пользователя по умолчанию

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserWebApp userWebApp){

        if (usersRepository.findByEmail(userWebApp.getEmail()) != null) {
            throw new IllegalArgumentException("Email уже используется");
        }

        userWebApp.setPassword(passwordEncoder.encode(userWebApp.getPassword()));
        userWebApp.getRoles().add(defaultRole);
        usersRepository.save(userWebApp);
    }
}

