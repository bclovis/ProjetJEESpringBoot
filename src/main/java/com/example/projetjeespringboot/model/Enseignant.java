package com.example.projetjeespringboot.model; // Change le package à la bonne convention

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDate; // Utilisation de LocalDate au lieu de Date

@Entity
@Table(name = "Enseignant")
public class Enseignant {

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance; // Utilisation de LocalDate pour gérer les dates sans heure

    @Column(name = "mdp", nullable = false)
    private String mdp;

    // Constructeurs
    public Enseignant() {
    }

    public Enseignant(String email, String nom, String prenom, LocalDate dateNaissance, String mdp) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mdp = mdp;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
