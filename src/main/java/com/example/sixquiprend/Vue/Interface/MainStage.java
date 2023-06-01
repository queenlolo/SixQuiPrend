package com.example.sixquiprend.Vue.Interface;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainStage extends StackPane {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public MainStage(){
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to our 6 qui prend\n\n\n" + "Lorlay, Massil, Lorie");
        alert.showAndWait();
        this.getChildren().addAll();

        askName();
    }

    public void askName(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose your name:");
        dialog.showAndWait().ifPresent(userName -> {
            String name = userName;
        });

    }

}
