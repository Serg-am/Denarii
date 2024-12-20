package com.denarii.config;

import com.denarii.service.UserWebAppDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserWebAppDetailService userWebAppDetailService;

    @Autowired
    public SecurityConfig(UserWebAppDetailService userWebAppDetailService) {
        this.userWebAppDetailService = userWebAppDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                //.antMatchers("/admin-panel").hasRole("ADMIN")
                .antMatchers("/auth/**", "/logout", "/index", "/", "/cities", "/ads", "/test").permitAll()
                .antMatchers("/css/**", "/fonts/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/admin", "/admin_panel").hasRole("ADMIN")
                .antMatchers("/user", "/requests").hasRole("USER")
                .antMatchers("/operator", "/operator_panel").hasRole("OPERATOR")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
                .and()
                .exceptionHandling();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userWebAppDetailService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


