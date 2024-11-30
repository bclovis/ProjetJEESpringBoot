package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.DemandeFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeFiliereRepository extends JpaRepository<DemandeFiliere, Integer> {

    // Récupérer toutes les demandes d'un étudiant donné
    List<DemandeFiliere> findByEtudiantEmail(String etudiantEmail);

    // Récupérer toutes les demandes en attente pour un étudiant donné
    @Query("SELECT d FROM DemandeFiliere d WHERE d.etudiantEmail = :email AND d.statut = 'EN_ATTENTE'")
    List<DemandeFiliere> findDemandesEnAttenteByEtudiant(@Param("email") String etudiantEmail);



    @Query(value = "SELECT COUNT(d) FROM DemandeFiliere d WHERE LOWER(d.etudiantEmail) LIKE :keyword OR LOWER(d.statut) LIKE :keyword")
    long countByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT d FROM DemandeFiliere d ORDER BY d.dateDemande DESC")
    List<DemandeFiliere> findAllPaged(int offset, int pageSize);

    @Query(value = "SELECT d FROM DemandeFiliere d WHERE " +
            "CAST(d.id AS string) LIKE :keyword OR " +
            "LOWER(d.etudiantEmail) LIKE :keyword OR " +
            "LOWER(CAST(d.filiere AS string)) LIKE :keyword OR " +
            "LOWER(d.statut) LIKE :keyword OR " +
            "CAST(d.dateDemande AS string) LIKE :keyword OR " +
            "LOWER(d.commentaireAdmin) LIKE :keyword")
    List<DemandeFiliere> findByKeyword(@Param("keyword") String keyword, int offset, int pageSize);
}
