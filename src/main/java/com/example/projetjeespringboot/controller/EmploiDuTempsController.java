package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.service.EmploiDuTempsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        // Récupérer l'emploi du temps avec les noms des cours
        Map<String, Map<String, String>> emploiParJourEtHeure = emploiDuTempsService.getEmploiDuTemps(role, email, semaine, filiere);

        // Récupérer l'ID des cours
        Map<String, Map<String, Integer>> emploiIdParJourEtHeure = emploiDuTempsService.getEmploiDuTempsId(role, email, semaine, filiere);

        model.addAttribute("emploiParJourEtHeure", emploiParJourEtHeure);
        model.addAttribute("emploiIdParJourEtHeure", emploiIdParJourEtHeure);


        model.addAttribute("semaine", semaine);
        model.addAttribute("filiere", filiere);
        model.addAttribute("role", role);
        model.addAttribute("jours", new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"});
        model.addAttribute("heures", new String[]{"08h-10h", "10h-12h", "12h-14h", "14h-16h", "16h-18h"});  // Liste des heures

        return "emploiDuTemps"; // Assurez-vous que cette vue est dans le répertoire src/main/resources/templates
    }

    @PostMapping("/supprimerCours")
    public String supprimerCours(
            @RequestParam String jour,
            @RequestParam String heure,
            @RequestParam Integer coursId,
            HttpSession session,
            Model model) {

        // Récupérer le rôle et l'email de la session
        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email");

        // Vérifier si l'utilisateur est connecté
        if (role == null || email == null) {
            return "redirect:/login"; // Rediriger vers la page de connexion si non connecté
        }

        // Appeler le service pour supprimer le cours
        boolean success = emploiDuTempsService.supprimerCours(role, email, jour, heure, coursId);

        // Ajouter un message de succès ou d'erreur dans le modèle
        if (success) {
            model.addAttribute("message", "Le cours a été supprimé avec succès.");
        } else {
            model.addAttribute("message", "Erreur lors de la suppression du cours.");
        }

        // Recharger l'emploi du temps avec les paramètres par défaut : semaine 1 et filière "Mathématiques"
        int semaine = 1; // Par défaut, semaine 1
        String filiere = "Mathématiques"; // Par défaut, filière Mathématiques

        // Récupérer l'emploi du temps mis à jour après la suppression
        Map<String, Map<String, String>> emploiParJourEtHeure = emploiDuTempsService.getEmploiDuTemps(role, email, semaine, filiere);
        Map<String, Map<String, Integer>> emploiIdParJourEtHeure = emploiDuTempsService.getEmploiDuTempsId(role, email, semaine, filiere);

        model.addAttribute("emploiParJourEtHeure", emploiParJourEtHeure);
        model.addAttribute("emploiIdParJourEtHeure", emploiIdParJourEtHeure);

        model.addAttribute("semaine", semaine);
        model.addAttribute("filiere", filiere);
        model.addAttribute("role", role);
        model.addAttribute("jours", new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"});
        model.addAttribute("heures", new String[]{"08h-10h", "10h-12h", "12h-14h", "14h-16h", "16h-18h"});  // Liste des heures

        // Retourner à l'emplois du temps de la semaine 1 et de la filière Mathématiques
        return "emploiDuTemps"; // Assurez-vous que la vue est rechargée avec les données mises à jour
    }
}
