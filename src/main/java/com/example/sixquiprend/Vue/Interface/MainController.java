package com.example.sixquiprend.Vue.Interface;

import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;

public class MainController {
    public MainController(MainStage mainStage) {

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

}
