package com.example.sixquiprend.Console;
import com.example.sixquiprend.Modele.Cards;
import com.example.sixquiprend.Modele.Deck;
import com.example.sixquiprend.Modele.GameLogic;
import com.example.sixquiprend.Modele.Player;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Création du paquet de cartes
        List<Cards> cards = new ArrayList<>();
        // Ajoutez ici les cartes au paquet

        // Création de l'instance de Deck
        Deck deck = new Deck(cards);

        // Création des joueurs
        List<Player> players = new ArrayList<>();
        players.add(new Player("Joueur 1"));
        players.add(new Player("Joueur 2"));

        // Mélange des cartes
        deck.shuffle();

        // Distribution des cartes aux joueurs
        deck.distribute(players);

        // Affichage de la main de chaque joueur
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getHand());
        }
    }

}

