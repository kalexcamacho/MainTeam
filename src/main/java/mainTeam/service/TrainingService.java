package mainTeam.service;

import mainTeam.exceptions.InvalidTrainingDataException;
import mainTeam.model.Player;
import mainTeam.model.Stats;
import mainTeam.model.TrainingData;
import mainTeam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {

    private final PlayerRepository playerRepository;

    @Autowired
    public TrainingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addTrainingData (TrainingData trainingData) throws InvalidTrainingDataException {
        for (Player playerData : trainingData.getPlayers()) {
            Player player = playerRepository.findById(playerData.getId()).orElse(null);

            if (player == null) {
                player = new Player();
                player.setId(playerData.getId());
                player.setName(playerData.getName());
                player.setStatsList(new ArrayList<>());
            }

            List<Stats> existingStats = player.getStatsList();
            List<Stats> newStats = playerData.getStatsList();

            for (Stats stats : newStats) {
                if (!existingStats.contains(stats)) {
                    if (stats.getSpeed() != null && stats.getSpeed().getTime() <= 0) {
                        throw new InvalidTrainingDataException("Time for speed must be greater than 0.");
                    }
                    existingStats.add(stats);
                }
            }

            playerRepository.save(player);
        }
    }
}
