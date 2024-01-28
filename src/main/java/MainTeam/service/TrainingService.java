package MainTeam.service;

import MainTeam.exceptions.InvalidTrainingDataException;
import MainTeam.model.Player;
import MainTeam.model.Stats;
import MainTeam.model.TrainingData;
import MainTeam.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {

    private final PlayerRepository playerRepository;

    public TrainingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addTrainingData (TrainingData trainingData) throws InvalidTrainingDataException {
        for (Player playerData : trainingData.getPlayers()) {
            Player player = playerRepository.findById(String.valueOf(playerData.getId())).orElse(null);

            if (player == null) {
                player = new Player();
                player.setId(playerData.getId());
                player.setName(playerData.getName());
                player.setStats(new ArrayList<>());
            }

            List<Stats> existingStats = player.getStats();
            List<Stats> newStats = playerData.getStats();

            for (Stats stats : newStats) {
                if (!existingStats.contains(stats)) {
                    if (stats.getSpeed() != null && Integer.parseInt(stats.getSpeed().getTime()) <= 0) {
                        throw new InvalidTrainingDataException("Time for speed must be greater than 0.");
                    }
                    existingStats.add(stats);
                }
            }

            playerRepository.save(player);
        }
    }
}
