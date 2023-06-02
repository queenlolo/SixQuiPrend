package com.example.sixquiprend.Modele;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    protected String link;
    private int value;
    private int nbBeefs;
    public static List<Cards> cards = new ArrayList<>();
    public static List<Cards> allCards = new ArrayList<>();
    private List<Integer> card;

    public Cards(int value, int nbBeefs, String link) {
        this.value = value;
        this.nbBeefs = nbBeefs;
        this.link = "file:src/main/java/com/example/sixquiprend/Vue/ImageCards/" + link;
        card = new ArrayList<>();
        // Ajouter des éléments à la liste cards
        card.add(1);
        card.add(2);
        card.add(3);
        // ...
    }
    public List<Integer> getCard() {
        return card;
    }

    public void setCard(List<Integer> card) {
        this.card = card;
    }

    public static int beef(int numCard, int i) {
        if (numCard % 10 == 5) {
            cards.get(i - 1).nbBeefs = 2;
        }
        if (numCard % 10 == 0) {
            cards.get(i - 1).nbBeefs = 3;
        }
        if (numCard % 11 == 0) {
            cards.get(i - 1).nbBeefs = 5;
        }
        if (numCard % 10 != 5 && numCard % 10 != 0 && numCard % 11 != 0) {
            cards.get(i - 1).nbBeefs = 1;
        }
        if (numCard == 55){
            cards.get(i - 1).nbBeefs = 7;
        }
        return cards.get(i - 1).nbBeefs;
    }

    public static List<Cards> card2() {
        List<Cards> card2 = new ArrayList<>();
        // Initialiser les cartes avec les valeurs de 1 à 104
        for (int i = 1; i <= 104; i++) {
    Cards card = new Cards(i, 0, i + ".png");
    card2.add(card);
    cards.add(card);
    allCards.add(card);
    card.setNbBeefs(beef(card.getValue(), i));
    }

        return card2;
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
        return link;
    }

    // Affichage
    public void afficher() {
        System.out.println("Link: " + this.link + ", Value: " + this.value);
    }
}
