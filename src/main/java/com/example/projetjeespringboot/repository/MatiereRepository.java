package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    // Tu peux ajouter ici des méthodes spécifiques si nécessaire
}
