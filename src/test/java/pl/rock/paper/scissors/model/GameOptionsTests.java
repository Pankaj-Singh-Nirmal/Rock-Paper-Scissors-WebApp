package pl.rock.paper.scissors.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.rock.paper.scissors.model.GameOptions.PAPER;
import static pl.rock.paper.scissors.model.GameOptions.ROCK;
import static pl.rock.paper.scissors.model.GameOptions.SCISSORS;

import java.util.EnumSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@DisplayName("Tests for enum GameOptions")
class GameOptionsTests 
{
	@ParameterizedTest
	@EnumSource(GameOptions.class)
	@DisplayName("Should Get Enum Contents")
	void shouldGetEnumContents(GameOptions gameOptions) 
	{
		assertTrue(EnumSet.of(ROCK, PAPER, SCISSORS).contains(gameOptions));
	}
	
	@Test
	@DisplayName("Should Get Options As String[]")
	void shouldGetOptions() 
	{
		/** given **/
		String[] expected = {"ROCK", "PAPER", "SCISSORS"};
		
		/** when **/
		String[] actual = GameOptions.getOptions(GameOptions.class);
		
		/** then **/
		assertThat(actual).isNotNull().isNotEmpty();
		assertThat(actual.length).isEqualTo(3);
		assertArrayEquals(expected, actual);
	}
}
