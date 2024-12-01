package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import com.example.projetjeespringboot.repository.ProfesseurMatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupprimerAssociationController {

    @Autowired
    private ProfesseurMatiereRepository professeurMatiereRepository;

    @PostMapping("/supprimerAssocProfMatiere")  // Vérifiez ici la méthode POST et l'URL
    public String supprimerAssociation(@RequestParam("id") Integer id, Model model) {

        if (id != null) {
            // Vérifier si l'association existe
            ProfesseurMatiere association = professeurMatiereRepository.findById(id).orElse(null);

            if (association != null) {
                // Supprimer l'association
                professeurMatiereRepository.delete(association);
                model.addAttribute("message", "Association supprimée avec succès.");
            } else {
                model.addAttribute("errorMessage", "Association introuvable.");
            }
        } else {
            model.addAttribute("errorMessage", "ID d'association invalide.");
        }

        // Rediriger vers la page qui affiche les associations
        return "associerProfesseurMatiere"; // Affiche de nouveau la page avec les messages
    }
}
