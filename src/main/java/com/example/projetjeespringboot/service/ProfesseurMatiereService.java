package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Matiere;
import com.example.projetjeespringboot.model.ProfesseurMatiere;
import com.example.projetjeespringboot.repository.ProfesseurMatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurMatiereService {

    @Autowired
    private ProfesseurMatiereRepository professeurMatiereRepository;

    // Méthode pour vérifier si un professeur enseigne une matière
    public boolean professeurEnseigneMatiere(Enseignant enseignant, Matiere matiere) {
        // Vérifie si l'association existe entre l'enseignant et la matière
        ProfesseurMatiere association = professeurMatiereRepository.findByEnseignantAndMatiere(enseignant, matiere);

        // Si l'association existe, renvoyer true, sinon false
        return association != null;
    }

    // Méthode pour trouver une association entre un professeur et une matière
    public ProfesseurMatiere findAssociation(Enseignant enseignant, Matiere matiere) {
        return professeurMatiereRepository.findByEnseignantAndMatiere(enseignant, matiere);
    }

    // Autres méthodes selon tes besoins
}
