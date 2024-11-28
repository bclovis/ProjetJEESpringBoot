package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant,String> {

    Enseignant findByEmail(String email);

    List<Enseignant> findByNom(String nom);
}
