package com.example.sixquiprend.Modele;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Player winner;
    private int nbPlayers;
    private int score;
    private int numRound;
    public Game(int nbPlayers){
        this.nbPlayers = nbPlayers;
        this.score = 0;
        this.numRound = 0;
    }
    public Player getWinner(){
        return winner;
    }
    public void setWinner( Player winner){
        this.winner=winner;
    }
    public int getNbPlayers(){
        return nbPlayers;
    }
    public void setNbPlayers(int nbPlayers){
        this.nbPlayers = nbPlayers;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score= score;
    }
    public int getNumRound(){
        return numRound;
    }
    public void setNumRound(int numRound){
        this.numRound= numRound;
    }
    public void calculateScore(){
        int totalNbBeefs = 0;
        for (Player player : players) {
            totalNbBeefs += player.getNbBeefs();
        }
        score += totalNbBeefs;
    }


    public void showWinner(){
        if (winner != null) {
            System.out.println("The winner is: " + winner.getName());
        } else {
            System.out.println("No winner.");
        }
    }


    public void showRound(){
        System.out.println("Round : " + numRound);

    }

    public static void Player(){
        Player joueur1 = new Player("Joueur 1");
        Player joueur2 = new Player("Joueur 2");


        // Création d'une liste de joueurs
        List<Player> joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueur2);

        System.out.println(joueur1.getName());
        System.out.println(joueur2.getName());
        }
    }
