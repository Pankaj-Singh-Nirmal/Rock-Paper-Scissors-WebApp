package pl.rock.paper.scissors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class containing the main method of the application. This is the starting point when
 * the application is run.
 * 
 * @author Pankaj Singh Nirmal
 */

@SpringBootApplication
public class RockPaperScissorsWebAppApplication 
{
	/**
	 * Main method of the application. This is the starting point when
	 * the application is run.
	 * 
	 * @param args used for passing arguments at run time
	 */
	public static void main(String[] args) 
	{
		SpringApplication.run(RockPaperScissorsWebAppApplication.class, args);
	}

}
