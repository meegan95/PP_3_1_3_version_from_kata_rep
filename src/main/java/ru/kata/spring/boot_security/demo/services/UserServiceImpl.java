package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import ru.kata.spring.boot_security.demo.util.UsersValidator;


import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final UsersValidator usersValidator;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository,UsersValidator usersValidator) {
        this.usersRepository = usersRepository;
        this.usersValidator = usersValidator;
    }

    @Transactional
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Transactional
    public User findOne(int id) {
        Optional<User> user = usersRepository.findById(id);
        return user.orElse(null);
    }

    @Transactional
    public User getPersonByUsername(String username) {
        Optional<User> user = usersRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Transactional
    public void save(User user) {
        if (getPersonByUsername(user.getUsername()) == null){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        usersRepository.save(user);}
        else return;
    }

    @Transactional
    public void update(int id, User updatedUser) {
        if (findOne(id).getPassword().equals(updatedUser.getPassword())) {
            updatedUser.setId(id);
            usersRepository.save(updatedUser);
            return;
        }
        updatedUser.setId(id);
        updatedUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

}
