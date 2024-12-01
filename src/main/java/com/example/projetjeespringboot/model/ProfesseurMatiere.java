package com.example.projetjeespringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professeur_matiere")  // Vous pouvez également adapter le nom de la table si nécessaire
public class ProfesseurMatiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relation avec l'entité Enseignant
    @ManyToOne
    @JoinColumn(name = "professeur_email", referencedColumnName = "email", nullable = false)
    private Enseignant enseignant;  // Relation avec l'entité Enseignant

    @ManyToOne
    @JoinColumn(name = "matiere_id", referencedColumnName = "id", nullable = false)
    private Matiere matiere;  // Vous devrez avoir une entité Matiere qui est reliée à la table Matiere

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
