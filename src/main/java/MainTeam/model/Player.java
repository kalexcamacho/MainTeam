package MainTeam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Player {
    @Id
    private int id;
    private String name;
    private List<Stats> stats;

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

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> statsList) {
        this.stats = statsList;
    }

    public int calculateTotalScore(List<Stats> stats, float powerPercentage, float speedPercentage, float passesPercentage) {
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

        if (stats != null && !stats.isEmpty()) {
            for (Stats stat : stats) {
                float powerScore = Integer.parseInt(stat.getPower()) * powerPercentage;
                float speedScore = stat.getSpeed().calculateSpeed() * speedPercentage;
                float passesScore = Integer.parseInt(stat.getPasses()) * passesPercentage;

                totalScore += Math.round(powerScore + speedScore + passesScore);
            }
        }

        return totalScore;
    }
}
