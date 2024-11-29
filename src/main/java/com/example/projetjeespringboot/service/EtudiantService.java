package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
