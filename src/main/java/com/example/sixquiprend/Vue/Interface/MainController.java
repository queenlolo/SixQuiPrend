package com.example.sixquiprend.Vue.Interface;

import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MainController {

    private MainStage mainStage;
    public MainController(MainStage mainStage) {
        this.mainStage = mainStage;
    }
    public static void askName(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose your name:");
        dialog.showAndWait();
        String userName = dialog.getResult();
        if (userName == null){
            close();
        }
        if (userName.isEmpty()) {
            askName();
        }
    }

    static void close() {
        Platform.exit();
        System.exit(0);
    }

    static Image cropImage(Image image, int cropLeft, int cropRight) {
        int originalWidth = (int) image.getWidth();
        int originalHeight = (int) image.getHeight();
        int cropX = cropLeft;
        int cropY = 0;
        int cropWidth = originalWidth - cropLeft - cropRight;
        int cropHeight = originalHeight;
        PixelReader reader = image.getPixelReader();
        WritableImage croppedImage = new WritableImage(reader, cropX, cropY, cropWidth, cropHeight);

        return croppedImage;
    }

    public static void changeCardPosition(ImageView imageView) {

        imageView.setOnMouseClicked(event -> {
            double currentTranslateY = imageView.getTranslateY();
            double newTranslateY = currentTranslateY - 30;

            imageView.setTranslateY(newTranslateY);
            imageView.setOnMouseClicked(null);

        });
    }

    public void moveCardPosition(ImageView card, int targetColumn, int targetRow) {
        card.setOnMouseClicked(event -> {
            GridPane gridPane = (GridPane) card.getParent(); // Obtenir le GridPane parent
            int currentColumn = GridPane.getColumnIndex(card); // Obtenir l'index de colonne actuel
            int currentRow = GridPane.getRowIndex(card); // Obtenir l'index de ligne actuel

            // Supprimer la carte de sa position actuelle
            gridPane.getChildren().remove(card);

            // Ajouter la carte à la position cible
            gridPane.add(card, targetColumn, targetRow);

            // Ajuster les propriétés translateX et translateY de la carte pour réinitialiser les transformations précédentes
            card.setTranslateX(0);
            card.setTranslateY(0);


        });
    }




}