package com.example.sixquiprend.Vue.Interface;

import com.example.sixquiprend.Modele.*;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Flow;

public class MainStage extends StackPane {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    HBox hBox = new HBox();
    HBox hBox2 = new HBox();
    VBox vBox = new VBox();
    private FlowPane cardsContainer;
    private FlowPane cardsContainer2;


    private List<Player> players;
    private int currentPlayerIndex = 0;

    private List<Cards> drawnCards;
    private List<Cards> cardsToPlace;
    private GridPane gridPane;

    public MainStage() {
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to our 6 qui prend\n\n\n" + "Lorlay, Massil, Lorie");
        alert.showAndWait();

        MainController.askName();

        List<Cards> cards = Cards.card2();
        Deck deck = new Deck(cards);

        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        deck.distribute(players);

        cardsContainer = new FlowPane();
        cardsContainer2 = new FlowPane();

        cardsContainer.setPrefWidth(920);
        cardsContainer.setPrefHeight(100);
        cardsContainer.setHgap(0);
        cardsContainer.setVgap(0);
        cardsContainer.setAlignment(Pos.CENTER);

        cardsContainer2.setPrefWidth(920);
        cardsContainer2.setPrefHeight(100);
        cardsContainer2.setHgap(0);
        cardsContainer2.setVgap(0);
        cardsContainer2.setAlignment(Pos.CENTER);

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        drawnCards = deck.drawRandomCards(4);
        cardsToPlace = new ArrayList<>();

        for (int i = 0; i < player1.getHand().size(); i++) {
            Cards card = player1.getHand().get(i);
            Image image = new Image(card.getLink());
            Image newImage = MainController.cropImage(image, 180, 180);
            ImageView imageView = new ImageView(newImage);
            imageView.setFitWidth(90);
            imageView.setFitHeight(140);

            cardsContainer.getChildren().add(imageView);

        }

        for (Cards card : player2.getHand()) {
            Image image = new Image("file:src/main/java/com/example/sixquiprend/Vue/ImageCards/dos.png");
            Image newImage = MainController.cropImage(image, 180, 180);
            ImageView imageView = new ImageView(newImage);
            imageView.setFitWidth(90);
            imageView.setFitHeight(140);

            cardsContainer2.getChildren().add(imageView);
        }

        hBox.getChildren().add(cardsContainer);
        hBox2.getChildren().add(cardsContainer2);

        gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        int numColumns = 5;
        int numRows = 4;

        double cardWidth = 90;
        double cardHeight = 100;

        for (int col = 0; col < numColumns; col++) {
            for (int row = 0; row < numRows; row++) {
                Rectangle card = new Rectangle(cardWidth, cardHeight, Color.TRANSPARENT);
                card.setStroke(Color.TRANSPARENT);
                gridPane.add(card, col, row);
            }
        }

        for (int row = 0; row < numRows; row++) {
            Cards card = drawnCards.get(row);
            Image image = new Image(card.getLink());
            Image newImage = MainController.cropImage(image, 180, 180);
            ImageView imageView = new ImageView(newImage);
            imageView.setFitWidth(90);
            imageView.setFitHeight(140);

            gridPane.add(imageView, 0, row);
        }

        vBox.getChildren().addAll(hBox2, gridPane, hBox);

        this.getChildren().add(vBox);

        nextPlayerChooseCard();
    }

    private void nextPlayerChooseCard() {
        if (currentPlayerIndex < players.size()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            FlowPane currentContainer = currentPlayerIndex == 0 ? cardsContainer : cardsContainer2;

            if (!currentPlayer.getHand().isEmpty()) {
                for (int i = 0; i < currentContainer.getChildren().size(); i++) {
                    ImageView imageView = (ImageView) currentContainer.getChildren().get(i);
                    Cards card = currentPlayer.getHand().get(i);

                imageView.setOnMouseClicked(event -> {
                    try {
                        int cardValue = card.getValue();
                        System.out.println("Value of clicked card: " + cardValue);
                        currentContainer.getChildren().remove(imageView);
                        currentPlayer.getHand().remove(card);
                        cardsToPlace.add(card);
                        currentPlayerIndex++; // Passer au joueur suivant
                        nextPlayerChooseCard(); // Appeler récursivement pour le joueur suivant
                    } catch (Exception exception) {
                        // Gérer les exceptions ici
                    }
                });
            } }else {
                currentPlayerIndex++; // Passer au joueur suivant
                nextPlayerChooseCard();
            }
        } else {
            currentPlayerIndex = 0; // Réinitialiser l'index du joueur courant
            // Tous les joueurs ont choisi une carte
            if (cardsToPlace.size() == players.size()) {
                Collections.sort(cardsToPlace, (card1, card2) -> Integer.compare(card1.getValue(), card2.getValue()));
                // Afficher une pop-up ou effectuer toute autre action nécessaire
                System.out.println("All players have chosen a card.");
                System.out.println("Cards to place: " + cardsToPlace);
                placeCards(gridPane);
                // Tous les joueurs ont choisi une carte

            } else {
                System.out.println("Not all players have chosen a card yet.");
            }
        }
    }
    private void placeCards(GridPane gridPane) {
        int numRows = 4;
        int currentCol = 0;

        for (Cards cardsToPlace : cardsToPlace) {
            int bestCol = -1;
            int differenceMin = Integer.MAX_VALUE;

            for (int row = 0; row < numRows; row++) {
                Cards middleCards = drawnCards.get(row);

                if (cardsToPlace.getValue() > middleCards.getValue()) {
                    for (int col = currentCol; col < gridPane.getColumnConstraints().size(); col++) {
                        boolean colonneVide = true;

                        for (Node node : gridPane.getChildren()) {
                            Integer nodeColonne = GridPane.getColumnIndex(node);
                            Integer nodeLigne = GridPane.getRowIndex(node);

                            if (nodeColonne != null && nodeLigne != null && nodeColonne == col && nodeLigne == row) {
                                colonneVide = false;
                                break;
                            }
                        }

                        if (colonneVide) {
                            int difference = cardsToPlace.getValue() - middleCards.getValue();

                            if (difference < differenceMin) {
                                differenceMin = difference;
                                bestCol = col;
                            }
                        }
                    }
                }
            }

            // Si aucune colonne cible n'a été trouvée, placer la carte dans la colonne courante
            if (bestCol == -1) {
                bestCol = currentCol;
            }

            Image imageCardsToPlace = new Image(cardsToPlace.getLink());
            Image nouvelleImageCarteAPlacer = MainController.cropImage(imageCardsToPlace, 180, 180);
            ImageView imageViewCardsToPlace = new ImageView(nouvelleImageCarteAPlacer);
            imageViewCardsToPlace.setFitWidth(90);
            imageViewCardsToPlace.setFitHeight(140);

            gridPane.add(imageViewCardsToPlace, bestCol+1, numRows - 1);

            // Mettre à jour la colonne courante pour la prochaine carte
            currentCol = bestCol + 1;
        }
    }




}
