package algorithms.parkingGenerators;

/**
 * Created by Alon on 01/01/2019.
 */
public interface IParkingGenerator {
    public Parking generate();
    public long measureAlgorithmTimeMillis();
}
