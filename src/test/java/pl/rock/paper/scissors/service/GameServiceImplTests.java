package pl.rock.paper.scissors.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.rock.paper.scissors.model.GameOptions;

@SpringBootTest
@DisplayName("Tests for class GameServiceImpl")
class GameServiceImplTests 
{
	@Autowired
	private GameService gameService;
	
	@Test
	@DisplayName("Should Get Array Index From Player Input")
	void shouldGetArrayIndexFromPlayerInput() 
	{
		/** given **/
		// Reference String array is {"ROCK", "PAPER", "SCISSORS"}
		String playerInput = "PAPER";
		
		/** when **/
		int actualIndex = gameService.getArrayIndexFromPlayerInput(playerInput);
		int actualIndexNotFound = gameService.getArrayIndexFromPlayerInput(null);
		
		/** then **/
		int expectedIndex = 1;
		int expectedIndexNotFound = -1;
		assertThat(actualIndex).isEqualTo(expectedIndex);
		assertThat(actualIndexNotFound).isEqualTo(expectedIndexNotFound);
	}
	
	@Test
	@DisplayName("Should Get Computer Input")
	void shouldGetComputerInput() 
	{	
		/** given **/
		final List<String> list = List.of("ROCK", "PAPER", "SCISSORS");
		
		/** when **/
		String actual = gameService.getComputerInput();
		
		/** then **/
		assertThat(list, hasItem(actual));
	}
	
	@Test
	@DisplayName("Should Get Result")
	void shouldGetResult() 
	{	
		/** given **/
		
		/**  Reference 2D array:
		 * 
		 	{
				{0, 2, 1},
				{1, 0, 2},
				{2, 1, 0}
			};
		 * 
		 * */
		
		int player1Input = 1;
		int player2Input = 2;
		
		/** when **/
		String actual = gameService.getResult(player1Input, player2Input);
		
		/** then **/
		String expected = "Player 2 Won !";
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("Should Get Result With Exception")
	void shouldGetResultWithException() 
	{	
		/** given **/
		
		/**  Reference 2D array:
		 * 
		 	{
				{0, 2, 1},
				{1, 0, 2},
				{2, 1, 0}
			};
		 * 
		 * */
		
		int player1Input = 1;
		int player2InvalidInput = 3;
		
		assertThrows(ArrayIndexOutOfBoundsException.class, /** then **/
			() -> gameService.getResult(player1Input, player2InvalidInput));	/** when **/
	}
	
	@ParameterizedTest
	@EnumSource(GameOptions.class)
	@DisplayName("Should Get Image Url")
	void shouldGetImageUrl(GameOptions gameOptions) 
	{	
		/** given **/
		String playerInput = gameOptions.toString();
		
		/** when **/
		String actual = gameService.getImageUrl(playerInput);
		
		/** then **/
		String expected = "<img src=\"/images/"+playerInput.toLowerCase()+".png\">";
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
		@DisplayName("Should Reset Score With Alternate Game")
		void shouldResetScoreWithAlternateGame() 
		{	
			/** given **/
			boolean alternateGame = true;
			
			/** when **/
			gameService.resetScore(score, alternateGame);
			
			/** then **/
			Integer expectedPlayer1Score = 0;
			Integer expectedPlayer2Score = 0;
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
