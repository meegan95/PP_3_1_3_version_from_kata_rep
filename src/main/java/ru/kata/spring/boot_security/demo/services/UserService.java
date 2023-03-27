package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findOne(int id);

    User getPersonByUsername(String username);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);
}
