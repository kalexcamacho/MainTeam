package mainTeam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Player {
    @Id
    private String id;
    private String name;
    private List<Stats> statsList;

    public Player() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stats> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<Stats> statsList) {
        this.statsList = statsList;
    }

    public int calculateTotalScore(List<Stats> statsList, float powerPercentage, float speedPercentage, float passesPercentage) {
        int totalScore = 0;

        final float defaultPowerPercentage = 0.20f;
        final float defaultSpeedPercentage = 0.30f;
        final float defaultPassesPercentage = 0.50f;

        powerPercentage = (powerPercentage > 0 && powerPercentage <= 1) ? powerPercentage : defaultPowerPercentage;
        speedPercentage = (speedPercentage > 0 && speedPercentage <= 1) ? speedPercentage : defaultSpeedPercentage;
        passesPercentage = (passesPercentage > 0 && passesPercentage <= 1) ? passesPercentage : defaultPassesPercentage;

        for (Stats stats : statsList) {
            float powerScore = stats.getPower() * powerPercentage;
            float speedScore = stats.getSpeed().calculateSpeed() * speedPercentage; // Calcula el puntaje de velocidad directamente aquí
            float passesScore = stats.getPasses() * passesPercentage;

            totalScore += Math.round(powerScore + speedScore + passesScore);
        }

        return totalScore;
    }
}
