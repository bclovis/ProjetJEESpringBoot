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
}