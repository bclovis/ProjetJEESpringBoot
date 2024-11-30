package com.example.projetjeespringboot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class EnseignantController {

    @GetMapping("/enseignant")
    public String afficherPageEnseignant(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);
        return "enseignant";
    }

}
