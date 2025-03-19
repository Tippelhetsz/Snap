package org.interview.game;

import org.interview.model.Card;
import org.interview.model.Player;
import org.interview.reader.InputReader;
import org.interview.validator.GameValidator;

import java.util.*;

public class Snap {

    public void playSnap() {
        Game game = new Game();
        GameValidator gameValidator = new GameValidator();
        InputReader inputReader = new InputReader();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Snap!");
        final int numberOfDecks = inputReader.getNumberOfDecks(scanner);
        final String matchingRule = inputReader.getMatchingRule(scanner);
        final int numberOfPlayers = inputReader.getNumberOfPlayers(scanner);
        final int stoppingCondition = inputReader.getStoppingCondition(scanner);

        game.createRules(numberOfDecks, matchingRule, numberOfPlayers, stoppingCondition);
        game.createDeck();
        game.createPlayers();
        game.dealCardsToPlayers();

        boolean isGameRunning = true;

        while (isGameRunning) {
            nextPlayerPlayCard(game);

            Optional<Card> optSnapCard = gameValidator.isSnapPossible(game);

            optSnapCard.ifPresent(card -> randomSnap(game, gameValidator, card));

            gameValidator.validatePlayerHands(game);

            isGameRunning = gameValidator.validateGameState(game);

            game.increaseRoundCounter();
        }
    }

    private void nextPlayerPlayCard(Game game) {
        int currentPlayer = game.getRoundCounter() % game.getNumberOfPlayers();
        game.getPlayers().get(currentPlayer).playCardFromHand();
    }

    private void randomSnap(Game game, GameValidator validator, Card snapCard) {
        if (new Random().nextInt(1, 10) % 3 == 0) {
            giveStacksToRandomPlayer(game, validator, snapCard);
        }
    }

    private void giveStacksToRandomPlayer(Game game, GameValidator validator, Card snapCard) {
        int randomPlayer = new Random().nextInt(0, game.getNumberOfPlayers());
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
