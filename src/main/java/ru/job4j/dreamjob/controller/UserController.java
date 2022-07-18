package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.UserService;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("message", "Пользователь с такой почтой уже существует");
        return "fail";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        Optional<User> regUser = service.add(user);
        if (regUser.isEmpty()) {
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        Optional<User> userDb = service.findUserByName(user.getName());
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        return "redirect:/index";
    }
}
