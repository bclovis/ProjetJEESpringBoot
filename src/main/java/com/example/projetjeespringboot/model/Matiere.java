package com.example.projetjeespringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matiere") // Correspond exactement à la table "matiere" dans votre base de données
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom", nullable = false, unique = true) // Ajout de "unique = true" pour refléter votre contrainte SQL
    private String nom;

    // Constructeurs
    public Matiere() {
    }

    public Matiere(String nom) {
        this.nom = nom;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode toString pour un affichage lisible
    @Override
    public String toString() {
        return "Matiere{id=" + id + ", nom='" + nom + '\'' + '}';
    }

    public Matiere get() {
        return this;
    }

    public boolean isEmpty() {
        return (nom == null || nom.trim().isEmpty());
    }
}
