package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.MatiereFiliere;
import com.example.projetjeespringboot.repository.MatiereFiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereFiliereService {

    @Autowired
    private MatiereFiliereRepository matiereFiliereRepository;

    public List<MatiereFiliere> getAllMatiereFilieres() {
        return matiereFiliereRepository.findAll();
    }

    public MatiereFiliere getMatiereFiliereById(int id) {
        return matiereFiliereRepository.findById(id).orElse(null);
    }

    public MatiereFiliere createOrUpdateMatiereFiliere(MatiereFiliere matiereFiliere) {
        return matiereFiliereRepository.save(matiereFiliere);
    }

    public void deleteMatiereFiliere(int id) {
        matiereFiliereRepository.deleteById(id);
    }
}
