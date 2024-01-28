package MainTeam.controller;

import MainTeam.exceptions.InvalidTrainingDataException;
import MainTeam.model.TrainingData;
import MainTeam.service.TrainingService;
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

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void addTrainingData_ValidData_ReturnsOk() {
        TrainingData validTrainingData = new TrainingData();

        ResponseEntity<?> response = teamController.addTrainingData(validTrainingData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addTrainingData_InvalidData_ReturnsBadRequest() throws InvalidTrainingDataException {
        TrainingData invalidTrainingData = new TrainingData(); // Crear datos inválidos aquí

        doThrow(new InvalidTrainingDataException("Invalid training data"))
                .when(trainingService).addTrainingData(any(TrainingData.class));

        ResponseEntity<?> response = teamController.addTrainingData(invalidTrainingData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addTrainingData_ServiceError_ReturnsInternalServerError() throws InvalidTrainingDataException {
        TrainingData validTrainingData = new TrainingData(); // Crear datos válidos aquí

        doThrow(new RuntimeException("Simulated service error"))
                .when(trainingService).addTrainingData(any(TrainingData.class));

        ResponseEntity<?> response = teamController.addTrainingData(validTrainingData);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}