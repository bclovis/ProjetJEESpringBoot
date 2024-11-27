package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.MatiereFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereFiliereRepository extends JpaRepository<MatiereFiliere, Integer> {
    // Vous pouvez ajouter des méthodes de requête personnalisées si nécessaire
}
