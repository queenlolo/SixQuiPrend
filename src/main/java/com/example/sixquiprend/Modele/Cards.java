package com.example.sixquiprend.Modele;

public abstract class Cards {
    protected String colour;
    protected String link;
    private int value;
    private int nbBeefs;

    public Cards(int value, int nbBeefs, String couleur, String lien) {
        this.value = value;
        this.nbBeefs = nbBeefs;
        this.colour = couleur;
        this.link = "imgCartes/" + lien;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNbBeefs() {
        return nbBeefs;
    }

    public void setNbBeefs(int nbBeefs) {
        this.nbBeefs = nbBeefs;
    }

    public String getColour() {
        return this.colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getLink() {
        return this.link;
    }

    //Affichage
    public void afficher() {
        System.out.println("Colour: " + this.colour + ", Link: " + this.link + ", Value: " + this.value + "");
    }

}
