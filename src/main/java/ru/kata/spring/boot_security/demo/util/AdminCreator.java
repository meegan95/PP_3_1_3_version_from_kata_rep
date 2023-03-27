package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.HashSet;

@Component
public class AdminCreator {

    private final UsersRepository usersRepository;
    @Autowired
    public AdminCreator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Bean
    public void adminCreate(){
        try {
            HashSet<Role> roles = new HashSet<>();
            roles.add(new Role("ROLE_ADMIN"));
            roles.add(new Role("ROLE_USER"));
            usersRepository.save(new User("admin", 1999,encodePassword("admin"), roles));
        } catch (Exception e) {/*ignore*/}
    }
    @Bean
    public void userCreate(){
        try {
            HashSet<Role> roles = new HashSet<>();
            roles.add(new Role("ROLE_USER"));
            usersRepository.save(new User("user", 1999, encodePassword("user"), roles));
        } catch (Exception e) {/*ignore*/}
    }

    private String encodePassword(String password){
       return new BCryptPasswordEncoder().encode(password);
    }
}
