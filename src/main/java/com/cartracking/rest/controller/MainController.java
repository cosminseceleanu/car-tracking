package com.cartracking.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "titlu");
        model.addAttribute("body", "asasdas");

        return "index";
    }

    @RequestMapping("/")
    public String main() {
        return "redirect:/app/index.html";
    }
}
