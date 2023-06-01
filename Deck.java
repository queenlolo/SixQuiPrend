package com.example.sixquiprend.Modele;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import static com.example.sixquiprend.Modele.Cards.card2;

public class Deck {
    private List<Cards> cards;

    public Deck(List<Cards> cards){
        this.cards = cards;
    }

    public Deck() {
        this.cards = Cards.card2();
    }

    // Méthode déjà faite par Java
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void distribute(List<Player> players) {
        int nbPlayers = players.size();
        int nbCardsPerPlayer = 10;
        this.cards = Cards.card2();

        for (Player player : players) {
            List<Cards> playerCards = new ArrayList<>();

            for (int j = 0; j < nbCardsPerPlayer; j++) {
                if (!cards.isEmpty()) {
                    playerCards.add(cards.remove(0));
                }
            }
            player.setHand(playerCards);
            player.setNbCards(playerCards.size());
        }
    }
}
