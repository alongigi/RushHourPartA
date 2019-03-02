package algorithms.search;

/**
 * Created by Alon on 01/03/2019.
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
    }

    public ParkingState(Position position)
    {
        super(new Parking(position.toString()));
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
        ParkingState parkingState = (ParkingState) object;
        String goal = "ooooooooooooooooAAoooooooooooooooooo";
        if(parkingState.toString().intern() == goal.intern() || toString().intern() == goal.intern())
            return toString().indexOf('A') == parkingState.toString().indexOf('A');
        return toString().intern() == parkingState.toString().intern();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
