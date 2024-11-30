package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesseurMatiereRepository extends JpaRepository<ProfesseurMatiere, Integer> {

    @Query("SELECT p FROM ProfesseurMatiere p " +
            "JOIN p.enseignant enseignant " +
            "JOIN p.matiere matiere " +
            "WHERE enseignant.nom LIKE %:keyword% " +
            "OR enseignant.prenom LIKE %:keyword% " +
            "OR matiere.nom LIKE %:keyword%")
    List<ProfesseurMatiere> findAssociationsWithKeyword(String keyword);
}
