package com.example.projetjeespringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class EnseignantController {

    @GetMapping("/enseignant")
    public String afficherPageEnseignant() {
        return "enseignant"; // Correspond à src/main/resources/templates/etudiant.html
    }
}
