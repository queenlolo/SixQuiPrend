package com.example.sixquiprend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    private Button btnPlay = new Button("Play");
    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("6 Qui Prend");

        Image image = new Image("file:src/main/java/com/example/sixquiprend/Vue/Image/menu.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(700);

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, btnPlay);

        Scene scene = new Scene(root, 1000, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}