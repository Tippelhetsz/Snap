package org.interview.reader;

import java.util.Scanner;

public class InputReader {

    public int getNumberOfDecks(Scanner scanner) {
        System.out.println("How many playing card decks to play");

        return Integer.parseInt(scanner.nextLine().trim());
    }

    public String getMatchingRule(Scanner scanner) {
        System.out.println("Should cards be matched: on suit, value, or both.");

        return scanner.nextLine().trim().toUpperCase();
    }

    public int getNumberOfPlayers(Scanner scanner) {
        System.out.println("How many players are playing");

        return Integer.parseInt(scanner.nextLine().trim());
    }

    public int getStoppingCondition(Scanner scanner) {
        System.out.println("After how many played cards should the game stop:");
        System.out.println("(if game should run until there is only 1 player left, type 0)");

        return Integer.parseInt(scanner.nextLine().trim());
    }
}
