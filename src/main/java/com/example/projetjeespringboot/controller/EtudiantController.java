package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/etudiant") // Définition d'un préfixe pour toutes les routes étudiant
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // Affichage de la page étudiante (par exemple)
    @GetMapping
    public String afficherPageEtudiant() {
        return "etudiant";
    }

}
