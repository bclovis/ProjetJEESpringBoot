package com.example.projetjeespringboot.Repository;

import com.example.projetjeespringboot.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {

}
