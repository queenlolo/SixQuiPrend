package com.example.sixquiprend.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Player {
    public static final int MAX_HAND_SIZE = 10;
    private String name;
    private int nbCards;
    private int nbBeefs;
    private int score;
    private List<Cards> hand;

    public Player(String name) {
        this.name = name;
        this.nbCards = 0;
        this.nbBeefs = 0;
        this.score = 0;
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

    public Cards chooseCard(List<Cards> hand) {
        if (this.hand.isEmpty()) {
            return null;
        }

        // Affiche hand
        System.out.println("Hand of player" + name + ":");
        for (int i = 0; i < this.hand.size(); i++) {
            System.out.println((i + 1) + ". " + this.hand.get(i));
        }

        //scanner permet de lire ce qu'écrit le joueur dans la console
        int chosen = -1;
        Scanner scanner = new Scanner(System.in);
        while (chosen < 1 || chosen > this.hand.size()) {
            System.out.print("Choose a card (1-" + this.hand.size() + "): ");
            chosen = scanner.nextInt();
        }

        Cards chosenCard = this.hand.get(chosen- 1);
        this.hand.remove(chosen - 1);
        nbCards--;

        // Retourne carte choisie
        return chosenCard;
    }


    public void removeFromHand(Cards card) {
        hand.remove(card);
    }
    public void reset() {
        hand.clear();
    }

    public void setHand(List<Cards> hand) {
        this.hand = hand;
    }

    public List<Cards> getHand() {
        return hand;
    }

    public void addScore(int score) {
        this.score += score;
    }
    public int getScore() {
        return score;
    }

}
