package algorithms.parkingGenerators;


/**
 * Created by Alon on 02/01/2019.
 */
public abstract class AParkingGenerator implements IParkingGenerator {
    @Override
    public abstract Parking generate();

    @Override
    public long measureAlgorithmTimeMillis() {
        long start = System.currentTimeMillis();
        generate();
        long end = System.currentTimeMillis();
        return end - start;
    }
}
