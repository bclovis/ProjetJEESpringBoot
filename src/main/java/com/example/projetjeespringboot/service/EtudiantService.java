package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Méthode pour valider la connexion de l'étudiant
    public Etudiant validateLogin(String email, String password) {
        // Cherche un enseignant par son email
        Etudiant etudiant = etudiantRepository.findByEmail(email);

        // Si un enseignant est trouvé et que les mots de passe correspondent
        if (etudiant != null && etudiant.getMdp().equals(password)) {
            return etudiant;  // Retourner l'enseignant si la validation réussit
        }
        return null;  // Retourne null si l'authentification échoue
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantByEmail(String email) {
        return etudiantRepository.findById(email);
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(String email) {
        etudiantRepository.deleteById(email);
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
}
