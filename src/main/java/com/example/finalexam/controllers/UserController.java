package com.example.finalexam.controllers;

import com.example.finalexam.dtos.UserDTO;
import com.example.finalexam.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        return "register";
    }


    @PostMapping("/register")
    public String registerPage(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("login") String login,
                               @RequestParam("password") String password, Model model, HttpSession session, Principal principal) {

        userService.register(name, email, login, password);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal){
        UserDTO user = userService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

}
