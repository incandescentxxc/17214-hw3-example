# Design and Justification of Santorini Game

## Overview

My Santorini Web Game is implemented as a full-stack web application, where the frontend uses React.js to create the UI, and the backend uses Spring Boot MVC to provide API response. The communication between UI and the backend is achieved by cross-origin Restful API calls. Thus, the whole project can be considered as a complete MVC structure. As per the requirement of the write-up, in this doc, we focus on the design of **Model** layer, which is the core of our Santorini Game.

Compared to HW-3, this version of Santorini reconsiders the functionality of **Board** and **Player**. In previous version, **Board** takes the responsiblity of checking whether the logic of move and build is valid. However, I made a big design decision to move the logic of verifying logic into **Player**, and made **Board** a pure utility class, which only serves the purpose of changing board-related status without verifying its validity. **Player** is a base class, where different Gods inheritate directly from this base class. Each god, based on their own power, can store extra player-related status and change the move, build logic without compromising the basic logic in Player. I did in this way because of the following points,

1. Design for Reuse: The base logic of player contains functions like isValidMove(), isValidBuild(), which can be the same across different gods. I didn't need to write repetitive codes to implement other gods with the same move and build logic.
2. Design for Extensibility: Easier to extend **Player** to have _GodPower_ by _inheritance_. We can easily added more Gods by only adding new God class which is inherited from the base **Player** class.
3. Design for Understandability (readbility, low representational gap): Player manipulates their workers by Board. This model is more intuitive than the alternative that Board takes Player into account to move a worker.

## <strong> Game.java </strong>

- Design principles: low representational gap, law of demeter
- Design heuristic: controller

Game.java directly receives and coordinates a system operation by interacting with the GameController (which is responsible for API calls). The Game.java would initialize the whole game, delegates Player to do corresponding action, and updates its own status. It contains both players, workers and the board. Apart from the game-related objects, it also stores the status of Game, which are called INIT, PLACE, SELECT, MOVE, BUILD, MOVE_OR_BUILD, BUILD_OR_SKIP, FINISHED. (Low representational gap)

However, Game.java would not actually do the move and build operation in its own. Instead, it would delegate the corresponding operations to those objects that are responsible for the operaitons. Game.java functions here as an organizer to organize different objects to work properly. (Law of demeter)

## <strong> Player.java </strong>

### **Athena.java | Demeter.java | Minotaur.java | Pan.java**

- Design principles: low representational gap
- Design goals: Reuse, extensibility

Player-related classes function as the brain of the game, because they define whether a move or a build is valid or not based on their god power. Therefore, Player.java is the base object defining default logics. Other god players can override some of the logic, and reuse the majority of same logic. This is particularly useful because the majority of the logic is the same. God power only alters a small portion of logic.

## <strong> Board.java (model) </strong>

- Design principles: low representational gap, low coupling, law of demeter
- Design heuristic: information expert
- Design pattern: Strategy Pattern

Board simulates the most natural idea of a board in a realistic game. It only has a series of grids, which stands for the square units in the board. (low representation gap).

The only advantage of board over grid is that board knows all the grids, and is able to check the relationship across grids. While grid can only know its own content and states. This point gives the responsibility of board - check two or more grids' relation, while not go deep into a single grid. Instead, when performing tasks such as move and build in the grid, it delegates the tasks to the grid itself.
(low coupling, law of demeter, and information expert).

Since Game.java maintains the status of a list of operable grids, and the list of operable grids depends on Game Status, therefore Board should have a function that return operable grids based on game status, and this is done by Strategy Pattern. This considers that if there are more new Game Status in the future, this way can minifies the influence to existing codes.

## <strong> Grid.java (model) </strong>

- Design principles: low representational gap, low coupling, law of demeter

Grid is the single square unit on a board. It knows what is on the grid, either it is a worker or a tower. (low representation gap).

As mentioned above, grid knows and only knows its own content, and doesn't contain the information of other grids. So when performing validity check, it can only gives the true or false based on whether there is a violation to its knowledege (occupation). Though it knows whethere there exists a tower or a worker, it won't finish the tasks for them. Instead, it will further delegate the jobs of building to tower.
(low coupling and law of demeter).

## <strong> Tower.java (model) </strong>

- Design principles: low representational gap, low coupling, law of demeter

Tower corresponding to the blocks and the dome in the real game. Similarly, it also has levels, standing for its height, and a variable _hasDome_ to represent whether there is a dome in the tower. (low representation gap).

Tower only receives the tasks from grid. It can build itself up one level up or add a dome on top. Reversely, however, it cannot know the position of itself from grid.(low coupling and law of demeter).

## <strong> Worker.java (model) </strong>

- Design principles: low representational gap, law of demeter

Workers are those units that can be manipulated by the players. It knows about the information of the grid he/she is currently stand on.
