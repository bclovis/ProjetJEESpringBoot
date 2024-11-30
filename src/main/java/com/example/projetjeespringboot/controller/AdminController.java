package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.service.AdminService;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin") // Ajout d'un préfixe commun pour toutes les routes admin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EtudiantService etudiantService;

    private static final int PAGE_SIZE = 20;

    // Page d'accueil de l'admin
    @GetMapping
    public String returnAdminHome() {
        return "admin";
    }

    // Gestion des étudiants dans l'admin
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

    // Méthode pour supprimer un étudiant par email
    @DeleteMapping("/deleteEtudiant/{email}")
    public void deleteEtudiant(@PathVariable String email) {
        etudiantService.deleteEtudiant(email);
    }

    /* Méthode pour créer un nouvel étudiant
    @PostMapping("/createEtudiant")
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.saveEtudiant(etudiant);
    }
    */

    // Méthode pour obtenir un étudiant par email
    @GetMapping("/etudiant/{email}")
    public Optional<Etudiant> getEtudiantByEmail(@PathVariable String email) {
        return etudiantService.getEtudiantByEmail(email);
    }
}
