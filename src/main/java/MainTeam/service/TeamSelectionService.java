package MainTeam.service;

import MainTeam.model.Player;
import MainTeam.model.PlayerScore;
import MainTeam.model.TeamSelectionCriteria;
import MainTeam.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TeamSelectionService {

    private final PlayerRepository playerRepository;

    public TeamSelectionService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public ResponseEntity<?> calculateMainTeam(TeamSelectionCriteria criteria) {
        List<Player> players = playerRepository.findAll();

        float powerPercentage = criteria.getPowerPercentage() != null ? criteria.getPowerPercentage() / 100.0f : 0.2f;
        float speedPercentage = criteria.getSpeedPercentage() != null ? criteria.getSpeedPercentage() / 100.0f : 0.3f;
        float passesPercentage = criteria.getPassesPercentage() != null ? criteria.getPassesPercentage() / 100.0f : 0.5f;
        int teamSize = criteria.getTeamSize() != null ? criteria.getTeamSize() : 7;

        List<Player> eligiblePlayers = players.stream()
                .filter(player -> player.getStats() != null && player.getStats().size() >= 3)
                .toList();

        if (eligiblePlayers.size() < teamSize) {
            return ResponseEntity.badRequest().body("There is not enough information.");
        }

        List<PlayerScore> playerScores = eligiblePlayers.stream()
                .map(player -> new PlayerScore(player.getId(), player.getName(),
                        player.calculateTotalScore(player.getStats(), powerPercentage, speedPercentage, passesPercentage)))
                .sorted(Comparator.comparingInt(PlayerScore::getScore).reversed())
                .limit(teamSize)
                .toList();

        return ResponseEntity.ok(playerScores);
    }
}