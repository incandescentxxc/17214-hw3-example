# Homework 5: Santorini (Part 2)

# Get Started

This project implemented the Satorini Game in a frontend/backend seperated fashion. Frontend is
written in React.js, backend is a Spring Boot application. Therefore, it is necessary to launch two
applications (frontend and backend ) in the meantime.

## Boot up the backend: Spring Boot

0.  Clone the project
1.  cd to the project directory
2.  In terimial of current directory, enter

            mvn install

3.  Then enter the following to add maven wrapper jar

            mvn -N io.takari:maven:wrapper

4.  Then run the Spring boot application by

            ./mvnw clean spring-boot:run

## Boot up the frontend: React

0.  install yarn, which is a similar package manager with npm
1.  cd to the /frontend directory
2.  Entern

            yarn install

3.  Enter

            yarn start

4.  If all the above steps are finished, open the browser and go to localhost:8000

## Run tests

Unit and integrate testing are only written for the backend. To test backend, cd to root folder and run

            mvn site

or

        mvn test

## How to Play

The game logic then follow strictly with the original game rules.

1. Select a player to start. The starting player will choose god power for both the player.
2. Starting player choose 2 god power from any 8 of the power card (except Hermes and Promethus not implemented yet) by clicking the card. Then click Next.
3. The second player then chooses a god for him/herself, leaving the other god power to the first player.
4. （optional) Each player may enter a name for themselves. By default, the names of player 1 and 2 are simply Player1, Player2. Click Start the Game.
5. Then here comes the main page of the game interface. The power rules of the god are shown on the two sides of the page. The status prompt are shown on the top of the page. The border of the square is colored to indicate whether you can click a square or not. Specificly,

- Green color: you can PLACE a player on the square, or you can select any worker from that square.
- Yellow color: you can MOVE a worker on that square, or you can click the button - "cancel this selection" to go back to cancel stage.
- Red color: you can BUILD a block on that square

6. Note that some gods like demeter will have a option to build one more block, this can be skip by click the button below the board.

7. If some player wins, the winning page directly shows.
8. New Game option is located in the top-left corner "Santorini". If you click Santorini there, the whole game will be restarted.

## God card implementation

Apart from the four required gods. four more gods are implemented. They are **Apollo, Artemis, Atlas, and Hephaestus.**
