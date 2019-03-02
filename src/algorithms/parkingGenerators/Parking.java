package algorithms.parkingGenerators;

import java.util.HashMap;

/**
 * Created by Alon on 03/01/2019.
 */

public class Parking {

    protected String parking;
    protected Position startPosition, goalPosition;

    /**
     * A parking is created by using string.
     * @param newParking string.
     */
    public Parking(String newParking) {
        parking = newParking;
        this.startPosition = new Position(newParking);
        this.goalPosition = new Position("ooooooooooooooooAAoooooooooooooooooo");
    }

    public Parking(byte[] toByteArray){
        this.parking = new String(toByteArray);
    }

    /**
     * Print the parking.
     */
    public void print() {
        HashMap<Character, String> carTypes = new HashMap<Character, String>() {{
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
        int k = 0;
        for (int row = 0; row < parking.length() ; row++) {
            System.out.print(carTypes.get(parking.charAt(k)) + " " + "\u001B[0m" + " ");
            if(k % 6 == 5)
                System.out.println();
            k++;
        }
    }

    /**
     * return the start position.
     * @return the start position.
     */
    public Position getStartPosition(){
        return startPosition;
    }

    /**
     * return the goal position.
     * @return the goal position.
     */
    public Position getGoalPosition(){
        return goalPosition;
    }


    @Override
    public String toString(){
        return parking;
    }

    public byte[] toByteArray(){

        byte[] byteArray = parking.getBytes();

        return byteArray;
    }
}
