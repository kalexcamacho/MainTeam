package mainTeam.service;

import mainTeam.model.Player;
import mainTeam.model.PlayerScore;
import mainTeam.model.TeamSelectionCriteria;
import mainTeam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamSelectionService {
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamSelectionService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public ResponseEntity<?> calculateStartingTeam(TeamSelectionCriteria criteria) {
        List<Player> players = playerRepository.findAll();

        // Filtrar jugadores que tienen al menos 3 registros de estadísticas
        List<Player> eligiblePlayers = players.stream()
                .filter(player -> player.getStatsList() != null && player.getStatsList().size() >= 3)
                .collect(Collectors.toList());

        // Verificar si hay suficientes jugadores con los datos requeridos
        if (eligiblePlayers.size() < criteria.getTeamSize()) {
            return ResponseEntity.badRequest().body("No hay suficientes jugadores con al menos 3 registros de estadísticas");
        }

        List<PlayerScore> playerScores = eligiblePlayers.stream()
                .map(player -> new PlayerScore(player.getId(), player.getName(),
                        player.calculateTotalScore(player.getStatsList(), criteria.getPowerPercentage(), criteria.getSpeedPercentage(), criteria.getPassesPercentage())))
                .sorted(Comparator.comparingInt(PlayerScore::getScore).reversed())
                .limit(criteria.getTeamSize())
                .collect(Collectors.toList());

        return ResponseEntity.ok(playerScores);
    }
}