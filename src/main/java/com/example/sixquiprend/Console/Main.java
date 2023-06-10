package com.example.sixquiprend.Console;
import com.example.sixquiprend.Modele.Cards;
import com.example.sixquiprend.Modele.Deck;
import com.example.sixquiprend.Modele.Player;
import com.example.sixquiprend.Modele.GameLogic;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        int numPlayers = 2;
        GameLogic game = new GameLogic(numPlayers);
        game.playGame();
        Cards.card2();
        Cards cards = new Cards(10, 5, "example.png");

    }
}


