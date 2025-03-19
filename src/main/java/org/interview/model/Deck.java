package org.interview.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck = createDeck();
    }

    private List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        int[] values =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",};

        for (String suit : suits) {
            for (int i = 0; i < values.length; i++) {
                deck.add(new Card(suit, rank[i], values[i]));
            }
        }

        return deck;
    }
}
