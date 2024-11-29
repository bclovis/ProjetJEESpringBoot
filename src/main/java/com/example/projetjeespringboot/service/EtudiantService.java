package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant validateLogin(String email, String password) {
        // Cherche un enseignant par son email
        Etudiant etudiant = etudiantRepository.findByEmail(email);

        // Si un enseignant est trouvé et que les mots de passe correspondent
        if (etudiant != null && etudiant.getMdp().equals(password)) {
            return etudiant;  // Retourner l'enseignant si la validation réussit
        }

        return null;  // Retourner null si les informations sont incorrectes
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
}
