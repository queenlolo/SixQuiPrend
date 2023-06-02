package com.example.sixquiprend.Vue.Interface;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PrimaryStage extends StackPane {
    Button btnPlay = new Button("START");
    Image image = new Image("file:src/main/java/com/example/sixquiprend/Vue/Image/menu.jpg");
    ImageView imageView = new ImageView(image);

    public PrimaryStage(){
        imageView.setFitWidth(920);
        imageView.setFitHeight(700);
        btnPlay.getStyleClass().add("btnPlay");

        this.getChildren().addAll(imageView, btnPlay);
    }
}
