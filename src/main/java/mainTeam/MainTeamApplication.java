package mainTeam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "mainTeam.repository")
public class MainTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainTeamApplication.class, args);
	}

}
