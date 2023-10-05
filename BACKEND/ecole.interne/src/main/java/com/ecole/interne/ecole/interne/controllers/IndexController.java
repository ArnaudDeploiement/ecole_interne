package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.User;
import com.ecole.interne.ecole.interne.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class IndexController {

    private final UserRepository usercrud;


    @Autowired
    public IndexController(UserRepository usercrud) {
        this.usercrud = usercrud;
    }

    @GetMapping
    public String showUser(Model model) {
        model.addAttribute("message", "Application Spring boot + Thymeleaf - Créer un nouveau utilisateur");
        model.addAttribute("createUser", new User());
        List<User> users= usercrud.findAll();
        model.addAttribute("users", users);
        return "index";
    }


}
