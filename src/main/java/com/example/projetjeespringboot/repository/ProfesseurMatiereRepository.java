package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.ProfesseurMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurMatiereRepository extends JpaRepository<ProfesseurMatiere, Integer> {
    // Ajoutez des méthodes de recherche personnalisées si nécessaire
}
