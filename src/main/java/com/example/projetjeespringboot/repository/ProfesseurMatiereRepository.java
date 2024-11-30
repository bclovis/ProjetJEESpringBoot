package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfesseurMatiereRepository extends JpaRepository<ProfesseurMatiere, Integer> {

    // Méthode personnalisée pour rechercher une association par email du professeur et id de la matière
    // Méthode personnalisée pour rechercher une association par email du professeur et id de la matière
    List<ProfesseurMatiere> findByEnseignantEmailAndMatiereId(String professeurEmail, Integer matiereId);

    // Recherche des associations entre professeurs et matières, avec un filtrage par mot-clé (nom du professeur ou de la matière)
    @Query("SELECT pm FROM ProfesseurMatiere pm " +
            "JOIN pm.enseignant e " +
            "JOIN pm.matiere m " +
            "WHERE e.nom LIKE %:keyword% OR e.prenom LIKE %:keyword% OR m.nom LIKE %:keyword%")
    List<ProfesseurMatiere> findAssociations(String keyword);
}
