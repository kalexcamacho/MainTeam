package mainTeam.controller;

import mainTeam.controller.TeamController;
import mainTeam.exceptions.InvalidTrainingDataException;
import mainTeam.model.TrainingData;
import mainTeam.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void addTrainingData_ValidData_ReturnsOk() {
        TrainingData validTrainingData = new TrainingData(); // crear datos válidos aquí

        ResponseEntity<Void> response = teamController.addTrainingData(validTrainingData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addTrainingData_InvalidData_ReturnsBadRequest() throws InvalidTrainingDataException {
        TrainingData invalidTrainingData = new TrainingData(); // Crear datos inválidos aquí

        doThrow(new InvalidTrainingDataException("Invalid training data"))
                .when(trainingService).addTrainingData(any(TrainingData.class));

        ResponseEntity<Void> response = teamController.addTrainingData(invalidTrainingData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addTrainingData_ServiceError_ReturnsInternalServerError() throws InvalidTrainingDataException {
        TrainingData validTrainingData = new TrainingData(); // Crear datos válidos aquí

        doThrow(new RuntimeException("Simulated service error"))
                .when(trainingService).addTrainingData(any(TrainingData.class));

        ResponseEntity<Void> response = teamController.addTrainingData(validTrainingData);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}