package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Etudiant;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {

    // Trouver un étudiant par email
    Etudiant findByEmail(String email);

    // Compter le nombre d'étudiants avec un email donné
    Long countByEmail(String email);

    // Recherche par mot-clé (email, nom, prénom, dateNaissance ou filière)
    @Query("""
        SELECT e FROM Etudiant e
        WHERE LOWER(e.email) LIKE %:keyword%
        OR LOWER(e.nom) LIKE %:keyword%
        OR LOWER(e.prenom) LIKE %:keyword%
        OR CAST(e.dateNaissance AS string) LIKE %:keyword%
        OR LOWER(CAST(e.filiere AS string)) LIKE %:keyword%
    """)
    List<Etudiant> findByKeyword(String keyword, Pageable pageable);

    // Compter le nombre d'étudiants correspondant à un mot-clé
    @Query("""
        SELECT COUNT(e) FROM Etudiant e
        WHERE LOWER(e.email) LIKE %:keyword%
        OR LOWER(e.nom) LIKE %:keyword%
        OR LOWER(e.prenom) LIKE %:keyword%
        OR CAST(e.dateNaissance AS string) LIKE %:keyword%
        OR LOWER(CAST(e.filiere AS string)) LIKE %:keyword%
    """)
    long countByKeyword(String keyword);

}
