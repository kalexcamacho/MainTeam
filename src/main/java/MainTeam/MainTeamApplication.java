package MainTeam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "MainTeam.repository")
public class MainTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainTeamApplication.class, args);
	}

}
