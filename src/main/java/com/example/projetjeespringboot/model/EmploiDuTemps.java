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
        this.filiere = filiere; // Setter pour l'attribution de la Filière
    }

    public int getSemestre() {
        return semestre;
    }

    public int getId(){
        return id;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Enseignant getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Enseignant professeur) {
        this.professeur = professeur;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getSemaineDebut() {
        return semaineDebut;
    }

    public void setSemaineDebut(int semaineDebut) {
        this.semaineDebut = semaineDebut;
    }

    public int getSemaineFin() {
        return semaineFin;
    }

    public void setSemaineFin(int semaineFin) {
        this.semaineFin = semaineFin;
    }
}
