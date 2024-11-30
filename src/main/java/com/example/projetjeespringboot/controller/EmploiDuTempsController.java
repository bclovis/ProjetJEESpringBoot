package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.service.EmploiDuTempsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;

@Controller
public class EmploiDuTempsController {

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;

    @GetMapping("/AfficherEDTEtuEns")
    public String afficherEDTEtuEns(
            HttpSession session,
            @RequestParam(name = "semaine", defaultValue = "1") int semaine,
            Model model) {

        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email");

        // Vérifier si l'utilisateur est connecté
        if (role == null || email == null) {
            return "redirect:/login"; // Rediriger vers la page de connexion si non connecté
        }

        // Obtenir l'emploi du temps en fonction du rôle, de l'email et de la semaine
        Map<String, Map<String, String>> emploiParJourEtHeure = emploiDuTempsService.getEmploiDuTemps(role, email, semaine);

        // Ajouter les données au modèle Thymeleaf
        model.addAttribute("emploiParJourEtHeure", emploiParJourEtHeure);
        model.addAttribute("semaine", semaine);
        model.addAttribute("role", role);
        model.addAttribute("jours", new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"});
        model.addAttribute("heures", new String[]{"08h-10h", "10h-12h", "12h-14h", "14h-16h", "16h-18h"});  // Ajouter la liste des heures

        // Retourner le nom de la vue Thymeleaf
        return "EDTEtuEns";
    }
}
