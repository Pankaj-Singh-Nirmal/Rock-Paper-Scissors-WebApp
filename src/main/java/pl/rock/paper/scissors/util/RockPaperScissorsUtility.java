package pl.rock.paper.scissors.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.rock.paper.scissors.model.GameOptions;

public final class RockPaperScissorsUtility 
{	
	/**
	 * Generate computer's input by randomly selecting a value from enum GameOptions.
	 * 
	 * @return returns String value of randomly selected value from enum GameOptions
	 */
	public static String getComputerInput() 
	{
		/** Generate computer's input by randomly selecting a value from enum GameOptions **/
		return GameOptions.values()[new Random().nextInt(GameOptions.values().length)].toString();
	}
	
	/**
	 * Returns the URL of the image given by the player's choice.
	 * 
	 * @param playerInput String version of the choice made by the player
	 * @throws Exception if @param is not in enum GameOptions
	 * @return returns the URL of the image given by the player's choice
	 */
	public static String getImageUrl(String playerInput) throws Exception 
	{
		List<String> options = Stream.of(GameOptions.values()).map(Enum::name).collect(Collectors.toList());
		
		if(!options.contains(playerInput))
			throw new Exception(playerInput + " doesn't exist as an option in enum GameOptions !");
			
		String imageUrl = "<img src=\"/images/"+playerInput.toLowerCase()+".png\">";
		
		return imageUrl;
	}
}
