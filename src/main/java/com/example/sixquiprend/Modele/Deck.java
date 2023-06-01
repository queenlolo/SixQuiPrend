package com.example.sixquiprend.Modele;
import com.example.sixquiprend.Modele.Cards;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.util.List;

import static com.example.sixquiprend.Modele.Cards.card2;

public class Deck {
    private List<Cards> cards;
    public Deck(List<Cards> cards){
        this.cards = cards;
    }

    public Deck() {
        this.cards = card2();

    }

    // méthode déjà faite par java
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void distribute(List<Player> players) {
        int nbPlayers = players.size();
        int nbCardsPerPlayer = 10;

        for (int i = 0; i < nbPlayers; i++) {
            List<Cards> playerCards = new ArrayList<>();

            for (int j = 0; j < nbCardsPerPlayer; j++) {
                //playerCards.add(cards.remove(0));
            }

            players.get(i).setHand(playerCards);
        }
    }
}
