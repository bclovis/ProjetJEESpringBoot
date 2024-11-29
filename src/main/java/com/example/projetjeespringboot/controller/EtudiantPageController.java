package com.example.projetjeespringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etudiant")
public class EtudiantPageController {

    @GetMapping
    public String afficherPageEtudiant() {
        return "etudiant";
    }
}