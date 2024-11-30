package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Matiere;
import com.example.projetjeespringboot.repository.MatiereRepository;
import com.example.projetjeespringboot.service.AjouterNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class AjouterNoteController {

    @Autowired
    private AjouterNoteService ajouterNoteService;

    @Autowired
    private MatiereRepository matiereRepository;

    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(
            @SessionAttribute("email") String emailProf,
            Model model) {
        // Récupérer les matières enseignées par le professeur connecté
        List<Matiere> matieres = matiereRepository.findByProfesseurEmail(emailProf);

        // Ajouter les matières récupérées au modèle
        model.addAttribute("matieres", matieres);
        return "ajouterNote";
    }

    @PostMapping("/ajouter")
    public String ajouterNote(
            @RequestParam String emailEtudiant,
            @RequestParam String matiere,
            @RequestParam float note,
            @SessionAttribute("email") String emailProf,
            Model model) {
        try {
            ajouterNoteService.ajouterNote(emailProf, matiere, emailEtudiant, note);
            model.addAttribute("success", "Note ajoutée avec succès !");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "ajouterNote";
    }
}
