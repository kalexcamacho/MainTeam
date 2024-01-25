package mainTeam.model;

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

    public void setTime(int time) {
        if (time <= 0) {
            throw new IllegalArgumentException("Time must be greater than 0.");
        }
        this.time = time;
    }

    public float calculateSpeed() {
        if (time == 0) {
            throw new IllegalStateException("Cannot calculate speed when time is 0.");
        }
        return (float) distance / time;
    }
}
