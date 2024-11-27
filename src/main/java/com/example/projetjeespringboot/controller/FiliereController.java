package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Filiere;
import com.example.projetjeespringboot.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @GetMapping
    public List<Filiere> getAllFilieres() {
        return filiereService.getAllFilieres();
    }

    @GetMapping("/{id}")
    public Optional<Filiere> getFiliereById(@PathVariable int id) {
        return filiereService.getFiliereById(id);
    }

    @PostMapping
    public Filiere createFiliere(@RequestBody Filiere filiere) {
        return filiereService.saveFiliere(filiere);
    }

    @DeleteMapping("/{id}")
    public void deleteFiliere(@PathVariable int id) {
        filiereService.deleteFiliere(id);
    }
}
