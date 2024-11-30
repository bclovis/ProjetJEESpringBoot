package com.example.projetjeespringboot.model;

import jakarta.persistence.*;  // Utilisation de jakarta.persistence pour Spring Boot 3

@Entity
@Table(name = "ProfesseurMatiere")
public class ProfesseurMatiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relation avec l'entité Enseignant
    @ManyToOne
    @JoinColumn(name = "professeur_email", referencedColumnName = "email", nullable = false)
    private Enseignant enseignant;  // Objet Enseignant lié à cette association

    // Relation avec l'entité Matiere
    @ManyToOne
    @JoinColumn(name = "matiere_id", referencedColumnName = "id", nullable = false)
    private Matiere matiere;  // Objet Matiere lié à cette association

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}
