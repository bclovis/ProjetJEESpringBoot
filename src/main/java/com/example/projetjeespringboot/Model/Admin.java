package com.example.projetjeespringboot.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "admin")  // Nom de la table en minuscule par convention
public class Admin {
    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Temporal(TemporalType.DATE)  // On utilise @Temporal pour bien gérer le type Date sans l'heure
    @Column(name = "date_naissance", nullable = false)  // Conventions snake_case
    private Date dateNaissance;

    @Column(name = "mdp", nullable = false)
    private String mdp;

    // Constructeurs
    public Admin() {}

    public Admin(String email, String nom, String prenom, Date dateNaissance, String mdp) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mdp = mdp;
    }
}

