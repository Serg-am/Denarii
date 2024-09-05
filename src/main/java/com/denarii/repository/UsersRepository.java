package com.denarii.repository;

import com.denarii.entity.UserWebApp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsersRepository extends JpaRepository<UserWebApp, Long> {
    UserWebApp findByUsername(String username);
    UserWebApp findByEmail(String email);
    List<UserWebApp> findByUsernameContainingIgnoreCase(String username);

}
