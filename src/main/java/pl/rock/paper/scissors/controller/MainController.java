package pl.rock.paper.scissors.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.rock.paper.scissors.service.GameService;

/**
 * Controller of the application. RequestDispatcher accepts the client request and
 * redirect it to this class' appropriate method as per the @GetMapping annotation.
 * 
 * @author Pankaj Singh Nirmal
 */

@Controller
@SessionAttributes("playerOneSelection")
public class MainController 
{
	@Autowired
	GameService gameService;
	
	private boolean alternateGame;
	private Map<String, Integer> score;
	
	/**
	 * Maps the request for root URL and returns the view for home page.
	 * 
	 * @return returns view for home-page.jsp
	 */
	@GetMapping("/")
	public String loadHomePage() 
	{
		alternateGame = false;
		
		score = new HashMap<>();
		score.put("player1", 0);
		score.put("player2", 0);
		
		return "home-page";
	}
	
	/**
	 * Maps the request for /rulesAndInstructions and returns the view for rules and 
	 * instructions page.
	 * 
	 * @return returns view for rulesAndInstructions-page.jsp
	 */
	@GetMapping("/rulesAndInstructions")
	public String loadRulesPage() 
	{	
		return "rulesAndInstructions-page";
	}
	
	/**
	 * Maps the request for /onePlayerGame and returns the view for player options for 
	 * player 1 when playing against computer.
	 * 
	 * @param model add the supplied attribute under the supplied name
	 * @return returns view for playerOptions-page.jsp
	 */
	@GetMapping("/onePlayerGame")
	public String loadOnePlayerGamePage(Model model) 
	{
		/** If the game format is ALTERNATE, then scores will be reset to zero for 
		 * both players.
		 */
		gameService.resetScore(score, alternateGame);
		
		model.addAttribute("onePlayerGameFormat", "yes")
			 .addAttribute("player1Score", score.get("player1"))
			 .addAttribute("player2Score", score.get("player2"));
		
		return "playerOptions-page";
	}
	
	/**
	 * Maps the request for /processOnePlayerGame and returns the view for result and
	 * play again.
	 * 
	 * @param playerOneSelection is bound to a web request parameter
	 * @param model add the supplied attribute under the supplied name
	 * @return returns view for playAgain-page.jsp
	 */
	@GetMapping("/processOnePlayerGame")
	public String showOnePlayerGameResultPage(@RequestParam String playerOneSelection, Model model) 
	{
		int arrayIndexFromPlayer1Input = gameService.getArrayIndexFromPlayerInput(playerOneSelection);
		
		String computerInput = gameService.getComputerInput();
		int arrayIndexFromComputerInput = gameService.getArrayIndexFromPlayerInput(computerInput);
		
		/** Decides who is the winner **/
		String result = gameService.getResult(arrayIndexFromPlayer1Input, arrayIndexFromComputerInput);
		
		/** After the winner is decided, score is incremented for the respective player **/
		gameService.incrementScore(score, result);
		
		model.addAttribute("gameFormat", "onePlayerGame")
			 .addAttribute("playerOneSelectionImgUrl", gameService.getImageUrl(playerOneSelection))
			 .addAttribute("computerSelectionImgUrl", gameService.getImageUrl(computerInput))
			 .addAttribute("result", result)
			 .addAttribute("player1Score", score.get("player1"))
			 .addAttribute("player2Score", score.get("player2"));
		
		if(alternateGame)
			model.addAttribute("alternateGameFormat", "yes");
		
		return "playAgain-page";
	}

	/**
	 * Maps the request for /twoPlayerGame and returns the view for player options 
	 * for player 1 when playing again another player.
	 * 
	 * @param model add the supplied attribute under the supplied name
	 * @param sessionStatus allowing the handler methods to signal that their session 
	 * 		  processing is complete
	 * @return returns view for playerOptions-page.jsp
	 */
	@GetMapping("/twoPlayerGame")
	public String loadTwoPlayerGamePage(Model model, SessionStatus sessionStatus) 
	{
		gameService.resetScore(score, alternateGame);
			
		model.addAttribute("twoPlayerGameFormat", "yes")
			 .addAttribute("player1Score", score.get("player1"))
			 .addAttribute("player2Score", score.get("player2"));
		
		/** Completes the session at this point for @SessionAttributes("playerOneSelection") **/
		sessionStatus.setComplete();
		
		return "playerOptions-page";
	}
	
