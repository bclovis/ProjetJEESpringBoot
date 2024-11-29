package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.MatiereFiliere;
import com.example.projetjeespringboot.service.MatiereFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matierefilieres")
public class MatiereFiliereController {

    @Autowired
    private MatiereFiliereService matiereFiliereService;

    @GetMapping
    public List<MatiereFiliere> getAllMatiereFilieres() {
        return matiereFiliereService.getAllMatiereFilieres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatiereFiliere> getMatiereFiliereById(@PathVariable int id) {
        MatiereFiliere matiereFiliere = matiereFiliereService.getMatiereFiliereById(id);
        if (matiereFiliere == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matiereFiliere);
    }

    @PostMapping
    public MatiereFiliere createMatiereFiliere(@RequestBody MatiereFiliere matiereFiliere) {
        return matiereFiliereService.createOrUpdateMatiereFiliere(matiereFiliere);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatiereFiliere> updateMatiereFiliere(@PathVariable int id, @RequestBody MatiereFiliere matiereFiliereDetails) {
        MatiereFiliere matiereFiliere = matiereFiliereService.getMatiereFiliereById(id);
        if (matiereFiliere == null) {
            return ResponseEntity.notFound().build();
        }
        matiereFiliere.setMatiere(matiereFiliereDetails.getMatiere());
        matiereFiliere.setFiliere(matiereFiliereDetails.getFiliere());

        MatiereFiliere updatedMatiereFiliere = matiereFiliereService.createOrUpdateMatiereFiliere(matiereFiliere);
        return ResponseEntity.ok(updatedMatiereFiliere);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiereFiliere(@PathVariable int id) {
        MatiereFiliere matiereFiliere = matiereFiliereService.getMatiereFiliereById(id);
        if (matiereFiliere == null) {
            return ResponseEntity.notFound().build();
        }
        matiereFiliereService.deleteMatiereFiliere(id);
        return ResponseEntity.noContent().build();
    }
}
