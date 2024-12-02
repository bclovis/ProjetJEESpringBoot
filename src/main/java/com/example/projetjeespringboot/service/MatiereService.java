package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Matiere;
import com.example.projetjeespringboot.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    // Récupérer toutes les matières
    public List<Matiere> findAll() {
        return matiereRepository.findAll();
    }

    // Trouver une matière par son ID
    public Matiere findById(int id) {
        return matiereRepository.findById(id).orElse(null);
    }

    // Sauvegarder une nouvelle matière
    public Matiere save(Matiere matiere) {
        return matiereRepository.save(matiere);
    }
}
