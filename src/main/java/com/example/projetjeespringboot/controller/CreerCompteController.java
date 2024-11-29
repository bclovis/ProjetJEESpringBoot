package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.model.Filieres;
import com.example.projetjeespringboot.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class CreerCompteController {

    @Autowired
    private CompteService compteService;

    // Page de création de compte
    @GetMapping("/creationCompte")
    public String showCreationCompteForm() {
        return "creationCompte"; // Page d'inscription
    }

    // Gérer la soumission du formulaire de création de compte
    @PostMapping("/creerCompte")
    public String creerCompte(@RequestParam("typeCompte") String typeCompte,
                              @RequestParam("nom") String nom,
                              @RequestParam("prenom") String prenom,
                              @RequestParam("email") String email,
                              @RequestParam("dateNaissance") String dateNaissanceStr,
                              @RequestParam("mdp") String mdp,
                              Model model) {

        // Vérification du format de l'email
        if (!compteService.isValidEmail(email)) {
            model.addAttribute("error", "Le format de l'email est invalide");
            return "creationCompte"; // Retourne à la page de création de compte avec message d'erreur
        }

        // Vérification de la date de naissance et de l'âge (au moins 18 ans)
        Date dateNaissance;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateNaissance = sdf.parse(dateNaissanceStr);
            int age = compteService.calculateAge(dateNaissance);
            if (age < 18) {
                model.addAttribute("error", "L'utilisateur doit avoir au moins 18 ans");
                return "creationCompte"; // Retourne avec un message d'erreur si moins de 18 ans
            }
        } catch (ParseException e) {
            model.addAttribute("error", "Le format de la date de naissance est invalide");
            return "creationCompte";
        }

        // Vérification si l'email existe déjà
        if (compteService.isEmailExists(email)) {
            model.addAttribute("error", "L'email existe déjà");
            return "creationCompte"; // Retourne avec un message d'erreur si l'email existe déjà
        }

        // Création du compte
        try {
            if ("etudiant".equals(typeCompte)) {
                Etudiant etudiant = new Etudiant(email, nom, prenom, dateNaissance, mdp, Filieres.AUCUNE);
                compteService.createEtudiant(etudiant);
            } else if ("enseignant".equals(typeCompte)) {
                Enseignant enseignant = new Enseignant(email, nom, prenom, dateNaissance, mdp);
                compteService.createEnseignant(enseignant);
            }
            model.addAttribute("success", "Compte créé avec succès.");
            return "creationCompte";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la création du compte.");
            return "creationCompte";
        }
    }
}
