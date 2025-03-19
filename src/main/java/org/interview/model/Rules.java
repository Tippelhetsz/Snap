package org.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rules {

    private final int numberOfDecks;
    private final MatchingRule matchingRule;
    private final int numberOfPlayers;
    private final int stoppingCondition;
}
