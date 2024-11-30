package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

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

    @GetMapping("/etudiant")
    public String afficherPageEtudiant() {
        return "etudiant";
    }
}
