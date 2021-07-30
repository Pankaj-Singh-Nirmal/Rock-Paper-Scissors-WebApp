package pl.rock.paper.scissors.service;

import java.util.Map;

/**
 * Collection of methods, making it possible to achieve the business logic 
 * of the application.
 * 
 * @author Pankaj Singh Nirmal
 */

public interface GameService 
{
	/**
	 * Returns the index of the specified element. Internally enum GameOptions values 
	 * are converted into a String[].
	 * 
	 * @param playerInput input from the player obtained from view layer
	 * @return returns the index of the specified element
	 */
	int getArrayIndexFromPlayerInput(String playerInput);

	/**
	 * Generate computer's input by randomly selecting a value from enum GameOptions.
	 * 
	 * @return returns String value of randomly selected value from enum GameOptions
	 */
	String getComputerInput();

	/**
	 * Returns the result of the game which specifies about the winner of the current
	 * match.
	 * 
	 * @param player1Input Integer version of the choice made by player 1
	 * @param player2Input Integer version of the choice made by player 2
	 * @throws ArrayIndexOutOfBoundsException if the input parameter is greater than
	 * 		   or equal to the size of the array
	 * @return returns the String value of the result
	 */
	String getResult(int player1Input, int player2Input);

	/**
	 * Returns the URL of the image given by the player's choice.
	 * 
	 * @param playerInput String version of the choice made by the player
	 * @return returns the URL of the image given by the player's choice
	 */
	String getImageUrl(String playerInput);

	/**
	 * Increments the score of the player who won the current match.
	 * 
	 * @param score is a <K, V> pair where key is the player number and value
	 * 		  is the score of that player
	 * @param result is the deciding factor of the winnings of the current game
	 */
	void incrementScore(Map<String, Integer> score, String result);

	/**
	 * Resets the current scores to zero when the game format is chosen as ALTERNATE.
	 * 
	 * @param score current value of the points gained by the players
	 * @param alternateGame one of the format of the game, named as ALTERNATE
	 */
	void resetScore(Map<String, Integer> score, boolean alternateGame);

	/**
	 * Resets the current scores to zero.
	 * 
	 * @param score current value of the points gained by the players
	 */
	void resetScore(Map<String, Integer> score);
}
