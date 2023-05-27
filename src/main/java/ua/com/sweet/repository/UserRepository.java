package ua.com.sweet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.sweet.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
