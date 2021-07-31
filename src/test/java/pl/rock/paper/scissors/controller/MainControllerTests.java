package pl.rock.paper.scissors.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.rock.paper.scissors.service.GameService;

@SpringBootTest
@DisplayName("Tests for class MainController")
@TestMethodOrder(OrderAnnotation.class)
class MainControllerTests 
{
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@MockBean
	GameService gameService;
	
	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Order(1)
	@DisplayName("Should Load Home Page")
	void shouldLoadHomePage() throws Exception 
	{
		/** given **/
		String viewName = "home-page";
		String forwardedUrl = "/WEB-INF/view/home-page.jsp";
		
		mockMvc.perform(get("/"))			/** when **/
			   .andExpect(status().isOk())	/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Load Rules Page")
	void shouldLoadRulesPage() throws Exception 
	{
		/** given **/
		String viewName = "rulesAndInstructions-page";
		String forwardedUrl = "/WEB-INF/view/rulesAndInstructions-page.jsp";
		
		mockMvc.perform(get("/rulesAndInstructions"))	/** when **/
			   .andExpect(status().isOk())				/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Return 404 Not Found Status")
	void shouldReturn404NotFoundStatus() throws Exception 
	{
		/** given **/
		String invalidUrl = "/thisUrlIsInvalid";
		
		mockMvc.perform(get(invalidUrl))				/** when **/
			   .andExpect(status().isNotFound());		/** then **/
	}
	
	@Test
	@DisplayName("Should Load One Player Game Page")
	void shouldLoadOnePlayerGamePage() throws Exception 
	{
		/** given **/
		String viewName = "playerOptions-page";
		String forwardedUrl = "/WEB-INF/view/playerOptions-page.jsp";
		
		mockMvc.perform(get("/onePlayerGame"))	/** when **/
			   .andExpect(status().isOk())		/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Show One Player Game Result Page")
	void shouldShowOnePlayerGameResultPage() throws Exception 
	{
		/** given **/
		String playerOneSelection = "SCISSORS";
		String viewName = "playAgain-page";
		String forwardedUrl = "/WEB-INF/view/playAgain-page.jsp";
		
		mockMvc.perform
			(get("/processOnePlayerGame?playerOneSelection="+playerOneSelection))	/** when **/
			   .andExpect(status().isOk())				/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Load Two Player Game Page")
	void shouldLoadTwoPlayerGamePage() throws Exception 
	{	
		/** given **/
		String viewName = "playerOptions-page";
		String forwardedUrl = "/WEB-INF/view/playerOptions-page.jsp";
		
		mockMvc.perform(get("/twoPlayerGame"))	/** when **/
			   .andExpect(status().isOk())		/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Show Two Player Game Player1 Page")
	void shouldShowTwoPlayerGamePlayer1Page() throws Exception 
	{	
		/** given **/
		String requestParam = "PAPER";
		String viewName = "playerOptions-page";
		String forwardedUrl = "/WEB-INF/view/playerOptions-page.jsp";
		
		mockMvc.perform
			(post("/processTwoPlayerGame-player-1")
			   .param("playerOneSelection", requestParam))	/** when **/
			   .andExpect(status().isOk())					/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Show Two Player Game Result Page")
	void shouldShowTwoPlayerGameResultPage() throws Exception 
	{	
		/** given **/
		String sessionAttribute = "ROCK";
		String requestParam = "PAPER";
		String viewName = "redirect:/playAgain";
		
		mockMvc.perform
			(post("/processTwoPlayerGame-player-2")
			   .sessionAttr("playerOneSelection", sessionAttribute) /** when **/
			   .param("playerTwoSelection", requestParam))		   
			   .andExpect(status().isFound())					    /** then **/
			   .andExpect(view().name(viewName));
	}
	
	@Test
	@DisplayName("Should Load Play Again Page")
	void shouldLoadPlayAgainPage() throws Exception 
	{	
		/** given **/
		String viewName = "playAgain-page";
		String forwardedUrl = "/WEB-INF/view/playAgain-page.jsp";
		
		mockMvc.perform
			(get("/playAgain"))					/** when **/
			   .andExpect(status().isOk())		/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Load Alternate Game Page")
	void shouldLoadAlternateGamePage() throws Exception 
	{
		/** given **/
		String viewName = "playerOptions-page";
		String forwardedUrl = "/WEB-INF/view/playerOptions-page.jsp";
		
		mockMvc.perform(get("/alternateGame"))	/** when **/
			   .andExpect(status().isOk())		/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Load Reset Game For One Player Game Page")
	void shouldLoadResetGameForOnePlayerGamePage() throws Exception 
	{
		/** given **/
		String viewName = "playerOptions-page";
		String forwardedUrl = "/WEB-INF/view/playerOptions-page.jsp";
		
		mockMvc.perform(get("/resetGameForOnePlayerGame"))	/** when **/
			   .andExpect(status().isOk())					/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
	
	@Test
	@DisplayName("Should Load Reset Game For Two Player Game Page")
	void shouldLoadResetGameForTwoPlayerGamePage() throws Exception 
	{
		/** given **/
		String viewName = "playerOptions-page";
		String forwardedUrl = "/WEB-INF/view/playerOptions-page.jsp";
		
		mockMvc.perform(get("/resetGameForTwoPlayerGame"))	/** when **/
			   .andExpect(status().isOk())					/** then **/
			   .andExpect(view().name(viewName))
			   .andExpect(forwardedUrl(forwardedUrl));
	}
}
