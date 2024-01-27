package mainTeam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Player {
    @Id
    private int id;
    private String name;
    private List<Stats> statsList;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        float totalPercentage = powerPercentage + speedPercentage + passesPercentage;

        if (totalPercentage != 1) {
            powerPercentage = defaultPowerPercentage;
            speedPercentage = defaultSpeedPercentage;
            passesPercentage = defaultPassesPercentage;
        }

        if (statsList != null && !statsList.isEmpty()) {
            for (Stats stats : statsList) {
                float powerScore = Integer.parseInt(stats.getPower()) * powerPercentage;
                float speedScore = stats.getSpeed().calculateSpeed() * speedPercentage;
                float passesScore = Integer.parseInt(stats.getPasses()) * passesPercentage;

                totalScore += Math.round(powerScore + speedScore + passesScore);
            }
        }

        return totalScore;
    }
}
