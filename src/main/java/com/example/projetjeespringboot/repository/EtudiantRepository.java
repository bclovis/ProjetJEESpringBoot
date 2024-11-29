package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Etudiant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    // On peut ajouter ici des méthodes spécifiques si nécessaire, comme la recherche par email ou par rôle.
    Etudiant findByEmail(String email);

    @Query("SELECT e FROM Etudiant e WHERE LOWER(e.email) LIKE %:keyword% OR LOWER(e.nom) LIKE %:keyword% OR LOWER(e.prenom) LIKE %:keyword% OR CAST(e.dateNaissance AS string) LIKE %:keyword% OR LOWER(CAST(e.filiere AS string)) LIKE %:keyword%")
    List<Etudiant> findByKeyword(String keyword, Pageable pageable);

    @Query("SELECT COUNT(e) FROM Etudiant e WHERE LOWER(e.email) LIKE %:keyword% OR LOWER(e.nom) LIKE %:keyword% OR LOWER(e.prenom) LIKE %:keyword% OR CAST(e.dateNaissance AS string) LIKE %:keyword% OR LOWER(CAST(e.filiere AS string)) LIKE %:keyword%")
    long countByKeyword(String keyword);
}
