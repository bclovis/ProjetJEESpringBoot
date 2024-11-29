package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.DemandeFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeFiliereRepository extends JpaRepository<DemandeFiliere, Integer> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici si nécessaire
}
