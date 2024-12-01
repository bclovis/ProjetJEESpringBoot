package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.service.EmploiDuTempsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmploiDuTempsController {

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;

    @GetMapping("/emploiDuTemps")
    public String afficherEmploiDuTemps(
            HttpSession session,
            @RequestParam(name = "semaine", defaultValue = "1") int semaine,
            @RequestParam(name = "filiere", defaultValue = "Mathématiques") String filiere,
            Model model) {

        // Récupérer le rôle et l'email de la session
        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email");

        // Vérifier si l'utilisateur est connecté
        if (role == null || email == null) {
            return "redirect:/login"; // Rediriger vers la page de connexion si non connecté
        }

        Map<String, Map<String, String>> emploiParJourEtHeure = new HashMap<>();

        emploiParJourEtHeure = emploiDuTempsService.getEmploiDuTemps(role, email, semaine, filiere);
        model.addAttribute("emploiParJourEtHeure", emploiParJourEtHeure);

        model.addAttribute("semaine", semaine);
        model.addAttribute("filiere", filiere);
        model.addAttribute("role", role);
        model.addAttribute("jours", new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"});
        model.addAttribute("heures", new String[]{"08h-10h", "10h-12h", "12h-14h", "14h-16h", "16h-18h"});  // Liste des heures

        // Retourner le nom de la vue Thymeleaf
        return "emploiDuTemps"; // Assurez-vous que cette vue est dans le répertoire src/main/resources/templates
    }

}
