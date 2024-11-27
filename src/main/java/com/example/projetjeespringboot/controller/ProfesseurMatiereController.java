package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import com.example.projetjeespringboot.service.ProfesseurMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professeur-matieres")
public class ProfesseurMatiereController {

    @Autowired
    private ProfesseurMatiereService professeurMatiereService;

    @GetMapping
    public List<ProfesseurMatiere> getAllProfesseurMatieres() {
        return professeurMatiereService.getAllProfesseurMatieres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesseurMatiere> getProfesseurMatiereById(@PathVariable int id) {
        Optional<ProfesseurMatiere> professeurMatiere = professeurMatiereService.getProfesseurMatiereById(id);
        if (professeurMatiere.isPresent()) {
            return ResponseEntity.ok(professeurMatiere.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ProfesseurMatiere createProfesseurMatiere(@RequestBody ProfesseurMatiere professeurMatiere) {
        return professeurMatiereService.saveProfesseurMatiere(professeurMatiere);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesseurMatiere> updateProfesseurMatiere(@PathVariable int id, @RequestBody ProfesseurMatiere updatedProfesseurMatiere) {
        Optional<ProfesseurMatiere> existingProfesseurMatiere = professeurMatiereService.getProfesseurMatiereById(id);
        if (existingProfesseurMatiere.isPresent()) {
            updatedProfesseurMatiere.setId(id);
            return ResponseEntity.ok(professeurMatiereService.saveProfesseurMatiere(updatedProfesseurMatiere));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesseurMatiereById(@PathVariable int id) {
        professeurMatiereService.deleteProfesseurMatiereById(id);
        return ResponseEntity.noContent().build();
    }
}
