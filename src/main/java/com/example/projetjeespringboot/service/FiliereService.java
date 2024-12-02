package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Filiere;
import com.example.projetjeespringboot.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    // Récupérer toutes les filières
    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    // Trouver une filière par son nom
    public Filiere findByNom(String nom) {
        return filiereRepository.findByNom(nom);
    }

    // Sauvegarder une nouvelle filière
    public Filiere save(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    // Trouver une filière par son ID
    public Optional<Filiere> findById(int id) {
        return filiereRepository.findById(id);
    }
}
