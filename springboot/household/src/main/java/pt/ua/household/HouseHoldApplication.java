package pt.ua.household;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HouseHoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseHoldApplication.class, args);
	}

}
