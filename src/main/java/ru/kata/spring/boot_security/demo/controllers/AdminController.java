package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RolesService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UsersValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final RolesService rolesService;
    private final UserService userService;
    private final UsersValidator usersValidator;

    @Autowired
    public AdminController(UserService userService, RolesService rolesService, UsersValidator usersValidator) {
        this.userService = userService;
        this.rolesService = rolesService;
        this.usersValidator = usersValidator;

    }


    @GetMapping()
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("admin", userService.getPersonByUsername(principal.getName()));
        model.addAttribute("user", userService.getPersonByUsername(principal.getName()));
        model.addAttribute("allRoles", rolesService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "admin/show";
    }

    //    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "admin/new";
//    }
//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "admin/new";
//    }
    @GetMapping("/new")
    public String newUser( Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        List<Role> roles = rolesService.getRoles();
        model.addAttribute("allRoles", roles);
        return "admin/new";
    }

//    @PostMapping("/new")
//    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
////        usersValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) return "/admin/new";
//        userService.save(user, bindingResult);
//        return "redirect:/admin";
//    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Role> roles = rolesService.getRoles();
            model.addAttribute("allRoles", roles);
            return "/admin/new";
        }
        userService.save(user);
        return "redirect:/admin/";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findOne(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult
            , @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
