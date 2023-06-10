package com.example.sixquiprend.Vue.Interface;

import com.example.sixquiprend.HelloApplication;
import javafx.event.ActionEvent;

public class PrimaryController {
    HelloApplication helloApplication;
    private PrimaryStage primaryStage;

    public PrimaryController(PrimaryStage primaryStage, HelloApplication helloApplication){
        this.helloApplication = helloApplication;
        this.primaryStage = primaryStage;
        primaryStage.getStylesheets().add("file:src/main/java/com/example/sixquiprend/Vue/Interface/style.css");
        primaryStage.btnPlay.setOnAction(this::changeScene);
    }

    public void changeScene(ActionEvent e) {
        helloApplication.showMainStage();
    }

}
