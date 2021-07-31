# Rock-Paper-Scissors-WebApp
Game rules: https://en.wikipedia.org/wiki/Rock_paper_scissors

## Demo:
Game can be played on a machine with following docker commands:
  >**docker pull pankajsn/rock-paper-scissors**\
  >**docker run -p 8080:8080 pankajsn/rock-paper-scissors**

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
If process will be successful, a war will be created in target folder with all dependencies: rock-paper-scissors.war

## Game extension
A Rock, paper, scissors, lizard, spock may be an extension for the current version of the game. Implementation ideas are as follows:\
step 1: add two more values in enum GameOptions in package pl.rock.paper.scissors.model\
		The new enum looks like this:
		
		public enum GameOptions 
		{
			ROCK,
			PAPER,
			SCISSORS,
			LIZARD,	// new
			SPOCK;	// new
		}
		
		
step 2: update Map winOptions in method getResult() from class GameServiceImpl 
		in package pl.rock.paper.scissors.serviceImpl as follows:
			
			Map<String, String> winOptions = new HashMap<>();
			winOptions.put("ROCK", "PAPER, SPOCK");
			winOptions.put("PAPER", "SCISSORS, LIZARD");
			winOptions.put("SCISSORS", "ROCK, SPOCK");
			winOptions.put("LIZARD", "ROCK, SCISSORS");
			winOptions.put("SPOCK", "PAPER, LIZARD");
		
step 3: Add images for LIZARD and SPOCK with name lizard.png and spock.png respectively.\
step 4: Add the newly created images in the respective view pages as player options. 
