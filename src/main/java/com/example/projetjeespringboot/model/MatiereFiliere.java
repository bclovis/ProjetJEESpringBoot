package com.example.projetjeespringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Matiere_filiere")
public class MatiereFiliere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    // Constructeur par défaut
    public MatiereFiliere() {}

    // Constructeur avec les objets Matiere et Filiere
    public MatiereFiliere(Matiere matiere, Filiere filiere) {
        this.matiere = matiere;
        this.filiere = filiere;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return "MatiereFiliere [id=" + id + ", matiere=" + matiere.getNom() + ", filiere=" + filiere.getNom() + "]";
    }
}
