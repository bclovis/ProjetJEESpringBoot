package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import com.example.projetjeespringboot.repository.ProfesseurMatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurMatiereService {

    @Autowired
    private ProfesseurMatiereRepository professeurMatiereRepository;

    public List<ProfesseurMatiere> getAllProfesseurMatieres() {
        return professeurMatiereRepository.findAll();
    }

    public Optional<ProfesseurMatiere> getProfesseurMatiereById(int id) {
        return professeurMatiereRepository.findById(id);
    }

    public ProfesseurMatiere saveProfesseurMatiere(ProfesseurMatiere professeurMatiere) {
        return professeurMatiereRepository.save(professeurMatiere);
    }

    public void deleteProfesseurMatiereById(int id) {
        professeurMatiereRepository.deleteById(id);
    }
}
