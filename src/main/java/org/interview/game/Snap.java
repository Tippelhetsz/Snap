package org.interview.game;

import java.util.Scanner;

public class Snap {

    public void playSnap() {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Snap!");
        System.out.println("How many playing card decks to play");
        final int numberOfDecks = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Should cards be matched: on suit, value, or both.");
        final String matchingRule = scanner.nextLine().trim().toUpperCase();
        System.out.println("How many players are playing");
        final int numberOfPlayers = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("After how many played cards should the game stop:");
        System.out.println("(if game should run until there is only 1 player left, type 0)");
        final int stoppingCondition = Integer.parseInt(scanner.nextLine().trim());

        game.createRules(numberOfDecks, matchingRule, numberOfPlayers, stoppingCondition);
        game.createDeck();
        game.createPlayers();
    }
}
