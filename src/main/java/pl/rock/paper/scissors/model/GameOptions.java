package pl.rock.paper.scissors.model;

import java.util.Arrays;

/**
 * An enum which stores the constant values ROCK, PAPER, SCISSORS which
 * are the options presented to the player when it is their turn to play.
 * 
 * @author Pankaj Singh Nirmal
 */

public enum GameOptions 
{
	ROCK,
	PAPER,
	SCISSORS;
	
	/**
	 * Converts the enum constants to String[] by mapping each value to a string 
	 * constant inside the array.
	 * 
	 * @param e enum object containing all the enum constants
	 * @return returns a String array populated with enum constants
	 */
	public static String[] getOptions(Class<? extends Enum<?>> e) 
	{
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
