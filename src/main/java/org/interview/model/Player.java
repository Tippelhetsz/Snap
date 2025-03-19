package org.interview.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int playerNumber;
    private final List<Card> playerHand;
    private List<Card> stack;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.playerHand = new ArrayList<>();
        this.stack = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        this.playerHand.add(card);
    }
}
