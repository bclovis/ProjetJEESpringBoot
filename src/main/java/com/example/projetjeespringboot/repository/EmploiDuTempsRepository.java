package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Integer> {
    List<EmploiDuTemps> findByFiliereEtudiantEmailAndSemaine(String email, int semaine);
    List<EmploiDuTemps> findByProfesseurEmailAndSemaine(String email, int semaine);
    List<EmploiDuTemps> findByFiliereAndSemaine(String filiere, int semaine);
}
