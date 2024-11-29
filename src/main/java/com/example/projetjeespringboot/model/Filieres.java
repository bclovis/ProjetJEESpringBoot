package com.example.projetjeespringboot.model;

public enum Filieres {
    INFORMATIQUE,
    MATHEMATIQUES,

    AUCUNE;

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
