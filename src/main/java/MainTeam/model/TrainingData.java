package MainTeam.model;

import java.util.ArrayList;
import java.util.List;
public class TrainingData {
    private List<Player> players;

    public TrainingData() {
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
