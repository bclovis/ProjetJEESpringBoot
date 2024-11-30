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
}
