package com.example.projetjeespringboot.Service;

import com.example.projetjeespringboot.Model.Etudiant;
import com.example.projetjeespringboot.Repository.EtudiantRepository;
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
