package algorithms.parkingGenerators;

import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by אלון on 21/01/2019.
 */
public class Main {
    public static void main(String[] args) {
        IParkingGenerator g = new MyParkingGenerator();
        Parking p = g.generate();
        p.print();
        ISearchable searchableParking = new SearchableParking(p);
        ParkingState ps = new ParkingState(p);
        ArrayList<ParkingState> pss = searchableParking.getAllPossibleStates(ps);

    }
}
