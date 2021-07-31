package pl.rock.paper.scissors.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import pl.rock.paper.scissors.model.GameOptions;

@DisplayName("Tests for class RockPaperScissorsUtility")
class RockPaperScissorsUtilityTests 
{
	@Test
	@DisplayName("Should Get Computer Input")
	void shouldGetComputerInput() 
	{	
		/** given **/
		final List<String> list = List.of("ROCK", "PAPER", "SCISSORS");
		
		/** when **/
		String actual = RockPaperScissorsUtility.getComputerInput();
		
		/** then **/
		assertThat(list, hasItem(actual));
	}
	
	@ParameterizedTest
	@EnumSource(GameOptions.class)
	@DisplayName("Should Get Image Url")
	void shouldGetImageUrl(GameOptions gameOptions) throws Exception 
	{	
		/** given **/
		String playerInput = gameOptions.toString();
		
		/** when **/
		String actual = RockPaperScissorsUtility.getImageUrl(playerInput);
		
		/** then **/
		String expected = "<img src=\"/images/"+playerInput.toLowerCase()+".png\">";
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("Should Throw Exception")
	void shouldThrowException() throws Exception 
	{	
		/** given **/
		String playerInput = "thisDoesNotExistInGameOptions";
		
		/** then **/
		assertThrows(Exception.class, 
				() -> RockPaperScissorsUtility.getImageUrl(playerInput));	/** when **/
	}
}
