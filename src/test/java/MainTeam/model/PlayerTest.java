package MainTeam.model;

import java.util.ArrayList;
import java.util.List;

import MainTeam.exceptions.InvalidTrainingDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Stats createStats(String power, String distance, String time, String passes) throws InvalidTrainingDataException {
        Stats stats = new Stats();
        stats.setPower(power);
        Speed speed = new Speed(distance, time); // Crear Speed con los argumentos directamente
        stats.setSpeed(speed);
        stats.setPasses(passes);
        return stats;
    }

    @Test
    public void calculateTotalScoreTest() throws InvalidTrainingDataException {
        ArrayList<Stats> statsList = new ArrayList<>();
        statsList.add(createStats("300", "30", "5", "20"));

        Player player = new Player();
        int totalScore = player.calculateTotalScore(statsList, 0.2f, 0.3f, 0.5f);

        Assertions.assertEquals(72, totalScore);
    }

}
