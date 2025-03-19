# Snap

The project was built using `openjdk-23` and Gradle.

To run the code, we can either run it from the IDE, by running the `main` method in the `Main` class,
 or by first building the project using `gradlew` and then running it from the command line:

```bash
  ./gradlew build
```

```bash
  java -jar build/libs/Snap-1.0.jar
```

## Technical decisions, design decisions, compromises
I tried to separate responsibility as much as possible, making sure that all the parts are focusing on one thing.
Although I think I could find places to improve, given the time constraint I approached the task with the KISS 
principal in mind. My approach was to handle each aspect of the game as its own entity e.g.: rules, players, cards, game, etc.

I had to make some compromises regarding how I handled the different collections, and given more time I would try
to refactor them, but I also wanted to keep it simple for the cases when I had to debug the code.

I also realized close to the end that I should have assigned the stacks of cards of each player to the game/table itself,
rather than the players, because right now if a player is removed from the game when they have no more cards, they take
their stack of cards with them, and it should be kept in the game for later, when some other player can snap it up.
But because of the time constraint I was focusing on a working solution and trying to clean up the code at the end.

## Improvements
Given more time I would implement an input validation to the beginning, currently the inputs are not validated,
and it is possible to get a `NumberFormatException` or `IllegalArgumentException` if the inputs are incorrect.

I would also implement testing and documentation to the methods.

Move the snap cards out of the player object and into the game and update the logic of card retriever from the table.

I also would look at the logic that calculates which player is next, because currently when we remove players
from the game -- when they have no more cards -- the logic I think skips the next player in line and goes to the next + 1 player.