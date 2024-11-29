package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    @GetMapping
    public List<Enseignant> getAllEnseignants() {
        return enseignantService.getAllEnseignants();
    }

    @GetMapping("/{email}")
    public Optional<Enseignant> getEnseignantByEmail(@PathVariable String email) {
        return enseignantService.getEnseignantByEmail(email);
    }

    @PostMapping
    public Enseignant createEnseignant(@RequestBody Enseignant enseignant) {
        return enseignantService.saveEnseignant(enseignant);
    }

    @DeleteMapping("/{email}")
    public void deleteEnseignant(@PathVariable String email) {
        enseignantService.deleteEnseignant(email);
    }
}
