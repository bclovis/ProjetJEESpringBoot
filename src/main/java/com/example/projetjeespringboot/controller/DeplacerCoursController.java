package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import com.example.projetjeespringboot.service.EmploiDuTempsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deplacerCours")
public class DeplacerCoursController {

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;

    // Afficher le formulaire pour déplacer un cours
    @GetMapping("/{coursId}")
    public String formDeplacerCours(@PathVariable int coursId, Model model) {
        EmploiDuTemps cours = emploiDuTempsService.getCoursById(coursId);
        model.addAttribute("cours", cours);
        return "deplacerCours"; // Retourner le formulaire pour déplacer le cours
    }

    // Lorsqu'on soumet le formulaire pour déplacer le cours
    @PostMapping
    public String deplacerCours(@RequestParam int coursId, @RequestParam String jour, @RequestParam String heure,
                                @RequestParam int semaine, Model model) {

        // Récupérer le cours à déplacer
        EmploiDuTemps cours = emploiDuTempsService.getCoursById(coursId);

        // Vérification des conflits (même professeur ou même créneau dans la filière)
        boolean conflit = emploiDuTempsService.verifierConflit(jour, heure, semaine, cours.getFiliere().getId(), coursId, cours.getProfesseur().getEmail());

        if (conflit) {
            // Si un conflit est trouvé, renvoyer un message d'erreur
            model.addAttribute("message", "Le créneau est déjà occupé par un autre cours.");
            model.addAttribute("cours", cours); // Repasser les informations du cours
            //model.addAttribute("messageClass", "error"); // Classe pour afficher l'erreur en rouge
            return "deplacerCours";  // Retourner au formulaire
        }

        // Si pas de conflit, déplacer le cours
        boolean success = emploiDuTempsService.deplacerCours(coursId, jour, heure, semaine);

        if (success) {
            model.addAttribute("message", "Le cours a été déplacé avec succès.");
            model.addAttribute("messageClass", "success"); // Classe pour afficher le succès
        } else {
            model.addAttribute("message", "Erreur lors du déplacement du cours.");
            model.addAttribute("messageClass", "error"); // Classe pour l'erreur
        }

        // Redirection vers l'emploi du temps avec la filière dynamique et semaine
        return "redirect:/emploiDuTemps?semaine=" + semaine + "&filiere=" + cours.getFiliere().toString();  // Redirige vers semaine et filière Mathématiques
    }
}
