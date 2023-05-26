package com.example.sixquiprend.Modele;

public class Game {
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

    }
    public void showWinner(){

    }
    public void showRound(){

    }
}
