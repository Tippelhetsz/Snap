package org.interview.game;

import lombok.Data;
import org.interview.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Game {

    private Rules rules;
    private List<Player> players;
    private List<Card> deck;
    private int roundCounter;

    public Game() {
        this.players = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.roundCounter = 0;
    }

    public void createRules(int numberOfDecks, String matchingRuleString, int numberOfPlayers, int stoppingCondition) {
        final MatchingRule matchingRule = MatchingRule.valueOf(matchingRuleString);
        stoppingCondition = stoppingCondition == 0 ? Integer.MAX_VALUE : stoppingCondition;

        this.rules = new Rules(numberOfDecks, matchingRule, numberOfPlayers, stoppingCondition);
    }

    public void createDeck() {
        final List<Card> deck = new ArrayList<>();

        for (int i = 1; i <= rules.getNumberOfDecks(); i++) {
            Deck newDeck = new Deck();
            Collections.shuffle(newDeck.getDeck());
            deck.addAll(newDeck.getDeck());
        }

        Collections.shuffle(deck);
        this.deck.addAll(deck);
    }

    public void createPlayers() {
        for (int i = 1; i <= rules.getNumberOfPlayers(); i++) {
            Player player = new Player(i);
            this.players.add(player);
        }
    }
}
