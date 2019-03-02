package algorithms.parkingGenerators;

/**
 * Created by Alon on 03/01/2019.
 */

public abstract class AParkingGenerator implements IParkingGenerator {

    /**
     * measures the time runing of generating parking.
     * @return the time runing of generating parking.
     */
    public long measureAlgorithmTimeMillis() {
        long start = System.currentTimeMillis();
        generate();
        long end = System.currentTimeMillis();
        return end - start;
    }
}
