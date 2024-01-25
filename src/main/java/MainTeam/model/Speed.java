package MainTeam.model;

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
        this.time = time;
    }

    public float calculateSpeed() {
        return time != 0 ? (float) distance / time : 0;
    }
}
