package com.example.projetjeespringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Emploi_du_temps")
public class EmploiDuTemps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String jour;
    private String heure;

    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "professeur_email", nullable = false)
    private Enseignant professeur;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    private int semestre;
    private int semaineDebut;
    private int semaineFin;

    public String getJour(){
        return jour;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public String getHeure() {
        return heure;
    }

    public Enseignant getProfesseur(){
        return professeur;
    }
}
