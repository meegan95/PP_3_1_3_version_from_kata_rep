//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.ModelAndView;
//import ru.kata.spring.boot_security.demo.models.Role;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.repositories.RolesRepository;
//import ru.kata.spring.boot_security.demo.services.UsersDetailsService;
//
//import java.util.List;
//@Controller
//public class UsersController {
//
//    private UsersDetailsService usersDetailsService;
//    private RolesRepository rolesRepository;
//
//    @Autowired
//    public UsersController(UsersDetailsService usersDetailsService, RolesRepository rolesRepository) {
//        this.usersDetailsService = usersDetailsService;
//        this.rolesRepository = rolesRepository;
//    }
//
//    @GetMapping("/users/new")
//    public ModelAndView newUser() {
//        User user = new User();
//        ModelAndView mav = new ModelAndView("user_form");
//        mav.addObject("user", user);
//
//        List<Role> roles = (List<Role>) rolesRepository.findAll();
//
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }
//
////    @GetMapping("/users/edit/{id}")
////    public ModelAndView editUser(@PathVariable(name = "id") Integer id) {
//////        User user = usersDetailsService.loadUserByUsername()
//////                service.get(id);
////        ModelAndView mav = new ModelAndView("user_form");
////        mav.addObject("user", user);
////
////        List<Role> roles = (List<Role>) rolesRepository.findAll();
////
////        mav.addObject("allRoles", roles);
////
////        return mav;
////    }
//}
