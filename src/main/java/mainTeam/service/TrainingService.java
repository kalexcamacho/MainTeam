package mainTeam.service;

import mainTeam.exceptions.InvalidTrainingDataException;
import mainTeam.model.Player;
import mainTeam.model.Stats;
import mainTeam.model.TrainingData;
import mainTeam.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

//    @Autowired
    private final PlayerRepository playerRepository;

    public TrainingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addTrainingData (TrainingData trainingData) throws InvalidTrainingDataException {
        for (Player playerData : trainingData.getPlayers()) {
            Player player = playerRepository.findById( String.valueOf(playerData.getId())).orElse(null);

            if (player == null) {
                player = new Player();
                player.setId(playerData.getId());
                player.setName(playerData.getName());
            }

            List<Stats> existingStats = player.getStatsList();
            List<Stats> newStats = playerData.getStatsList();

            for (Stats stats : newStats) {
                if (!existingStats.contains(stats)) {
                    if (stats.getSpeed() != null && Integer.parseInt(stats.getSpeed().getTime()) <= 0) {
                        throw new InvalidTrainingDataException("Time for speed must be greater than 0.");
                    }
                    existingStats.add(stats);
                }
            }
            player.setStatsList(existingStats);

            playerRepository.save(player);
        }
    }
}
