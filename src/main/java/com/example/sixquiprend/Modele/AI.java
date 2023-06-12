package com.example.sixquiprend.Modele;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Random;

public class AI extends Player {
    private List<Player> players;

    public AI(String name, List<Player> players) {
        super(name);
        this.players = players;
    }

    public Cards playRandomCard() {
        Player player2 = players.get(1);
        Cards cardToPlace = null;

        if (!player2.getHand().isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(player2.getHand().size());
            cardToPlace = player2.getHand().remove(randomIndex);

            System.out.println("AI played card: " + cardToPlace);
        } else {
            System.out.println("AI has no more cards to play.");
        }

        return cardToPlace;
    }

    public void play(GridPane gridPane) {
    }

}