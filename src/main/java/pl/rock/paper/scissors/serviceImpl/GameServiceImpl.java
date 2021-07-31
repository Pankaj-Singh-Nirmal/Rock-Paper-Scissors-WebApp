package pl.rock.paper.scissors.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import pl.rock.paper.scissors.service.GameService;

@Service
public class GameServiceImpl implements GameService
{
	@Override
	public String getResult(String player1Input, String player2Input) 
	{
		/* 
		 * Map's key is the base option and the respective value is the
		 * winning option. Eg: Base key is "ROCK" and its respective value
		 * is "PAPER" which means "PAPER" wins over "ROCK".
		 */
		Map<String, String> winOptions = new HashMap<>();
		winOptions.put("ROCK", "PAPER");
		winOptions.put("PAPER", "SCISSORS");
		winOptions.put("SCISSORS", "ROCK");
		
		/** Winning conditions **/
		if(player1Input.equals(player2Input))
			return "It's a Draw !";
		else if(winOptions.get(player2Input).contains(player1Input))
			return "Player 1 Won !";
		else if(winOptions.get(player1Input).contains(player2Input))
			return "Player 2 Won !";
		else
			return "Logic error !!!";
	}
	
	@Override
	public void incrementScore(Map<String, Integer> score, String result) 
	{
		/*
		 * contains 1 meaning player 1 won. Contains 2 meaning player 2/computer won 
		 * else the match was a draw.
		 */
		if(result.contains("1"))
			score.put("player1", score.get("player1")+1);
		else if(result.contains("2"))
			score.put("player2", score.get("player2")+1);
	}
	
	@Override
	public void resetScore(Map<String, Integer> score) 
	{
		/** Reset score because user pressed RESET button **/
		score.put("player1", 0);
		score.put("player2", 0);
	}
}
