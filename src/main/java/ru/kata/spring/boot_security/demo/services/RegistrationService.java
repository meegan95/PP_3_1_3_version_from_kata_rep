package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;


@Service
public class RegistrationService {

    private final UsersRepository usersRepository;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersRepository usersRepository
//            , PasswordEncoder passwordEncoder
    ) {
        this.usersRepository = usersRepository;
//        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(new Role(user.getId(),"ROLE_USER"));
        usersRepository.save(user);
    }

}
