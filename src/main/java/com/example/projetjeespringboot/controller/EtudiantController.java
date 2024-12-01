package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.model.Filieres;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    private static final int PAGE_SIZE = 20;


    @GetMapping("/admin")
    public String returnHome() {
        return "admin";
    }

    @GetMapping("/etudiant")
    public String afficherPageEtudiant() {
        return "etudiant";
    }

    @GetMapping("/gererEtudiants")
    public String gererEtudiants(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "recherche", required = false) String recherche,
            Model model) {

        // Récupération des étudiants et du nombre total de pages
        List<Etudiant> etudiants = etudiantService.getEtudiants(page, PAGE_SIZE, recherche);
        int totalPages = etudiantService.getTotalPages(PAGE_SIZE, recherche);

        // Ajout des données au modèle pour la vue
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("recherche", recherche);

        return "gererEtudiants";
    }

    @PostMapping("/etudiants/modifier")
    public String afficherFormulaireModification(@RequestParam("email") String email, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantByEmail(email);
        if (etudiant == null) {
            return "redirect:/gererEtudiants?error=Étudiant%20introuvable";
        }
        model.addAttribute("etudiant", etudiant);
        return "modifierEtudiant"; // Nom de la vue (HTML) dans `resources/templates`
    }

    @PostMapping("/etudiants/saveChanges")
    public String enregistrerModification(@RequestParam("email") String email,
                                          @RequestParam("nom") String nom,
                                          @RequestParam("prenom") String prenom,
                                          @RequestParam("dateNaissance") String dateNaissanceStr,
                                          @RequestParam("mdp") String mdp,
                                          Model model) {

        // Conversion de la date de naissance depuis String
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNaissance = sdf.parse(dateNaissanceStr);

            // Mise à jour des informations de l'étudiant
            Etudiant etudiant = new Etudiant(email, nom, prenom, dateNaissance, mdp, Filieres.AUCUNE);
            boolean modificationRéussie = etudiantService.modifierEtudiant(etudiant);

            if (modificationRéussie) {
                model.addAttribute("success", "Étudiant modifié avec succès.");
                model.addAttribute("etudiant", etudiant);
                return "modifierEtudiant";
            } else {
                model.addAttribute("error", "Erreur lors de la modification de l'étudiant.");
                model.addAttribute("etudiant", etudiant);
                return "modifierEtudiant"; // Retourne avec message d'erreur
            }
        } catch (ParseException e) {
            model.addAttribute("error", "Erreur de format de date.");
            return "modifierEtudiant";
        }
    }

    @PostMapping("/etudiants/supprimer")
    public String supprimerEtudiant(@RequestParam("email") String email, Model model) {
        boolean suppressionReussie = etudiantService.supprimerEtudiant(email);
        if (suppressionReussie) {
            model.addAttribute("success", "Étudiant supprimé avec succès.");
        } else {
            model.addAttribute("error", "Erreur lors de la suppression de l'étudiant.");
        }
        return "redirect:/gererEtudiants"; // Redirection vers la page de gestion
    }

}
