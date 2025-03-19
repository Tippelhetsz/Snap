package org.interview.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Card {

    private final String suit;
    private final String rank;
    private final int value;


    @Override
    public String toString() {
        return this.rank + this.suit;
    }
}
