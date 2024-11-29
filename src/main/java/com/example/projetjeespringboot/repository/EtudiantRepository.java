package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    // On peut ajouter ici des méthodes spécifiques si nécessaire, comme la recherche par email ou par rôle.
    Etudiant findByEmail(String email);
    Long countByEmail(String email);
}
