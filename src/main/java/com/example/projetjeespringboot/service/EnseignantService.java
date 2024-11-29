package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Méthode pour récupérer tous les enseignants
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    // Méthode pour récupérer un enseignant par email
    public Optional<Enseignant> getEnseignantByEmail(String email) {
        return enseignantRepository.findById(email);
    }

    // Méthode pour enregistrer un enseignant
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // Méthode pour supprimer un enseignant par email
    public void deleteEnseignant(String email) {
        enseignantRepository.deleteById(email);
    }
}
