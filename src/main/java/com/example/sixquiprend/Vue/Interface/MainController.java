package com.example.sixquiprend.Vue.Interface;

import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;


public class MainController {

    private MainStage mainStage;
    public MainController(MainStage mainStage) {
        this.mainStage = mainStage;
        mainStage.getStylesheets().add("file:src/main/java/com/example/sixquiprend/Vue/Interface/style.css");
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

    public static Image cropImage(Image image, int cropLeft, int cropRight) {
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


}