	/**
	 * Maps the request for /processTwoPlayerGame-player-1 and returns the view 
	 * for player options for player 2 when playing against another player.
	 * 
	 * @param playerOneSelection is bound to a web request parameter
	 * @param model add the supplied attribute under the supplied name
	 * @param session identify a user across more than one page request and store 
	 * 		  information about that user
	 * @return returns view for playerOptions-page.jsp
	 */
	@PostMapping("/processTwoPlayerGame-player-1") // Post mapping to hide the choice made by player 1 in the URL
	public String showTwoPlayerGamePlayer1Page(@RequestParam String playerOneSelection, 
											   Model model, HttpSession session) 
	{
		/** Sets the value for @SessionAttributes("playerOneSelection") **/
		session.setAttribute("playerOneSelection", playerOneSelection);
		
		model.addAttribute("player1Score", score.get("player1"))
		 	 .addAttribute("player2Score", score.get("player2"));
		
		return "playerOptions-page";
	}
	
	/**
	 * Maps the request for /processTwoPlayerGame-player-2 and forwards the 
	 * request to /playAgain.
	 * 
	 * @param playerTwoSelection is bound to a web request parameter
	 * @param model add the supplied attribute under the supplied name
	 * @param session identify a user across more than one page request and store 
	 * 		  information about that user
	 * @return returns the request to /playAgain
	 */
	@PostMapping("/processTwoPlayerGame-player-2") // Post mapping so that score will not increment unfairly on page reload
	public String showTwoPlayerGameResultPage(@RequestParam String playerTwoSelection, 
											  Model model, 
											  HttpSession session, 
											  RedirectAttributes redirectAttributes) 
	{
		/** Fetches the value for @SessionAttributes("playerOneSelection") **/
		String playerOneSelection = (String) session.getAttribute("playerOneSelection");
		
		int arrayIndexFromPlayer1Input = gameService.getArrayIndexFromPlayerInput(playerOneSelection);
		int arrayIndexFromPlayer2Input = gameService.getArrayIndexFromPlayerInput(playerTwoSelection);
		
		String result = gameService.getResult(arrayIndexFromPlayer1Input, arrayIndexFromPlayer2Input);
		
		gameService.incrementScore(score, result);
		
		/** PRG Pattern **/
		redirectAttributes.addFlashAttribute("gameFormat", "twoPlayerGame")
						  .addFlashAttribute("playerOneSelectionImgUrl", gameService.getImageUrl(playerOneSelection))
						  .addFlashAttribute("playerTwoSelectionImgUrl", gameService.getImageUrl(playerTwoSelection))
						  .addFlashAttribute("result", result)
						  .addFlashAttribute("player1Score", score.get("player1"))
						  .addFlashAttribute("player2Score", score.get("player2"));
		
		if(alternateGame)
			redirectAttributes.addFlashAttribute("alternateGameFormat", "yes");
		
		return "redirect:/playAgain";
	}
	
	/**
	 * Maps the request for /playAgain and returns the view for 
	 * result and play again. If the page is reloaded now then an 
	 * error message comes on the screen for further instructions.
	 * 
	 * @return returns view for playAgain-page.jsp
	 */
	@GetMapping("/playAgain")
	public String loadPlayAgainPage()
	{
		return "playAgain-page";
	}
	
	/**
	 * Maps the request for /alternateGame and returns the view for player options for 
	 * player 1 when playing against computer.
	 * 
	 * @param model add the supplied attribute under the supplied name
	 * @return returns view for playerOptions-page.jsp
	 */
	@GetMapping("/alternateGame")
	public String loadAlternateGamePage(Model model) 
	{
		alternateGame = true;
		
		return loadOnePlayerGamePage(model);
	}
	
	/**
	 * Maps the request for /resetGameForOnePlayerGame and returns the view for 
	 * player options for player 1 when playing against computer. Game scores
	 * are also reset to zero for player and computer.
	 * 
	 * @param model add the supplied attribute under the supplied name
	 * @return returns view for playerOptions-page.jsp
	 */
	@GetMapping("/resetGameForOnePlayerGame")
	public String loadResetGameForOnePlayerGamePage(Model model) 
	{
		/** Resets the scores for player 1 and computer **/
		gameService.resetScore(score);
		
		return loadOnePlayerGamePage(model);
	}
	
	/**
	 * Maps the request for /resetGameForTwoPlayerGame and returns the view for 
	 * player options for player 1 when playing against another player. Game 
	 * scores are also reset to zero for both players.
	 * 
	 * @param model add the supplied attribute under the supplied name
	 * @param sessionStatus allowing the handler methods to signal that their session 
	 * 		  processing is complete
	 * @return returns view for playerOptions-page.jsp
	 */
	@GetMapping("/resetGameForTwoPlayerGame")
	public String loadResetGameForTwoPlayerGamePage(Model model, SessionStatus sessionStatus) 
	{
		/** Resets the scores for both players **/
		gameService.resetScore(score);
		
		return loadTwoPlayerGamePage(model, sessionStatus);
	}
}
