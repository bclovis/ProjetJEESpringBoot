package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,String> {

}
