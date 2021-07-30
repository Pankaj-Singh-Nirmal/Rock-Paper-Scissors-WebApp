# Rock-Paper-Scissors-WebApp
Game rules: https://en.wikipedia.org/wiki/Rock_paper_scissors

## Game modes:
- 1 Player: Player plays against computer
- 2 Players: 2 players play against each other
- Alternate: Game format switches after every game

## Stack
- Java 11
- Spring : Boot, Web, Tests
- HTML
- CSS
- JUnit 5
- Mockito
- Maven

## Build
Execute mvn clean install. It will start building process with test validations.
If process will be successful, a fat jar will be created in target folder with all dependencies: Rock-Paper-Scissors-WebApp-0.0.1-SNAPSHOT.jar

## Game extension
A Rock, paper, scissors, lizard, spock may be an extension for the current version of the game. Implementation ideas are as follows:
- Add required values (LIZARD and SPOCK) in the GameOptions enum
- Increase the array size with appropriate values in the getResult(int player1Input, int player2Input) from class GameServiceImpl
- Add appropriate request handlers in the MainController class
- Add appropriate views
