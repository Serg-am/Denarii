package com.denarii.service;

import com.denarii.entity.UserWebApp;
import com.denarii.repository.UsersRepository;
import com.denarii.security.UserWebAppDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserWebAppDetailService extends UserWebApp implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserWebAppDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Тут");
        System.out.println(email);
        Optional<UserWebApp> userWebAppOptional = Optional.ofNullable(usersRepository.findByEmail(email));

        if(userWebAppOptional.isEmpty()){
            System.out.println("Не найден");
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserWebAppDetails(userWebAppOptional.get());
    }
}
