package com.example.sixquiprend.Vue.Interface;

import com.example.sixquiprend.Modele.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainStage extends StackPane {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    HBox hBox = new HBox();
    HBox hBox2 = new HBox();
    VBox vBox = new VBox();
    private FlowPane cardsContainer;
    private FlowPane cardsContainer2;

    private List<Player> players;
    private int currentPlayerIndex = 0;

    private List<Cards> drawnCards;
    private List<Cards> cardsToPlace;
    private GridPane gridPane;
    private List<Pile> piles = new ArrayList<>();
    private int currentPileIndex = 0;

    public MainStage() {
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to our 6 qui prend\n\n\n" + "Lorlay, Lorie");
        alert.showAndWait();

        MainController.askName();

        List<Cards> cards = Cards.card2();
        Deck deck = new Deck(cards);

        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new AI("AI", players));

        deck.distribute(players);

        cardsContainer = new FlowPane();
        cardsContainer2 = new FlowPane();

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

        drawnCards = deck.drawRandomCards(4);
        cardsToPlace = new ArrayList<>();

        for (int i = 0; i < player1.getHand().size(); i++) {
            Cards card = player1.getHand().get(i);
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

        gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        int numColumns = 5;
        int numRows = 4;

        double cardWidth = 90;
        double cardHeight = 100;

        for (int col = 0; col < numColumns; col++) {
            for (int row = 0; row < numRows; row++) {
                Rectangle card = new Rectangle(cardWidth, cardHeight, Color.TRANSPARENT);
                card.setStroke(Color.TRANSPARENT);
                gridPane.add(card, col, row);
            }
        }

        for (int row = 0; row < numRows; row++) {
            Cards card = drawnCards.get(row);
            Image image = new Image(card.getLink());
            Image newImage = MainController.cropImage(image, 180, 180);
            ImageView imageView = new ImageView(newImage);
            imageView.setFitWidth(90);
            imageView.setFitHeight(140);

            gridPane.add(imageView, 0, row);
        }

        vBox.getChildren().addAll(hBox2, gridPane, hBox);

        this.getChildren().add(vBox);

        nextPlayerChooseCard();
    }

    private void nextPlayerChooseCard() {
        if (currentPlayerIndex < players.size()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            FlowPane currentContainer = currentPlayerIndex == 0 ? cardsContainer : cardsContainer2;

            if (!currentPlayer.getHand().isEmpty()) {
                if (currentPlayer instanceof AI) {
                    AI aiPlayer = (AI) currentPlayer;
                    Cards selectedCard = aiPlayer.playRandomCard();
                    cardsToPlace.add(selectedCard);
                    currentPlayerIndex++;
                    nextPlayerChooseCard();
                } else {
                    for (int i = 0; i < currentContainer.getChildren().size(); i++) {
                        ImageView imageView = (ImageView) currentContainer.getChildren().get(i);
                        Cards card = currentPlayer.getHand().get(i);

                        imageView.setOnMouseClicked(event -> {
                            if (event.getButton() == MouseButton.PRIMARY) {
                                if (imageView.getTranslateY() == 0) {
                                    double currentTranslateY = imageView.getTranslateY();
                                    double newTranslateY = currentTranslateY - 30;
                                    imageView.setTranslateY(newTranslateY);
                                } else {
                                    int cardValue = card.getValue();
                                    System.out.println("Valeur de la carte cliquée : " + cardValue);
                                    currentContainer.getChildren().remove(imageView);
                                    currentPlayer.getHand().remove(card);
                                    cardsToPlace.add(card);
                                    currentPlayerIndex++;
                                    nextPlayerChooseCard();
                                }
                            }
                        });
                    }
                }
            } else {
                currentPlayerIndex++; // Passer au joueur suivant
                nextPlayerChooseCard();
            }
        } else {
            currentPlayerIndex = 0; // Réinitialiser l'index du joueur courant
            // Tous les joueurs ont choisi une carte
            if (cardsToPlace.size() == players.size()) {
                Collections.sort(cardsToPlace, (card1, card2) -> Integer.compare(card1.getValue(), card2.getValue()));
                // Afficher une pop-up ou effectuer toute autre action nécessaire
                System.out.println("All players have chosen a card.");
                System.out.println("Cards to place: " + cardsToPlace);
                placeCards(gridPane);
                cardsToPlace.clear();

            } else {
                System.out.println("Not all players have chosen a card yet.");
            }
        }
    }




    private void placeCards(GridPane gridPane) {
        int numRows = 4;
        int numColumns = 5;
        int currentCol = 0;
        int currentRow = 0;

        for (Cards cardsToPlace : cardsToPlace) {
            int bestRow = -1;
            int bestCol = -1;
            int differenceMin = Integer.MAX_VALUE;
            List<Cards> middleCards = new ArrayList<>(drawnCards);

            for (int row = 0; row < numRows; row++) {
                if (cardsToPlace.getValue() > middleCards.get(row).getValue()) {
                    for (int col = 0; col < numColumns; col++) {
                        boolean positionEmpty = true;

                        for (Node node : gridPane.getChildren()) {
                            if (node instanceof ImageView) {
                                Integer nodeCol = GridPane.getColumnIndex(node);
                                Integer nodeRow = GridPane.getRowIndex(node);

                                if (nodeCol != null && nodeRow != null && nodeCol == col && nodeRow == row) {
                                    positionEmpty = false;
                                    break;
                                }
                            }
                        }

                        if (positionEmpty) {
                            int difference = cardsToPlace.getValue() - middleCards.get(row).getValue();

                            if (difference < differenceMin) {
                                differenceMin = difference;
                                bestRow = row;
                                bestCol = col;
                            }
                        }
                    }
                }
            }

            if (bestRow == -1 || bestCol == -1) {
                bestRow = currentRow;
                bestCol = currentCol;
            }

            middleCards.set(bestRow, cardsToPlace);
            System.out.println(middleCards);

            Image imageCardsToPlace = new Image(cardsToPlace.getLink());
            Image newImageCardToPlace = MainController.cropImage(imageCardsToPlace, 180, 180);
            ImageView imageViewCardsToPlace = new ImageView(newImageCardToPlace);
            imageViewCardsToPlace.setFitWidth(90);
            imageViewCardsToPlace.setFitHeight(140);

            gridPane.add(imageViewCardsToPlace, bestCol, bestRow);

            currentCol = bestCol + 1;
            currentRow = bestRow;

            drawnCards = new ArrayList<>(middleCards);
        }

        checkColumns(gridPane);
    }


    //verifie quand une colonne est pleine
    private void checkColumns(GridPane gridPane) {
        int numRows = 4;
        int numColumns = 5;

        for (int col = 0; col < numColumns; col++) {
            boolean isColumnFull = true;

            for (int row = 0; row < numRows; row++) {
                Node node = getNodeByRowColumnIndex(row, col, gridPane);

                if (node == null || node instanceof Rectangle) {
                    isColumnFull = false;
                    break;
                }
            }

            if (isColumnFull) {
                collectCards(col, gridPane);
            }
        }
    }
    // recherche une carte spécifique dans un GridPane en utilisant ses coordonnées de ligne et de colonne.
    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;

        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && columnIndex != null && rowIndex == row && columnIndex == column) {
                result = node;
                break;
            }
        }

        return result;
    }
    //quand colonne pleine on collecte les cartes
    private void collectCards(int column, GridPane gridPane) {
        ObservableList<Node> children = gridPane.getChildren();
        List<Cards> collectedCards = new ArrayList<>();

        for (int row = 0; row < gridPane.getRowConstraints().size() - 1; row++) {
            Node node = getNodeByRowColumnIndex(row, column, gridPane);

            if (node != null) {
                Cards card = findCardByImage(node);
                if (card != null) {
                    collectedCards.add(card);
                }
                gridPane.getChildren().remove(node);
            }
        }

        if (!collectedCards.isEmpty()) {
            addToCardPile(collectedCards);
        }

        System.out.println("Cards in column " + column + " have been collected.");
    }
    private void addToCardPile(List<Cards> cards) {
        Pile currentPile = piles.get(currentPileIndex);
        currentPile.addCards(cards);
    }




    // identifier  cartes sélectionnées par  joueurs  et pour obtenir l'URL de l'image associée donc pour getNodeByRowColumnIndex
    private Cards findCardByImage(Node node) {
        for (Cards card : cardsToPlace) {
            if (card.getLink().equals(getImageSource(node))) {
                return card;
            }
        }

        return null;
    }

    private String getImageSource(Node node) {
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;
            Image image = imageView.getImage();

            if (image != null) {
                return image.getUrl();
            }
        }

        return null;
    }


    private class Pile {
        private List<Cards> cards;

        public Pile(List<Cards> cards) {
            this.cards = cards;
        }

        public List<Cards> getCards() {
            return cards;
        }

        public void addCards(List<Cards> cards) {
            this.cards.addAll(cards);
        }
    }


}


