package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    // Si besoin, on peut ajouter des méthodes de recherche spécifiques ici
}
