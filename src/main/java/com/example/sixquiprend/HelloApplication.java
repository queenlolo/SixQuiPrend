package com.example.sixquiprend;

import com.example.sixquiprend.Modele.Player;
import com.example.sixquiprend.Modele.Deck;
import com.example.sixquiprend.Modele.GameLogic;
import com.example.sixquiprend.Vue.Interface.MainController;
import com.example.sixquiprend.Vue.Interface.MainStage;
import com.example.sixquiprend.Vue.Interface.PrimaryController;
import com.example.sixquiprend.Vue.Interface.PrimaryStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    Stage stage;
    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        showPrimaryStage();
    }

    public void showPrimaryStage(){
        PrimaryStage primaryStage = new PrimaryStage();
        PrimaryController primaryController = new PrimaryController(primaryStage, this);

        Scene scene = new Scene(primaryStage, 920, 700);
        stage.setTitle("6 Qui Prend");
        stage.setScene(scene);
        stage.show();
    }

    public void showMainStage(){
        MainStage mainStage = new MainStage();
        MainController mainController = new MainController(mainStage);

        Scene scene = new Scene(mainStage, 920, 700);
        stage.setTitle("6 Qui Prend");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}