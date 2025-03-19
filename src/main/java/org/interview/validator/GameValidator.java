package org.interview.validator;

import org.interview.game.Game;
import org.interview.model.Player;

public class GameValidator {

    public void validatePlayerHands(Game game) {
        game.getPlayers().removeIf(Player::isHandEmpty);
        game.getRules().setNumberOfPlayers(game.getPlayers().size());
    }
}
