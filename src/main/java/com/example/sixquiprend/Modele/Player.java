package com.example.sixquiprend.Modele;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int nbCards;
    private int nbBeefs;
    private List<Cards> playCards;
    private List<Cards> hand;

    public Player(String name) {
        this.name = name;
        this.nbCards = 0;
        this.nbBeefs = 0;
        this.playCards = new ArrayList<>();
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getNbCards() {
        return nbCards;
    }

    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
    }

    public int getNbBeefs() {
        return nbBeefs;
    }

    public void setNbBeefs(int nbBeefs) {
        this.nbBeefs = nbBeefs;
    }

    public List<Cards> getPlayCards() {
        return playCards;
    }

    public void setPlayCards(List<Cards> playCards) {
        this.playCards = playCards;
    }

    public Cards chooseCard() {
        // Logique pour choisir une carte à jouer
        // Retourne la carte choisie
        return null;
    }

    public void removeFromHand(Cards chosenCard) {
        playCards.remove(chosenCard);
    }

    public void setHand(List<Cards> playerCards) {
        this.hand = hand;
    }
    public Object getHand() {
        return hand;
    }
}
