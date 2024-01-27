package mainTeam.service;

import mainTeam.model.Player;
import mainTeam.model.PlayerScore;
import mainTeam.model.TeamSelectionCriteria;
import mainTeam.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TeamSelectionService {

//    @Autowired
    private final PlayerRepository playerRepository;

    public TeamSelectionService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public ResponseEntity<?> calculateMainTeam(TeamSelectionCriteria criteria) {
        List<Player> players = playerRepository.findAll();

        List<Player> eligiblePlayers = players.stream()
                .filter(player -> player.getStatsList() != null && player.getStatsList().size() >= 3)
                .toList();

        if (eligiblePlayers.size() < criteria.getTeamSize()) {
            return ResponseEntity.badRequest().body("There is not enough information.");
        }

        List<PlayerScore> playerScores = eligiblePlayers.stream()
                .map(player -> new PlayerScore(player.getId(), player.getName(),
                        player.calculateTotalScore(player.getStatsList(), criteria.getPowerPercentage(), criteria.getSpeedPercentage(), criteria.getPassesPercentage())))
                .sorted(Comparator.comparingInt(PlayerScore::getScore).reversed())
                .limit(criteria.getTeamSize())
                .toList();

        return ResponseEntity.ok(playerScores);
    }
}