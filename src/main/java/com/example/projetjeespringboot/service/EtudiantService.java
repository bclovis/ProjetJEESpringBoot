package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Méthode pour valider la connexion de l'étudiant
    public Etudiant validateLogin(String email, String password) {
        Etudiant etudiant = etudiantRepository.findByEmail(email);
        if (etudiant != null && etudiant.getMdp().equals(password)) {
            return etudiant;  // Retourne l'étudiant si l'email et le mot de passe sont valides
        }
        return null;  // Retourne null si l'authentification échoue
    }

    public List<Etudiant> getEtudiants(int page, int pageSize, String recherche) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        if (recherche != null && !recherche.isEmpty()) {
            List<Etudiant> resultat = etudiantRepository.findByKeyword(recherche.toLowerCase(), pageable);
            return resultat;
        }
        List<Etudiant> resultat = etudiantRepository.findAll(pageable).getContent();
        return resultat;
    }

    public int getTotalPages(int pageSize, String recherche) {
        long totalResults;
        if (recherche != null && !recherche.isEmpty()) {
            totalResults = etudiantRepository.countByKeyword(recherche.toLowerCase());
        } else {
            totalResults = etudiantRepository.count();
        }
        return (int) Math.ceil((double) totalResults / pageSize);
    }
    public Etudiant getEtudiantByEmail(String email) {
        return etudiantRepository.findByEmail(email);
    }

    public boolean modifierEtudiant(Etudiant etudiant) {
        try {
            // Vérifier si l'étudiant existe dans la base de données
            Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiant.getEmail());

            if (optionalEtudiant.isPresent()) {
                // L'étudiant existe, on récupère l'objet
                Etudiant etudiantExistant = optionalEtudiant.get();

                // Mettre à jour les champs de l'étudiant existant avec les nouvelles valeurs
                etudiantExistant.setNom(etudiant.getNom());
                etudiantExistant.setPrenom(etudiant.getPrenom());
                etudiantExistant.setDateNaissance(etudiant.getDateNaissance());
                etudiantExistant.setMdp(etudiant.getMdp());

                // Sauvegarder les modifications
                etudiantRepository.save(etudiantExistant);
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

    public boolean supprimerEtudiant(String email) {
        try {
            if (etudiantRepository.existsById(email)) {
                etudiantRepository.deleteById(email);
                return true;
            }
            return false; // L'étudiant n'existe pas
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Une erreur est survenue
        }
    }


}
