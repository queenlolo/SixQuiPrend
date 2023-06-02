package com.example.sixquiprend.Vue.Interface;

import com.example.sixquiprend.Modele.*;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;

public class MainStage extends StackPane {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    HBox hBox = new HBox();
    HBox hBox2 = new HBox();
    VBox vBox = new VBox();

    public MainStage(){
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to our 6 qui prend\n\n\n" + "Lorlay, Massil, Lorie");
        alert.showAndWait();

        MainController.askName();

        vBox.setSpacing(530);

        List<Cards> cards = Cards.card2();
        Deck deck = new Deck(cards);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        deck.distribute(players);

        FlowPane cardsContainer = new FlowPane();
        FlowPane cardsContainer2 = new FlowPane();

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

        for (Cards card : player1.getHand()) {
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
        vBox.getChildren().addAll(hBox2, hBox);

        this.getChildren().add(vBox);

    }
}
