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
	 * Returns the result of the game which specifies about the winner of the current
	 * match.
	 * 
	 * @param player1Input choice made by player 1
	 * @param player2Input choice made by player 2 or computer
	 * @return returns the resulting comment of the current match
	 */
	String getResult(String player1Input, String player2Input);
	
	/**
	 * Increments the score of the player who won the current match.
	 * 
	 * @param score is a <K, V> pair where key is the player number and value
	 * 		  is the score of that player
	 * @param result is the deciding factor of the winnings of the current game
	 */
	void incrementScore(Map<String, Integer> score, String result);

	/**
	 * Resets the current scores to zero.
	 * 
	 * @param score current value of the points gained by the players
	 */
	void resetScore(Map<String, Integer> score);
}
