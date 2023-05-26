package com.example.sixquiprend.Modele;
import java.util.List;
import java.util.ArrayList;


public class Player {
    private String name;
    private int nbCards;
    private int nbBeefs;
    //pas sur de ça, jsp si c'est une liste qu'il faut mettre
    private List<Cards> playCards;

    public Player(String name){
        this.name = name;
        this.nbCards = 0;
        this.nbBeefs = 0;
        this.playCards  =new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public int getNbCards(){
        return nbCards;
    }
    public void setNbCards(int nbCards){
        this.nbCards= nbCards;
    }
    public int getNbBeefs (){
        return nbBeefs;
    }

    public void setNbBeefs (int nbBeefs){
        this.nbBeefs = nbBeefs;
    }
    public List<Cards> getPlayCards(){
        return playCards;
    }
    public void setplayCards (List<Cards> playCards){
        this.playCards = playCards;
    }
}
