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
        Optional<UserWebApp> userWebAppOptional = usersRepository.findByEmailIgnoreCase(email); //Optional.ofNullable(usersRepository.findByEmail(email));

        if(userWebAppOptional.isEmpty()){
            System.out.println("Почта не дублируется");//TODO убрать когда не нужно будет
            throw new UsernameNotFoundException("Почта не найдена");
        }
        return new UserWebAppDetails(userWebAppOptional.get());
    }


    public UserDetails loadUserByName(String username) throws UsernameNotFoundException {
        Optional<UserWebApp> userWebAppOptional = usersRepository.findByUsernameIgnoreCase(username); // Optional.ofNullable(usersRepository.findByUsername(username));

        if(userWebAppOptional.isEmpty()){
            System.out.println("Пользователь не найден"); //TODO убрать когда не нужно будет
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserWebAppDetails(userWebAppOptional.get());
    }
}
