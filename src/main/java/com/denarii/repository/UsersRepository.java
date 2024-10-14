package com.denarii.repository;

import com.denarii.entity.UserWebApp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserWebApp, Long> {
    UserWebApp findByUsername(String username);
    UserWebApp findByEmail(String email);
    Optional<UserWebApp> findByUsernameIgnoreCase(String username);
    Optional<UserWebApp> findByEmailIgnoreCase(String email);
    List<UserWebApp> findByUsernameContainingIgnoreCase(String username);

}
