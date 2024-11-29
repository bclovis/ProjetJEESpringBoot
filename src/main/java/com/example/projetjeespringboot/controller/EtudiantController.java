package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    private static final int PAGE_SIZE = 20;

    @GetMapping("/admin")
    public String returnHome() {
        return "admin";
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

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{email}")
    public Optional<Etudiant> getEtudiantByEmail(@PathVariable String email) {
        return etudiantService.getEtudiantByEmail(email);
    }

    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.saveEtudiant(etudiant);
    }

    @DeleteMapping("/{email}")
    public void deleteEtudiant(@PathVariable String email) {
        etudiantService.deleteEtudiant(email);
    }
}
