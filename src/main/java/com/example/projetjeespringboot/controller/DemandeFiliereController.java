package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.DemandeFiliere;
import com.example.projetjeespringboot.service.DemandeFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandes-filiere")
public class DemandeFiliereController {

    @Autowired
    private DemandeFiliereService demandeFiliereService;

    @PostMapping
    public DemandeFiliere createOrUpdateDemandeFiliere(@RequestBody DemandeFiliere demandeFiliere) {
        return demandeFiliereService.saveDemandeFiliere(demandeFiliere);
    }

    @GetMapping("/{id}")
    public Optional<DemandeFiliere> getDemandeFiliereById(@PathVariable int id) {
        return demandeFiliereService.getDemandeFiliereById(id);
    }

    @GetMapping
    public List<DemandeFiliere> getAllDemandesFiliere() {
        return demandeFiliereService.getAllDemandesFiliere();
    }

    @DeleteMapping("/{id}")
    public void deleteDemandeFiliere(@PathVariable int id) {
        demandeFiliereService.deleteDemandeFiliereById(id);
    }
}
