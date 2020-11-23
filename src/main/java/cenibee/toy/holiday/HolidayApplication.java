package cenibee.toy.holiday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CHECK1 - @SpringBootApplication is consist of
 * 	- @Configuration
 * 	- @EnableAutoConfiguration
 * 	- @ComponentScan
 */
@SpringBootApplication
public class HolidayApplication {

	// CHECK2 - the main method uses Spring Boot's SpringApplication.run() to launch an application - no xml files such as web.xml!
	public static void main(String[] args) {
		SpringApplication.run(HolidayApplication.class, args);
	}

}
