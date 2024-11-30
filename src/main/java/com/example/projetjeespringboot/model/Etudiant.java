package com.example.projetjeespringboot.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Etudiant")
public class Etudiant {

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "date_naissance", nullable = false)
    private Date dateNaissance;

    @Column(name = "mdp", nullable = false)
    private String mdp;

    @Column(name = "filiere", nullable = true)
    @Enumerated(EnumType.STRING)
    private Filieres filiere; // Colonne avec une énumération des filières

    // Constructeurs

    public Etudiant() {}

    public Etudiant(String email, String nom, String prenom, Date dateNaissance, String mdp, Filieres filiere) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mdp = mdp;
        this.filiere = filiere;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Filieres getFiliere() {
        return filiere;
    }

    public void setFiliere(Filieres filiere) {
        this.filiere = filiere;
    }
}
