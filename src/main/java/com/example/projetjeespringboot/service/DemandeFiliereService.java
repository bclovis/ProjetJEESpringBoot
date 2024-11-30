package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.DemandeFiliere;
import com.example.projetjeespringboot.model.Filieres;
import com.example.projetjeespringboot.repository.DemandeFiliereRepository;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import com.example.projetjeespringboot.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DemandeFiliereService {

    private final DemandeFiliereRepository demandeFiliereRepository;

    private final EtudiantRepository etudiantRepository;

    private static final int PAGE_SIZE = 20;

    public DemandeFiliereService(DemandeFiliereRepository demandeFiliereRepository, EtudiantRepository etudiantRepository) {
        this.demandeFiliereRepository = demandeFiliereRepository;
        this.etudiantRepository = etudiantRepository;
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

    public List<DemandeFiliere> findDemandes(int page, String keyword) {
        // Utilisation de la pagination et filtre par mot-clé
        if (keyword == null || keyword.trim().isEmpty()) {
            return demandeFiliereRepository.findAllPaged((page - 1) * PAGE_SIZE, PAGE_SIZE);
        } else {
            return demandeFiliereRepository.findByKeyword("%" + keyword.toLowerCase() + "%", (page - 1) * PAGE_SIZE, PAGE_SIZE);
        }
    }



    public int getTotalPages(String keyword) {
        long totalResults = (keyword == null || keyword.trim().isEmpty()) ?
                demandeFiliereRepository.count() : demandeFiliereRepository.countByKeyword("%" + keyword.toLowerCase() + "%");
        return (int) Math.ceil((double) totalResults / PAGE_SIZE);
    }

    public void handleAction(String action, int demandeId, String commentaire) {
        DemandeFiliere demande = demandeFiliereRepository.findById(demandeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid demande ID"));

        switch (action) {
            case "accepter":
                demande.setStatut("ACCEPTEE");
                Etudiant etudiant = etudiantRepository.findByEmail(demande.getEtudiantEmail());
                if (etudiant != null) {
                    etudiant.setFiliere(demande.getFiliere());
                    etudiantRepository.save(etudiant);
                }
                break;
            case "refuser":
                demande.setStatut("REFUSEE");
                break;
            case "supprimer":
                demandeFiliereRepository.delete(demande);
                return;
            case "commenter":
                if (commentaire != null && !commentaire.trim().isEmpty()) {
                    demande.setCommentaireAdmin(commentaire);
                }
                break;
        }
        demandeFiliereRepository.save(demande);
    }
}
