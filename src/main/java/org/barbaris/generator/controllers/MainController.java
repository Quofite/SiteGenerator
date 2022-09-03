package org.barbaris.generator.controllers;

import org.barbaris.generator.models.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/generate")
    public String generation(@RequestBody PageModel page) {
        


        return "redirect:/index";
    }
}
