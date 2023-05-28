package com.example.sixquiprend.Console;
import com.example.sixquiprend.Modele.GameLogic;
public class Main {
    GameLogic game;
    Display display;

    public Main(){
        this.game = new GameLogic();
        this.display= new Display();
    }
    public int play(){
        while(!game.isGameOver()){

        }
    }
}

