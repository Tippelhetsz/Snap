package org.interview.validator;

import org.interview.game.Game;
import org.interview.model.Card;
import org.interview.model.Player;
import org.interview.model.Rules;

import java.util.List;
import java.util.Optional;

public class GameValidator {

    public Optional<Card> isSnapPossible(Game game) {
        final var topCards = getTopCardsFromPlayersStack(game.getPlayers());

        for (int i = 0; i < topCards.size(); i++) {
            for (int j = 0; j < topCards.size(); j++) {
                if (i != j && isSnapAccordingToRules(game.getRules(), topCards.get(i), topCards.get(j))) {
                    return Optional.ofNullable(topCards.get(i));
                }
            }
        }
        return Optional.empty();
    }

    public boolean isSnapAccordingToRules(Rules rules, Card cardOne, Card cardTwo) {
        switch(rules.getMatchingRule()) {
            case SUIT -> {
                return cardOne.getSuit().equals(cardTwo.getSuit());
            }
            case VALUE -> {
                return cardOne.getValue() == cardTwo.getValue();
            }
            default -> {
                return cardOne.getSuit().equals(cardTwo.getSuit()) && cardOne.getValue() == cardTwo.getValue();
            }
        }
    }

    public void validatePlayerHands(Game game) {
        game.getPlayers().removeIf(Player::isHandEmpty);
        game.getRules().setNumberOfPlayers(game.getPlayers().size());
    }

    public boolean validateGameState(Game game) {
        if (game.getPlayers().size() == 1) {
            System.out.println("Winner is Player " + game.getPlayers().getFirst().getPlayerNumber());
            return false;
        }

        if ((game.getRoundCounter() + 1) >= game.getRules().getStoppingCondition()) {
            System.out.printf("Reached end of the game. %d cards have been played\n", game.getRules().getStoppingCondition());
            return false;
        }

        return true;
    }

    private static List<Card> getTopCardsFromPlayersStack(List<Player> players) {
        return players.stream()
                .map(Player::getStack)
                .filter(stack -> !stack.isEmpty())
                .map(List::getLast)
                .toList();
    }
}
