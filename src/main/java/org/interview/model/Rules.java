package org.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rules {

    private int numberOfDecks;
    private MatchingRule matchingRule;
    private int numberOfPlayers;
    private int stoppingCondition;
}
