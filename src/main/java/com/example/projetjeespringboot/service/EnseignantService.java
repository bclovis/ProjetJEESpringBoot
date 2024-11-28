package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnseignantService {
    @Autowired
    private EnseignantRepository enseignantRepository;

    public Enseignant saveEnseignant(Enseignant enseignant)
    {
        return enseignantRepository.save(enseignant);
    }

    /*
    public Enseignant updateEnseignant(Enseigantn enseignant, String enseignantId)
    {
        //
    }
    */

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

    public void deleteEnseignantById(String enseignantId)
    {
        enseignantRepository.deleteById(enseignantId);
    }
}
