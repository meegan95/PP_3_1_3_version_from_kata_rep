package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        updatedUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

}