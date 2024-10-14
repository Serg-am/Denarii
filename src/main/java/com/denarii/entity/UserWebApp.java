package com.denarii.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "userwebapp")
public class UserWebApp implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым")
    //@Size(min = 3, max = 32, message = "Имя должно быть от 3 до 32 символов")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Имя не должно быть пустым")
    //@Size(min = 5, max = 32, message = "Почта должна быть от 5 до 32 символов")
    @Column(name = "email")
    private String email;

    //@Size(min = 8, max = 32, message = "Пароль должен быть от 8 до 32 символов")
    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();


    @Column(name = "phone_number")
    private String phoneNumber; // Номер телефона

    @Column(name = "telegram_username")
    private String telegramUsername; // Логин Telegram



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

