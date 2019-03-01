package algorithms.parkingGenerators;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alon on 01/01/2019.
 */
public class Parking {

    private static final HashMap<Character, String> carTypes = new HashMap<Character, String>() {{
        put('A', "\u001B[41m");
        put('B', "\u001B[42m");
        put('C', "\u001B[43m");
        put('D', "\u001B[44m");
        put('E', "\u001B[45m");
        put('F', "\u001B[46m");
        put('G', "\u001B[47m");
        put('H', "\u001B[42m");
        put('I', "\u001B[43m");
        put('J', "\u001B[44m");
        put('K', "\u001B[45m");
        put('L', "\u001B[46m");
        put('M', "\u001B[47m");
        put('N', "\u001B[42m");
        put('O', "\u001B[43m");
        put('o', "");
        put('x', "\u001B[40m");
    }};

    protected String parking;
    protected Position startPosition, goalPosition;

    public Parking(String newParking) {
        parking = newParking;
        this.startPosition = new Position(newParking);
        this.goalPosition = new Position("ooooooooooooooooAAoooooooooooooooooo");
    }

    public void print() {
        int k = 0;
        for (int row = 0; row < parking.length() ; row++) {
            System.out.print(carTypes.get(parking.charAt(k)) + " " + "\u001B[0m" + " ");
            if(k % 6 == 5)
                System.out.println();
            k++;
        }
    }

    public Position getStartPosition(){
        return startPosition;
    }

    public Position getGoalPosition(){
        return goalPosition;
    }

    @Override
    public String toString(){
        return parking;
    }
}
