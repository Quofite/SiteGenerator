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
    @PostMapping("/generate")
    public String generation(@RequestBody PageModel page) {
        


        return "redirect:/index";
    }
}
