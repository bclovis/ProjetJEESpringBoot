package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import com.example.projetjeespringboot.service.EmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emploi-du-temps")
public class EmploiDuTempsController {

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;

    @PostMapping
    public EmploiDuTemps createOrUpdateEmploiDuTemps(@RequestBody EmploiDuTemps emploiDuTemps) {
        return emploiDuTempsService.saveEmploiDuTemps(emploiDuTemps);
    }

    @GetMapping("/{id}")
    public Optional<EmploiDuTemps> getEmploiDuTempsById(@PathVariable int id) {
        return emploiDuTempsService.getEmploiDuTempsById(id);
    }

    @GetMapping
    public List<EmploiDuTemps> getAllEmploiDuTemps() {
        return emploiDuTempsService.getAllEmploiDuTemps();
    }

    @DeleteMapping("/{id}")
    public void deleteEmploiDuTemps(@PathVariable int id) {
        emploiDuTempsService.deleteEmploiDuTempsById(id);
    }
}
