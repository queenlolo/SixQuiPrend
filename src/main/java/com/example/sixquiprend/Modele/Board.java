package com.example.sixquiprend.Modele;

import com.example.sixquiprend.Modele.Cards;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // definit le nombre de rangee et colonnes sur le platrau
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLUMNS = 5;
    private List<Integer> card;


    private List<List<Cards>> rows;

    public Board() {
        card = new ArrayList<>();
        rows = new ArrayList<>();

        for (int i = 0; i < NUM_ROWS; i++) {
            rows.add(new ArrayList<>());
        }
    }

    public void addCard(Cards card) {
        //cherche la carte à l'endroit le plus stratégique
        //int row = findBestRow(card);
        //ajoute la carte
        //rows.get(row).add(card);
        if (card != null) {
            this.card.addAll(card.getCard());
        }
    }

    public boolean isFull() {
        for (List<Cards> row : rows) {
            if (row.size() >= NUM_COLUMNS) {
                return true;
            }
        }
        return false;
    }

    public Cards takeBeefCard() {
        List<Cards> lastRow = rows.get(rows.size() - 1);
        Cards bullCard = lastRow.get(lastRow.size() - 1);
        lastRow.remove(bullCard);
        return bullCard;
    }

    public void reset() {
        for (List<Cards> row : rows) {
            row.clear();
        }
    }

    

    private int findBestRow(Cards card) {
        int bestRow = 0;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < rows.size(); i++) {
            List<Cards> row = rows.get(i);
            int sum = row.stream().mapToInt(Cards::getValue).sum();
            int diff = Math.abs(sum - card.getValue());

            if (diff < minDiff) {
                bestRow = i;
                minDiff = diff;
            }
        }

        return bestRow;
    }
}
