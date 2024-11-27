package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etudiants")
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
}
