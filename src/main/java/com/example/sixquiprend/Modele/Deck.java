package com.example.sixquiprend.Modele;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import static com.example.sixquiprend.Modele.Cards.card2;

public class Deck {
    private List<Cards> cards;
    //supp
    private List<Cards> card2;


    public Deck(List<Cards> cards){
        this.cards = cards;
    }

    public Deck() {
        //2 lignes a supp
        this.cards = Cards.cards;
        this.card2 = Cards.card2();
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
                    shuffle();
                    playerCards.add(cards.remove(0));
                }
            }
            player.setHand(playerCards);
            player.setNbCards(playerCards.size());
        }
    }
    //distribue une carte du paquet au joueur, comme la pioche et l'enlève bien du paquet
    public Cards drawCard() {
        if (cards.isEmpty()) {
            return null;
        }

        Cards drawnCard = cards.remove(0);
        return drawnCard;
    }
}
