package com.example.sixquiprend.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLogic {
    public List<Player> players;
    public List<List<Cards>> playerHands;
    public List<List<Cards>> playerPiles;
    public Deck deck;
    public Board board;
    public int score;

    public GameLogic(int numPlayers) {
        players = new ArrayList<>();
        //peut etre ajouter dans player
        playerHands = new ArrayList<>();
        playerPiles = new ArrayList<>();
        deck = new Deck();
        board = new Board();
        this.score = 0;

        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player("Player " + (i + 1));
            players.add(player);
            playerHands.add(new ArrayList<>());
            playerPiles.add(new ArrayList<>());
        }
    }

    public void playGame() {
        initializeGame();
        for (int round = 1; round <= 5; round++) {
            System.out.println("=== Round " + round + " ===");
            playRound();
            updateScore();
        }
        resetRound();

        displayGameResults();
    }

    public void initializeGame() {
        deck.shuffle();
        deck.distribute(players);
        board.reset();
        clearPlayerPiles();
    }

    public void clearPlayerPiles() {
        for (List<Cards> pile : playerPiles) {
            pile.clear();
        }
    }

    public void playRound() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            List<Cards> hand = playerHands.get(i);
            List<Cards> pile = playerPiles.get(i);

            System.out.println("Current board: " + board);

            System.out.println("Player " + player.getName() + ", choose a card:");
            //player.displayPlayerHand();

            Cards chosenCard = player.chooseCard(hand);
            board.addCard(chosenCard);


            if (board.isFull()) {
                Cards beefCard = board.takeBeefCard();
                pile.add(beefCard);
                System.out.println("Beef card taken: " + beefCard);
            }
        }
        updateScore();
    }
    public int calculateScore(List<Cards> pile) {
        int totalNbBeefs = 0;
        for (Cards card : pile) {
            totalNbBeefs += card.getNbBeefs();
        }
        score += totalNbBeefs;
        return totalNbBeefs;
    }

    public void updateScore() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            List<Cards> pile = playerPiles.get(i);
            int playerScore = calculateScore(pile);
            score += playerScore;
            player.addScore(playerScore);
        }
    }



    public void resetRound() {
        board.reset();
        clearPlayerPiles();
    }

    private boolean isGameOver() {
        for (List<Cards> hand : playerHands) {
            if (!hand.isEmpty()) {
                return false;
            }
        }

        for (List<Cards> pile : playerPiles) {
            if (!pile.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public void displayGameResults() {
        for (Player player : players) {
            System.out.println("Score of " + player.getName() + ": " + player.getScore());
        }
    }
}
