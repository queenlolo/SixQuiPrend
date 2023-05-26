package com.example.sixquiprend.Modele;

public class Cards {
    private int value;
    private int nbBeefs;
    public Cards (int value, int nbBeefs){
        this.value = value;
        this.nbBeefs = nbBeefs;
    }
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
    public int getNbBeefs(){
        return nbBeefs;
    }
    public void setNbBeefs(int nbBeefs){
        this.nbBeefs = nbBeefs;
    }
}
