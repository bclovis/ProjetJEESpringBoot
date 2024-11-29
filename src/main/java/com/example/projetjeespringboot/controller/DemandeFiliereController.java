package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.service.DemandeFiliereService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemandeFiliereController {

    private final DemandeFiliereService demandeFiliereService;

    public DemandeFiliereController(DemandeFiliereService demandeFiliereService) {
        this.demandeFiliereService = demandeFiliereService;
    }

    @GetMapping("/demandeFiliere")
    public String showChoixFiliereForm() {
        // Affiche la page demandeFiliere.html
        return "demandeFiliere";
    }

    @GetMapping("/retourMenuEtudiant")
    public String retourMenuEtu() {
        // Affiche la page demandeFiliere.html
        return "etudiant";
    }

    @PostMapping("/demandeFiliere")
    public String envoyerDemande(@RequestParam("filiere") String filiereStr, HttpSession session, Model model) {
        String etudiantEmail = (String) session.getAttribute("email");
        if (etudiantEmail == null) {
            model.addAttribute("error", "Erreur : Étudiant non connecté.");
            return "demandeFiliere";
        }

        try {
            demandeFiliereService.createDemandeFiliere(etudiantEmail, filiereStr);
            model.addAttribute("success", "Votre demande a été envoyée avec succès.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
        }

        return "demandeFiliere";  // Renvoyer vers la même page avec un message d'état
    }
}
