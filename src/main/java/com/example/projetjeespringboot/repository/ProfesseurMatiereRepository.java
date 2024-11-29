package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesseurMatiereRepository extends JpaRepository<ProfesseurMatiere, Integer> {

    // Méthode pour obtenir toutes les associations
    List<Object[]> findAllAssociations();

    @Query("SELECT p FROM ProfesseurMatiere p " +
            "JOIN p.enseignant enseignant " +
            "JOIN p.matiere matiere " +
            "WHERE enseignant.nom LIKE %:keyword% " +
            "OR enseignant.prenom LIKE %:keyword% " +
            "OR matiere.nom LIKE %:keyword%")
    List<ProfesseurMatiere> findAssociationsWithKeyword(String keyword);

    // Recherche d'une association spécifique basée sur l'email du professeur et l'ID de la matière
    List<ProfesseurMatiere> findByProfesseurEmailAndMatiereId(String professeurEmail, int matiereId);

    // Recherche d'associations avec des mots-clés dans les noms du professeur ou de la matière
    List<ProfesseurMatiere> findByProfesseurNomContainingOrProfesseurPrenomContainingOrMatiereNomContaining(String keyword, String keyword2, String keyword3);
}
