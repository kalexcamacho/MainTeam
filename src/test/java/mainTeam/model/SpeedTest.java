package mainTeam.model;

import mainTeam.exceptions.InvalidTrainingDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpeedTest {

    @Test
    public void getDistanceTest() {
        Speed speed = new Speed();
        speed.setDistance(30);

        assertEquals(30, speed.getDistance());
    }

    @Test
    public void getTimeTest() throws InvalidTrainingDataException {
        Speed speed = new Speed();
        speed.setTime(5);

        assertEquals(5, speed.getTime());
    }

    @Test
    public void setTimeWithInvalidValueTest() {
        Speed speed = new Speed();

        InvalidTrainingDataException exception = assertThrows(InvalidTrainingDataException.class, () -> {
            speed.setTime(0);
        });

        assertEquals("Time must be greater than 0.", exception.getMessage());
    }

    @Test
    public void calculateSpeedTest() throws InvalidTrainingDataException {
        Speed speed = new Speed();
        speed.setDistance(30);
        speed.setTime(5);

        assertEquals(6.0f, speed.calculateSpeed());
    }

    @Test
    public void calculateSpeedWhenTimeIsZeroTest() {
        Speed speed = new Speed(30, 0); // Establece time a 0 directamente

        IllegalStateException exception = assertThrows(IllegalStateException.class, speed::calculateSpeed);

        assertEquals("Cannot calculate speed when time is 0.", exception.getMessage());
    }
}