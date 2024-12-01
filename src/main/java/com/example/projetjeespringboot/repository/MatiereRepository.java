package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Integer> {

    // Trouver une matière par son nom
    Matiere findByNom(String nom);



    // Trouver les matières enseignées par un professeur
    @Query("SELECT m FROM Matiere m JOIN ProfesseurMatiere pm ON m.id = pm.matiere.id WHERE pm.enseignant.email = :emailProf")
    List<Matiere> findByProfesseurEmail(@Param("emailProf") String emailProf);
}
