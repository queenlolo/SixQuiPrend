package com.example.sixquiprend.Modele;

import com.example.sixquiprend.Modele.Board;
import com.example.sixquiprend.Modele.Cards;
import com.example.sixquiprend.Modele.Deck;
import com.example.sixquiprend.Modele.Player;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<Player> players;
    private List<List<Cards>> playerHands;
    private List<List<Cards>> playerPiles;
    private Deck deck;
    private Board board;
    private int score;

    public GameLogic(int numPlayers) {
        players = new ArrayList<>();
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

        while (!isGameOver()) {
            playRound();
            updateScore();
            resetRound();
        }

        displayGameResults();
    }

    private void initializeGame() {
        deck.shuffle();
        dealCards();
        board.reset();
        clearPlayerPiles();
    }

    private void dealCards() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            List<Cards> hand = playerHands.get(i);

            for (int j = 0; j < Player.MAX_HAND_SIZE; j++) {
                Cards card = deck.drawCard();
                hand.add(card);
            }
        }
    }

    private void clearPlayerPiles() {
        for (List<Cards> pile : playerPiles) {
            pile.clear();
        }
    }

    private void playRound() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            List<Cards> hand = playerHands.get(i);
            List<Cards> pile = playerPiles.get(i);

            Cards chosenCard = player.chooseCard(hand);
            board.addCard(chosenCard);
            hand.remove(chosenCard);

            if (board.isFull()) {
                Cards bullCard = board.takeBeefCard();
                pile.add(bullCard);
            }
        }
    }

    private void updateScore() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            List<Cards> pile = playerPiles.get(i);
            int score = calculateScore(pile);
            player.addScore(score);
        }
    }

    private int calculateScore(List<Cards> pile) {
        int totalNbBeefs = 0;
        for (Cards card : pile) {
            totalNbBeefs += card.getNbBeefs();
        }
        score += totalNbBeefs;
        return score;
    }


    private void resetRound() {
        board.reset();
        clearPlayerPiles();
    }

    private boolean isGameOver() {
        for (List<Cards> hand : playerHands) {
            if (!hand.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void displayGameResults() {
        List<Integer> scores = new ArrayList<>();

        for (Player player : players) {
            int score = player.getScore();
            scores.add(score);
            System.out.println("Score du Joueur " + player.getName() + ": " + score);
        }

        System.out.println("Le joueur avec le plus petit score a gagné :");
        int smallestScore = scores.stream().min(Integer::compare).orElse(0);
    }
}
