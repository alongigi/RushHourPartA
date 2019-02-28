package test;

import algorithms.parkingGenerators.IParkingGenerator;
import algorithms.parkingGenerators.MyParkingGenerator;
import algorithms.parkingGenerators.Parking;
import algorithms.parkingGenerators.Position;

public class RunParkingGenerator {
    public static void main(String[] args) {
        testParkingGenerator(new MyParkingGenerator());
    }

    private static void testParkingGenerator(IParkingGenerator parkingGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Parking generation time(ms): %s", parkingGenerator.measureAlgorithmTimeMillis()));
        // generate another maze
        Parking parking = parkingGenerator.generate();
        // prints the parking
        parking.print();
        // get the parking entrance
        Position startPosition = parking.getStartPosition();
        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", parking.getGoalPosition()));
    }
}