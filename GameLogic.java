package com.example.sixquiprend.Modele;
import java.util.List;

public class GameLogic {
    private List<Player> players;
    private Deck deck;
    private Row row;
    public GameLogic(List<Player> players) {
        this.players = players;
        deck = new Deck();
        row = new Row();
    }

    public GameLogic() {

    }

    public void initialize() {
        deck.shuffle();
        deck.distribute(players);
    }
    public void play() {
        for (Player player : players) {
            //joueur choisit carte de sa main
            Cards chosenCard = player.chooseCard();
            //carte ajouter à la ligne
            row.addCard(chosenCard);
            //carte n'est plus disponible dans sa main
            player.removeFromHand(chosenCard);
        }
        //met cartes dans l'ordre
        row.sortRow();
        int nbBeefs = row.calculateNbBeefs();

        System.out.println("The number of Beef heads is : " + nbBeefs);

        row.clearRow();
    }
    public boolean isGameOver() {
        return false;
    }
    public boolean Score(){

        return false;
    }
}
