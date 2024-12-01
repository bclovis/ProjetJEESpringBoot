package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AfficherEDTEtuEnsRepository extends JpaRepository<EmploiDuTemps, Integer> {
    // Méthode pour récupérer l'emploi du temps pour un étudiant ou un enseignant
    List<EmploiDuTemps> findByFiliereNomAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
            String filiere, int semaineDebut, int semaineFin
    );


    List<EmploiDuTemps> findByProfesseurEmailAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(String email, int semaineDebut, int semaineFin);
}
