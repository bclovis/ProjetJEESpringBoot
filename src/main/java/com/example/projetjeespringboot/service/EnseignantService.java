package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Méthode pour valider l'authentification d'un enseignant
    public Enseignant validateLogin(String email, String password) {
        // Cherche un enseignant par son email
        Enseignant enseignant = enseignantRepository.findByEmail(email);

        // Si un enseignant est trouvé et que les mots de passe correspondent
        if (enseignant != null && enseignant.getMdp().equals(password)) {
            return enseignant;  // Retourner l'enseignant si la validation réussit
        }

        return null;  // Retourner null si les informations sont incorrectes
    }

    public List<Enseignant> getEnseignants(int page, int pageSize, String recherche) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        if (recherche != null && !recherche.isEmpty()) {
            List<Enseignant> resultat = enseignantRepository.findByKeyword(recherche.toLowerCase(), pageable);
            return resultat;
        }
        List<Enseignant> resultat = enseignantRepository.findAll(pageable).getContent();
        return resultat;
    }

    public int getTotalPages(int pageSize, String recherche) {
        long totalResults;
        if (recherche != null && !recherche.isEmpty()) {
            totalResults = enseignantRepository.countByKeyword(recherche.toLowerCase());
        } else {
            totalResults = enseignantRepository.count();
        }
        return (int) Math.ceil((double) totalResults / pageSize);
    }

    // Méthode pour récupérer un enseignant par email
    public Enseignant getEnseignantByEmail(String email) {
        return enseignantRepository.findByEmail(email);
    }

    public boolean modifierEnseignant(Enseignant enseignant) {
        try {
            // Vérifier si l'étudiant existe dans la base de données
            Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(enseignant.getEmail());

            if (optionalEnseignant.isPresent()) {
                // L'étudiant existe, on récupère l'objet
                Enseignant enseignantExistant = optionalEnseignant.get();

                // Mettre à jour les champs de l'étudiant existant avec les nouvelles valeurs
                enseignantExistant.setNom(enseignant.getNom());
                enseignantExistant.setPrenom(enseignant.getPrenom());
                enseignantExistant.setDateNaissance(enseignant.getDateNaissance());
                enseignantExistant.setMdp(enseignant.getMdp());

                // Sauvegarder les modifications
                enseignantRepository.save(enseignantExistant);
                return true;
            } else {
                // Si l'étudiant n'existe pas, retourner false
                return false;
            }
        } catch (Exception e) {
            // Gestion des erreurs
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerEnseignant(String email) {
        try {
            if (enseignantRepository.existsById(email)) {
                enseignantRepository.deleteById(email);
                return true;
            }
            return false; // L'étudiant n'existe pas
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Une erreur est survenue
        }
    }

    // Méthode pour enregistrer un enseignant
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // Méthode pour supprimer un enseignant par email
    public void deleteEnseignant(String email) {
        enseignantRepository.deleteById(email);
    }

    // Méthode pour récupérer tous les enseignants
    public List<Enseignant> findAll() {
        return enseignantRepository.findAll();
    }

    // Méthode pour trouver un enseignant par son email
    public Enseignant findByEmail(String email) {
        return enseignantRepository.findByEmail(email);
    }
}