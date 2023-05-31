package com.example.sixquiprend.Modele;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    protected String link;
    private int value;
    private int nbBeefs;
    public static List<Cards> cards = new ArrayList<>();
    public static List<Cards> allCards = new ArrayList<>();

    public Cards(int value, int nbBeefs, String link) {
        this.value = value;
        this.nbBeefs = nbBeefs;
        this.link = "imgCartes/" + link;
    }

    public static int beef(int numCard, int i) {
        if (numCard % 10 == 5) {
            cards.get(i - 1).nbBeefs += 2;
        }
        if (numCard % 10 == 0) {
            cards.get(i - 1).nbBeefs += 3;
        }
        if (numCard % 11 == 0) {
            cards.get(i - 1).nbBeefs += 5;
        }
        if (numCard % 10 != 5 && numCard % 10 != 0 && numCard % 11 != 0) {
            cards.get(i - 1).nbBeefs += 1;
        }
        return cards.get(i - 1).nbBeefs;
    }

    public static List<Cards> card2() {
        // Initialiser les cartes avec les valeurs de 1 à 104
        for (int i = 1; i <= 104; i++) {
            Cards card = new Cards(0, 0);
            // jsp pq il faut supprimer link pour qu'il n'y est plus d'erreur
            cards.add(card);
            allCards.add(card);
            cards.get(i - 1).value = i;
            allCards.get(i - 1).value = i;
            beef(cards.get(i - 1).value, i);
            beef(allCards.get(i - 1).value, i);
        }

        return cards;
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

    public String getLink() {
        return this.link;
    }

    // Affichage
    public void afficher() {
        System.out.println("Link: " + this.link + ", Value: " + this.value);
    }
}
