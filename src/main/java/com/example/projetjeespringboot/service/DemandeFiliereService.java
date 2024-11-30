package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.DemandeFiliere;
import com.example.projetjeespringboot.model.Filieres;
import com.example.projetjeespringboot.repository.DemandeFiliereRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DemandeFiliereService {

    private final DemandeFiliereRepository demandeFiliereRepository;

    public DemandeFiliereService(DemandeFiliereRepository demandeFiliereRepository) {
        this.demandeFiliereRepository = demandeFiliereRepository;
    }

    public void createDemandeFiliere(String etudiantEmail, String filiereStr) {
        Filieres filiere;

        try {
            filiere = Filieres.valueOf(filiereStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Filière invalide.");
        }

        // Créer une nouvelle demande de filière sans vérifier les demandes précédentes
        DemandeFiliere demande = new DemandeFiliere();
        demande.setEtudiantEmail(etudiantEmail);
        demande.setFiliere(filiere);
        demande.setStatut("EN_ATTENTE");
        demande.setDateDemande(new Date());
        demande.setCommentaireAdmin("En attente de traitement");

        demandeFiliereRepository.save(demande);
    }
}