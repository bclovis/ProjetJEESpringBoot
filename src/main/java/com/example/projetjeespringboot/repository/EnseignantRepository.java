package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spécifie que cette interface est un repository JPA pour l'entité Enseignant
@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, String> {

    // Recherche un enseignant par email
    Enseignant findByEmail(String email);
    Long countByEmail(String email);

    @Query("SELECT e FROM Enseignant e WHERE LOWER(e.email) LIKE %:keyword% OR LOWER(e.nom) LIKE %:keyword% OR LOWER(e.prenom) LIKE %:keyword% OR CAST(e.dateNaissance AS string) LIKE %:keyword%")
    List<Enseignant> findByKeyword(String keyword, Pageable pageable);
    @Query("SELECT COUNT(e) FROM Enseignant e WHERE LOWER(e.email) LIKE %:keyword% OR LOWER(e.nom) LIKE %:keyword% OR LOWER(e.prenom) LIKE %:keyword% OR CAST(e.dateNaissance AS string) LIKE %:keyword%")
    long countByKeyword(String keyword);

}
