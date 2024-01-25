package mainTeam.controller;

import mainTeam.model.Player;
import mainTeam.model.TrainingData;
import mainTeam.repository.PlayerRepository;
import mainTeam.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main-team")
public class TeamController {

    private final TrainingService trainingService;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamController(TrainingService trainingService, PlayerRepository playerRepository) {
        this.trainingService = trainingService;
        this.playerRepository = playerRepository;
    }

    @PostMapping("/training")
    public ResponseEntity<?> addTrainingData(@RequestBody TrainingData data) {
        trainingService.addTrainingData(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team")
    public ResponseEntity<List<Player>> getTeam() {
        List<Player> teamList = playerRepository.findAll();
        return ResponseEntity.ok(teamList);
    }
}
