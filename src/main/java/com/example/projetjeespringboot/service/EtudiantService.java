package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
