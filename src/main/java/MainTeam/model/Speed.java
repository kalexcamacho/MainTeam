package MainTeam.model;

import MainTeam.exceptions.InvalidTrainingDataException;

/**
 * Representa la velocidad calculada en base a la distancia y el tiempo.
 */
public class Speed {
    private String distance;   // mts
    private String time;       // seg

    public Speed() {
    }

    /**
     * Constructor para inicializar una instancia de Speed.
     * @param distance La distancia recorrida en metros.
     * @param time El tiempo tomado en segundos.
     * @throws InvalidTrainingDataException Si el tiempo es menor o igual a 0.
     */
    public Speed(String distance, String time) throws InvalidTrainingDataException {
        this.setDistance(distance);
        this.setTime(time);
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    /**
     * Establece el tiempo tomado para recorrer una distancia.
     * @param time El tiempo en segundos.
     * @throws InvalidTrainingDataException Si el tiempo es menor o igual a 0.
     */
    public void setTime(String time) throws InvalidTrainingDataException {
        if (Integer.parseInt(time) <= 0) {
            throw new InvalidTrainingDataException("Time must be greater than 0.");
        }
        this.time = time;
    }

    /**
     * Calcula la velocidad promedio.
     * @return La velocidad en metros por segundo (m/s).
     * @throws IllegalStateException Si el tiempo es 0.
     */
    public float calculateSpeed() {
        if (Integer.parseInt(time) == 0) {
            throw new IllegalStateException("Cannot calculate speed when time is 0.");
        }
        return (float) Integer.parseInt(distance) / Integer.parseInt(time);
    }
}
