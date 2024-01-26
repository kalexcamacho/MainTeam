package mainTeam.service;

import mainTeam.exceptions.InvalidTrainingDataException;
import mainTeam.model.Player;
import mainTeam.model.Speed;
import mainTeam.model.Stats;
import mainTeam.model.TrainingData;
import mainTeam.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AddTrainingData() throws InvalidTrainingDataException {
        Speed speed1 = new Speed();
        speed1.setDistance(100);
        speed1.setTime(10);

        Stats stats1 = new Stats();
        stats1.setPower(50);
        stats1.setSpeed(speed1);
        stats1.setPasses(10);

        Player demoPlayer = new Player();
        demoPlayer.setId("1");
        demoPlayer.setName("Existing Player");
        demoPlayer.setStatsList(new ArrayList<>());
        demoPlayer.getStatsList().add(stats1);

        TrainingData trainingData = new TrainingData();
        List<Player> players = new ArrayList<>();
        players.add(demoPlayer);
        trainingData.setPlayers(players);

        when(playerRepository.findById("1")).thenReturn(Optional.of(demoPlayer));

        trainingService.addTrainingData(trainingData);

        verify(playerRepository, times(1)).save(demoPlayer);

        assertEquals(1, demoPlayer.getStatsList().size());
    }

    @Test
    public void testAddTrainingDataWhenPlayerDoesNotExist() throws InvalidTrainingDataException {
        Player nonExistingPlayer = new Player();
        nonExistingPlayer.setId("1");
        nonExistingPlayer.setName("Non Existing Player");
        nonExistingPlayer.setStatsList(new ArrayList<>());

        TrainingData trainingData = new TrainingData();
        trainingData.setPlayers(List.of(nonExistingPlayer));

        when(playerRepository.findById("1")).thenReturn(Optional.empty());

        trainingService.addTrainingData(trainingData);

        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(playerCaptor.capture());

        Player savedPlayer = playerCaptor.getValue();
        assertNotNull(savedPlayer);
        assertEquals("1", savedPlayer.getId());
        assertEquals("Non Existing Player", savedPlayer.getName());
        assertEquals(0, savedPlayer.getStatsList().size()); // No se añadieron estadísticas
    }

    @Test
    public void AddNewStatsToExistingPlayer() throws InvalidTrainingDataException {
        Speed speed1 = new Speed();
        speed1.setDistance(100);
        speed1.setTime(10);

        Stats stats1 = new Stats();
        stats1.setPower(50);
        stats1.setSpeed(speed1);
        stats1.setPasses(10);

        Player demoPlayer = new Player();
        demoPlayer.setId("1");
        demoPlayer.setName("Existing Player");
        demoPlayer.setStatsList(new ArrayList<>());
        demoPlayer.getStatsList().add(stats1);

        Speed speed2 = new Speed();
        speed2.setDistance(100);
        speed2.setTime(10);

        Stats stats2 = new Stats();
        stats2.setPower(50);
        stats2.setSpeed(speed2);
        stats2.setPasses(10);

        Player demoPlayerWithNewStats = new Player();
        demoPlayerWithNewStats.setId("1");
        demoPlayerWithNewStats.setName("Existing Player");
        demoPlayerWithNewStats.setStatsList(new ArrayList<>());
        demoPlayerWithNewStats.getStatsList().add(stats2);

        TrainingData trainingData = new TrainingData();
        trainingData.setPlayers(List.of(demoPlayerWithNewStats));

        when(playerRepository.findById("1")).thenReturn(Optional.of(demoPlayer));

        trainingService.addTrainingData(trainingData);

        verify(playerRepository, times(1)).save(demoPlayer);

        assertEquals(2, demoPlayer.getStatsList().size());
        assertTrue(demoPlayer.getStatsList().contains(stats2));
    }
}