package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant saveEtudiant(Etudiant etudiant)
    {
        return etudiantRepository.save(etudiant);
    }

    /*
    public Etudiant updateEtudiant(Etudiant etudiant, String etudiantId)
    {
        //
    }
    */

    public void deleteEtudiantById(String etudiantId)
    {
        etudiantRepository.deleteById(etudiantId);
    }
}
