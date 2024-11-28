package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.service.EmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

@Controller
public class EmploiDuTempsController {

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;

    @GetMapping("/AfficherEDTEtuEns")
    public String afficherEDTEtuEns(
            @SessionAttribute("role") String role,
            @SessionAttribute("email") String email,
            @RequestParam(name = "semaine", defaultValue = "1") int semaine,
            Model model) {

        // Vérifier si l'utilisateur est connecté
        if (role == null || email == null) {
            return "redirect:/login"; // Rediriger vers la page de connexion si non connecté
        }

        // Obtenir l'emploi du temps en fonction du rôle, de l'email et de la semaine
        Map<String, Map<String, String>> emploiParJourEtHeure = emploiDuTempsService.getEmploiDuTemps(role, email, semaine);

        // Ajouter les données au modèle Thymeleaf
        model.addAttribute("emploiParJourEtHeure", emploiParJourEtHeure);
        model.addAttribute("semaine", semaine);

        // Retourner le nom de la vue Thymeleaf
        return "EDTEtuEns";
    }
}
