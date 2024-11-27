package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Ajoutez des méthodes de recherche personnalisées si nécessaire
}
