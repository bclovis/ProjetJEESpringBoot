package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spécifie que cette interface est un repository JPA pour l'entité Enseignant
@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, String> {

    // Recherche un enseignant par email
    Enseignant findByEmail(String email);
    Long countByEmail(String email);

}
