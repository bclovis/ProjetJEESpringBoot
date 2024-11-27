package com.example.projetjeespringboot.model;  // Package adapté au contexte Spring Boot

import jakarta.persistence.*;  // On utilise javax.persistence pour JPA standard
import java.util.Date;

@Entity
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
    public Admin() {
    }

    public Admin(String email, String nom, String prenom, Date dateNaissance, String mdp) {
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
}
