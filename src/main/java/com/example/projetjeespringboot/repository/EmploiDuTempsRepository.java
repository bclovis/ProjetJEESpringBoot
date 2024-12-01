package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Integer> {

    List<EmploiDuTemps> findByFiliereNomAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
            String filiere, int semaineDebut, int semaineFin
    );


    List<EmploiDuTemps> findByProfesseurEmailAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(String email, int semaineDebut, int semaineFin);

    // Méthode pour vérifier si le professeur a déjà un cours sur ce créneau (jour, heure, professeur)
    long countByJourAndHeureAndProfesseurEmail(String jour, String heure, String professeurEmail);

    // Méthode pour vérifier si un emploi du temps existe déjà sur ce créneau (jour, heure, filière, semestre, semaine)
    long countByJourAndHeureAndFiliereNomAndSemestreAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
            String jour,
            String heure,
            String filiereNom,
            int semestre,
            int semaineDebut,
            int semaineFin);

    // Vérification des conflits avec le professeur
    long countByJourAndHeureAndProfesseurEmailAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqualAndIdNot(
            String jour, String heure, String professeurEmail, int semaine, int semaineFin, int id);

    // Vérification des conflits avec la filière
    long countByJourAndHeureAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqualAndFiliereIdAndIdNot(
            String jour, String heure, int semaine, int semaineFin, int filiereId, int id);
}
