package mainTeam.model;

import mainTeam.exceptions.InvalidTrainingDataException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpeedTest {

    @Test
    public void setTimeWithInvalidValueTest() {
        InvalidTrainingDataException exception = assertThrows(InvalidTrainingDataException.class, () -> {
            new Speed("30", "0");
        });

        assertEquals("Time must be greater than 0.", exception.getMessage());
    }

    @Test
    public void calculateSpeedTest() throws InvalidTrainingDataException {
        Speed speed = new Speed("30", "5");

        assertEquals(6.0f, speed.calculateSpeed());
    }

    @Test
    public void calculateSpeedWhenTimeIsZeroTest() {
        InvalidTrainingDataException exception = assertThrows(InvalidTrainingDataException.class, () -> {
            new Speed("30", "0");
        });

        assertEquals("Time must be greater than 0.", exception.getMessage());
    }
}