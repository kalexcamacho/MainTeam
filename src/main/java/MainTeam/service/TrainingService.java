package MainTeam.service;

import MainTeam.model.Player;
import MainTeam.model.TrainingData;
import MainTeam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrainingService {

    private final PlayerRepository playerRepository;

    @Autowired
    public TrainingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addTrainingData(TrainingData trainingData) {
        for (Player playerData : trainingData.getPlayers()) {
            Player player = playerRepository.findById(playerData.getId()).orElse(null);

            if (player == null) {
                player = new Player();
                player.setId(playerData.getId());
                player.setName(playerData.getName());
                player.setStatsList(new ArrayList<>());
            }

            player.getStatsList().addAll(playerData.getStatsList());

            playerRepository.save(player);
        }
    }
}
