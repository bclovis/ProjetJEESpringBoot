package com.example.projetjeespringboot.model;

public enum Filieres {
    MATHEMATIQUES(1),

    INFORMATIQUE(2),

    AUCUNE(3);

    private final int id;

    Filieres(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        switch (this) {
            case INFORMATIQUE:
                return "Informatique";
            case MATHEMATIQUES:
                return "Mathématiques";
            case AUCUNE:
                return "Aucune";
            default:
                return super.toString();
        }
    }
}
