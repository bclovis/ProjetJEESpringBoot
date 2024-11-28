package com.example.projetjeespringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "dateNaissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "mdp", nullable = false)
    private String mdp;

    @Column(name = "filiere", nullable = false)
    private String filiere;

    // Constructeurs
    public Etudiant() {}
    public Etudiant(String email, String nom, String prenom, LocalDate dateNaissance, String mdp) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mdp = mdp;
    }
}
