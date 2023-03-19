package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    //    @GetMapping()
//    public String index(Model model){
//        model.addAttribute("users", userService.findAll());
//        return "user/index";
//
//    }
    @GetMapping
    public String show(Principal principal, Model model) {
        model.addAttribute("user", userService.getPersonByUsername(principal.getName()));
        return "/user/show";
    }

//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.findOne(id));
//        return "user/show";
//    }
}
