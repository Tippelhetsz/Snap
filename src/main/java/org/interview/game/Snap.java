package org.interview.game;

import org.interview.model.Card;
import org.interview.model.Player;
import org.interview.validator.GameValidator;

import java.util.*;

public class Snap {

    public void playSnap() {
        Game game = new Game();
        GameValidator gameValidator = new GameValidator();

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
        game.dealCardsToPlayers();

        boolean isGameRunning = true;

        System.out.println(game.getPlayers().getFirst().getPlayerHand());
        System.out.println(game.getPlayers().getLast().getPlayerHand());
        while (isGameRunning) {
            nextPlayerPlayCard(game);

            Optional<Card> optSnapCard = gameValidator.isSnapPossible(game);

            optSnapCard.ifPresent(card -> randomSnap(game, gameValidator, card));

            gameValidator.validatePlayerHands(game);

            if (game.getPlayers().size() == 1) {
                System.out.println("Winner is Player " + game.getPlayers().getFirst().getPlayerNumber());
               isGameRunning = false;
            }

            game.increaseRoundCounter();
        }
    }

    private void nextPlayerPlayCard(Game game) {
        int currentPlayer = game.getRoundCounter() % game.getRules().getNumberOfPlayers();
        game.getPlayers().get(currentPlayer).playCardFromHand();
    }

    private void randomSnap(Game game, GameValidator validator, Card snapCard) {
        if (new Random().nextInt(1, 10) % 3 == 0) {
            giveStacksToRandomPlayer(game, validator, snapCard);
        }
    }

    private void giveStacksToRandomPlayer(Game game, GameValidator validator, Card snapCard) {
        int randomPlayer = new Random().nextInt(0, game.getRules().getNumberOfPlayers());
        List<Card> stackOfCards = new ArrayList<>();

        for (Player player : game.getPlayers()) {
            if (!player.getStack().isEmpty() &&
                    validator.isSnapAccordingToRules(game.getRules(), snapCard, player.getStack().getLast())) {
                stackOfCards.addAll(player.removeStack());
            }
        }

        game.getPlayers().get(randomPlayer).getPlayerHand().addAll(0, stackOfCards);
    }
}
