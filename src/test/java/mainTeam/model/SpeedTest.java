package mainTeam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedTest {

    @Test
    public void testCalculateSpeed() {
        Speed speed = new Speed();
        speed.setDistance(30);
        speed.setTime(5);

        assertEquals(6.0f, speed.calculateSpeed());
    }
}