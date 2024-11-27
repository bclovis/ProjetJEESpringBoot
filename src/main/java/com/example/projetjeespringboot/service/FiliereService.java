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

    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    public Optional<Filiere> getFiliereById(int id) {
        return filiereRepository.findById(id);
    }

    public Filiere saveFiliere(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public void deleteFiliere(int id) {
        filiereRepository.deleteById(id);
    }
}
