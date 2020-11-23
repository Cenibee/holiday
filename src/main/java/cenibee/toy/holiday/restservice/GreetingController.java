package cenibee.toy.holiday.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// CHECK5 - this annotion marks th class as a controller where every method returns a domain object instead of a view
//			 - (@Controller + @ResponseBody)
@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// CHECK1 - Get(Post/Put and so on)Mapping
	// CHECK2 - other form:
	// @RequestMapping(method = RequestMethod.GET)
	@GetMapping("/greeting")
	public Greeting greeting(
		// CHECK3 - this from query String ?-in POST, from body-?
		@RequestParam(value = "name", defaultValue = "World") String name) {
		
		// CHECK4 - the Main difference with a traditional MVC Controller and RESTful web service: no view technology; Spring's Http message converter
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
