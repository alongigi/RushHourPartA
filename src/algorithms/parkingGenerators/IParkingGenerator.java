package algorithms.parkingGenerators;

/**
 * Created by Alon on 03/01/2019.
 */

public interface IParkingGenerator {

    /**
     *Creates new parking.
     * @return Parking returns an object of parking.
     */
    public Parking generate();

    /**
     * measures the time of creating a new parking.
     * @return the time of creating a new parking.
     */
    public long measureAlgorithmTimeMillis();

    /**
     * get the name of generator.
     * @return the name of generator.
     */
    public String getName();
}
