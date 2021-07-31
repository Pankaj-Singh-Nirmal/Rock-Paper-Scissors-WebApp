package pl.rock.paper.scissors.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Tests for class GameServiceImpl")
class GameServiceImplTests 
{
	@Autowired
	private GameService gameService;
	
	@Test
	@DisplayName("Should Get Result As Player 1 Won")
	void shouldGetResultAsPlayer1Won() 
	{	
		/** given **/
		String player1Input = "ROCK";
		String player2Input = "SCISSORS";
		
		/** when **/
		String actual = gameService.getResult(player1Input, player2Input);
		
		/** then **/
		String expected = "Player 1 Won !";
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("Should Get Result As Player 2 Won")
	void shouldGetResultAsPlayer2Won() 
	{	
		/** given **/
		String player1Input = "ROCK";
		String player2Input = "PAPER";
		
		/** when **/
		String actual = gameService.getResult(player1Input, player2Input);
		
		/** then **/
		String expected = "Player 2 Won !";
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("Should Get Result As A Draw")
	void shouldGetResultAsADraw() 
	{	
		/** given **/
		String player1Input = "ROCK";
		String player2Input = "ROCK";
		
		/** when **/
		String actual = gameService.getResult(player1Input, player2Input);
		
		/** then **/
		String expected = "It's a Draw !";
		assertThat(actual).isEqualTo(expected);
	}
	
	@Nested
	class ScoreMethodTests
	{
		private Map<String, Integer> score;
		
		@BeforeEach
		void setUp() 
		{
			score = new HashMap<>();
			score.put("player1", 4);
			score.put("player2", 7);
		}
		
		@Test
		@DisplayName("Should Increment Score")
		void shouldIncrementScore() 
		{	
			/** given **/
			String result1 = "It's a Draw !";
			String result2 = "Player 1 Won !";
			String result3 = "Player 2 Won !";
			
			/** when **/
			gameService.incrementScore(score, result1); // no increment
			gameService.incrementScore(score, result2); // +1 for player 1
			gameService.incrementScore(score, result3); // +1 for player 2
			
			/** then **/
			Integer expectedPlayer1Score = 5;
			Integer expectedPlayer2Score = 8;
			Integer actualPlayer1Score = score.get("player1");
			Integer actualPlayer2Score = score.get("player2");
			assertThat(actualPlayer1Score).isEqualTo(expectedPlayer1Score);
			assertThat(actualPlayer2Score).isEqualTo(expectedPlayer2Score);
		}
		
		@Test
		@DisplayName("Should Reset Score")
		void shouldResetScore() 
		{	
			/** given **/
			// Initialised Map score in setUp()
			
			/** when **/
			gameService.resetScore(score);
			
			/** then **/
			Integer expectedPlayer1Score = 0;
			Integer expectedPlayer2Score = 0;
			Integer actualPlayer1Score = score.get("player1");
			Integer actualPlayer2Score = score.get("player2");
			assertThat(actualPlayer1Score).isEqualTo(expectedPlayer1Score);
			assertThat(actualPlayer2Score).isEqualTo(expectedPlayer2Score);
		}
	}
}
