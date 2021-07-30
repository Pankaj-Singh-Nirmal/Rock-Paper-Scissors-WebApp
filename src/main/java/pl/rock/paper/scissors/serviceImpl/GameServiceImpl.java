package pl.rock.paper.scissors.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import pl.rock.paper.scissors.model.GameOptions;
import pl.rock.paper.scissors.service.GameService;

@Service
public class GameServiceImpl implements GameService
{
	@Override
	public int getArrayIndexFromPlayerInput(String playerInput) 
	{
		/** Convert values of enum GameOptions into String array **/
		String[] gameOptions = GameOptions.getOptions(GameOptions.class);
		
		/** Get the index of the option selected by the player 
		 * (will be useful later in 2d array while obtaining result) 
		 */
		int index = Arrays.asList(gameOptions).indexOf(playerInput);
		
		return index;
	}
	
	@Override
	public String getComputerInput() 
	{
		/** Generate computer's input by randomly selecting a value from enum GameOptions **/
		return GameOptions.values()[new Random().nextInt(GameOptions.values().length)].toString();
	}
	
	@Override
	public String getResult(int player1Input, int player2Input) 
	{
		/** 0 = Draw, 1 = Player-1 won, 2 = Player-2 won **/
		int[][] resultPermutations = {
										{0, 2, 1},
										{1, 0, 2},
										{2, 1, 0}
					  				 };
		
		/** Obtain index from int[][] resultPermutations **/
		int resultEncoded = resultPermutations[player1Input][player2Input];
		
		/** Generate decoding key for int[][] resultPermutations **/
		Map<Integer, String> decodeResult = new HashMap<>();
		decodeResult.put(0, "It's a Draw !");
		decodeResult.put(1, "Player 1 Won !");
		decodeResult.put(2, "Player 2 Won !");
		
		/** Decode value using decoding key **/
		String resultDecoded = decodeResult.get(resultEncoded);
		
		return resultDecoded;
	}
	
	@Override
	public String getImageUrl(String playerInput) 
	{
		Map<String, String> imageMapper = new HashMap<>();
		imageMapper.put("ROCK", "<img src=\"/images/rock.png\">");
		imageMapper.put("PAPER", "<img src=\"/images/paper.png\">");
		imageMapper.put("SCISSORS", "<img src=\"/images/scissors.png\">");
		
		String imageUrl = imageMapper.get(playerInput);
		
		return imageUrl;
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
	public void resetScore(Map<String, Integer> score, boolean alternateGame) 
	{
		/** Reset score only if alternateGame is true **/
		if(alternateGame) 
		{
			score.put("player1", 0);
			score.put("player2", 0);
		}
	}
	
	@Override
	public void resetScore(Map<String, Integer> score) 
	{
		/** Reset score because user pressed RESET button **/
		score.put("player1", 0);
		score.put("player2", 0);
	}
}
