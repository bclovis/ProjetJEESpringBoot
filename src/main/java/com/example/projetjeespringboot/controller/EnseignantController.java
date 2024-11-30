package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    @GetMapping("/enseignant")
    public String afficherPageEnseignant() {
        return "enseignant"; // Correspond à src/main/resources/templates/etudiant.html
    }

    public List<Enseignant> getAllEnseignants() {
        return enseignantService.getAllEnseignants();
    }

    public Optional<Enseignant> getEnseignantByEmail(@PathVariable String email) {
        return enseignantService.getEnseignantByEmail(email);
    }

    @PostMapping("admin/createEnseignant")
    public Enseignant createEnseignant(@RequestBody Enseignant enseignant) {
        return enseignantService.saveEnseignant(enseignant);
    }

    @DeleteMapping("admin/deleteEnseignant/{email}")
    public void deleteEnseignant(@PathVariable String email) {
        enseignantService.deleteEnseignant(email);
    }
}
