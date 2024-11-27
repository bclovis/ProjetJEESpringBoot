package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.DemandeFiliere;
import com.example.projetjeespringboot.repository.DemandeFiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeFiliereService {

    @Autowired
    private DemandeFiliereRepository demandeFiliereRepository;

    public DemandeFiliere saveDemandeFiliere(DemandeFiliere demandeFiliere) {
        return demandeFiliereRepository.save(demandeFiliere);
    }

    public Optional<DemandeFiliere> getDemandeFiliereById(int id) {
        return demandeFiliereRepository.findById(id);
    }

    public List<DemandeFiliere> getAllDemandesFiliere() {
        return demandeFiliereRepository.findAll();
    }

    public void deleteDemandeFiliereById(int id) {
        demandeFiliereRepository.deleteById(id);
    }
}
