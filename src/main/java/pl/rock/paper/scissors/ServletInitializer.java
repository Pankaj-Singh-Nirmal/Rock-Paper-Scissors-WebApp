package pl.rock.paper.scissors;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Uses SpringApplicationBuilder to simply register this class as a configuration 
 * class of the application.
 * 
 * @author Pankaj Singh Nirmal
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RockPaperScissorsWebAppApplication.class);
	}

}
