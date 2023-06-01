package com.example.sixquiprend.Console;
import com.example.sixquiprend.Modele.Cards;
import com.example.sixquiprend.Modele.Deck;
import com.example.sixquiprend.Modele.GameLogic;
import com.example.sixquiprend.Modele.Player;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Player joueur1 = new Player("Joueur 1");
        Player joueur2 = new Player("Joueur 2");

        // Création d'une liste de joueurs
        List<Player> joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueur2);

        // Création du deck
        Deck deck = new Deck();
        deck.shuffle();
        deck.distribute(joueurs);

        // Choix de carte pour chaque joueur
        Cards carteJoueur1 = joueur1.chooseCard();
        Cards carteJoueur2 = joueur2.chooseCard();

        // Affichage des cartes choisies
        System.out.println("Carte choisie par " + joueur1.getName() + ": " + carteJoueur1);
        System.out.println("Carte choisie par " + joueur2.getName() + ": " + carteJoueur2);
    }
}

