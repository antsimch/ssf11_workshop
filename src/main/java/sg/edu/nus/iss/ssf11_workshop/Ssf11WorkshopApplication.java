package sg.edu.nus.iss.ssf11_workshop;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ssf11WorkshopApplication {

	private static final Logger logger = LoggerFactory.getLogger(Ssf11WorkshopApplication.class);

	// default port number
	private static final String DEFAULT_PORT = "3000";
	
	public static void main(String[] args) {
		logger.info("main method started......");

		// initialize the spring app
		SpringApplication app = new SpringApplication(Ssf11WorkshopApplication.class);

		// read args array and check for "port" parameter
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsValues = appArgs.getOptionValues("port");

		String port;

		// if port number is not in argument
		if ((opsValues == null) || (opsValues.get(0) == null)) {
			 
			// read port number from env variables
			port = System.getenv("PORT");

			if (port == null) {
				port = DEFAULT_PORT;
			}
		} else {
			
			// passing port number from CLI
			port = opsValues.get(0);
		}

		if (port != null) {
			
			// setting port number in the spring-boot config
			app.setDefaultProperties(Collections.singletonMap("server.port", port));
		}

		// launch spring boot app
		app.run(args);
	}

}
