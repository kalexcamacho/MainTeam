package mainTeam.model;

import mainTeam.exceptions.InvalidTrainingDataException;

public class Speed {
    private int distance;   //mts
    private int time;   //seg

    public Speed() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) throws InvalidTrainingDataException {
        if (time <= 0) {
            throw new InvalidTrainingDataException("Time must be greater than 0.");
        }
        this.time = time;
    }

    public Speed(int distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    public float calculateSpeed() {
        if (time == 0) {
            throw new IllegalStateException("Cannot calculate speed when time is 0.");
        }
        return (float) distance / time;
    }
}
