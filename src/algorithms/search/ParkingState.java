package algorithms.search;

/**
 * Created by Alon on 4/14/2017.
 */
import algorithms.parkingGenerators.*;

public class ParkingState extends AState {
    public ParkingState(Parking parking)
    {
        super(parking);
    }

    public ParkingState(String newParking)
    {
        super(new Parking(newParking));
        Parking p = new Parking(newParking);
        System.out.println(newParking);
        p.print();
    }


    @Override
    public String toString() {
        return state.toString();
    }


    @Override
    protected double calculateMoveCost(AState parent)
    {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        ParkingState mazeState = (ParkingState) object;
        return toString().intern() == mazeState.toString().intern();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
