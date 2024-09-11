package com.denarii.service;

import com.denarii.entity.Role;
import com.denarii.entity.UserWebApp;
import com.denarii.repository.UsersRepository;
import com.denarii.security.UserWebAppDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserWebApp user = userRepo.findByEmail(email);  // Изменено на поиск по email
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return new UserWebAppDetails(user);  // Возвращаем UserDetails с использованием email
    }

    public List<UserWebApp> findAll() {
        return userRepo.findAll();
    }

    public List<UserWebApp> findByUsernameContaining(String username) {
        return userRepo.findByUsernameContainingIgnoreCase(username);
    }

    public UserWebApp findById(Long userId) {
        Optional<UserWebApp> userOptional = userRepo.findById(userId);
        return userOptional.orElse(null);
    }

    public void assignOperatorRole(Long userId) {
        UserWebApp user = findById(userId);
        if (user != null) {
            user.getRoles().add(Role.OPERATOR);
            userRepo.save(user);
        }
    }

    public void saveUser(UserWebApp user) {
        userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    public UserWebApp findByRegisterName(String registerName) {
        return userRepo.findByEmail(registerName);
    }
}

