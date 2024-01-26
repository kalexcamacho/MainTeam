package mainTeam.model;

public class TeamSelectionCriteria {
    private Float powerPercentage;
    private Float speedPercentage;
    private Float passesPercentage;
    private Integer teamSize;

    public Float getPowerPercentage() {
        return powerPercentage;
    }

    public void setPowerPercentage(Float powerPercentage) {
        this.powerPercentage = powerPercentage;
    }

    public Float getSpeedPercentage() {
        return speedPercentage;
    }

    public void setSpeedPercentage(Float speedPercentage) {
        this.speedPercentage = speedPercentage;
    }

    public Float getPassesPercentage() {
        return passesPercentage;
    }

    public void setPassesPercentage(Float passesPercentage) {
        this.passesPercentage = passesPercentage;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }
}