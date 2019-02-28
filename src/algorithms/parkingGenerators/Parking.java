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
        put('C', "\u001B[44m");
        put('D', "\u001B[46m");
        put('E', "\u001B[43m");
        put('F', "\u001B[45m");
        put('G', "\u001B[47m");
        put('H', "\u001B[42m");
        put('I', "\u001B[44m");
        put('J', "\u001B[46m");
        put('K', "\u001B[43m");
        put('L', "\u001B[45m");
        put('M', "\u001B[47m");
        put('N', "\u001B[42m");
        put('o', "");
        put('x', "\u001B[40m");
    }};

    protected char[][] parking;
    protected Position startPosition, goalPosition;
    protected  String parkingString;

    public Parking(String newParking) {
        parkingString = newParking;
        int k = 0;
        this.parking = new char[6][6];
        for (int i = 0; i < 6 ; i++) {
            for (int j = 0; j < 6; j++) {
                parking[i][j] = newParking.charAt(k);
                if(newParking.charAt(k) == 'A' && newParking.charAt(k+1) == 'A')
                    this.startPosition = new Position(i, j+1);
                k++;
            }
        }
        this.goalPosition = new Position(2,5);
    }

    public void print() {
        for (int row = 0; row < parking.length ; row++) {
            for (int column = 0; column < parking[row].length ; column++) {
                System.out.print(carTypes.get(parking[row][column]) + " " + "\u001B[0m" + " ");
            }
            System.out.println();
        }
    }

    public Position getStartPosition(){
        return startPosition;
    }

    public Position getGoalPosition(){
        return goalPosition;
    }

    public char getCar(int row, int column){
        return parking[row][column];
    }

    @Override
    public String toString(){
        return parkingString;
    }
}
