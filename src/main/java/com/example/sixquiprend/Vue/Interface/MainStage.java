package com.example.sixquiprend.Vue.Interface;

import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class MainStage extends StackPane {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private ArrayList<Image> imPlayer;
    private  ArrayList<Image> imAI;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;


    public MainStage(){
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to our 6 qui prend\n\n\n" + "Lorlay, Massil, Lorie");
        alert.showAndWait();
        this.getChildren().addAll();

        MainController.askName();


    }

    void initArrayListsImages()
    {
        this.imPlayer = new ArrayList();
        this.imAI = new ArrayList();
    }

    void init(){
        initArrayListsImages();
        this.image1 = null;
        this.image2 = null;
        this.image3 = null;
        this.image4 = null;

    }



}
