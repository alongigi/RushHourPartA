package algorithms.search;
import algorithms.parkingGenerators.*;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Alon on 01/03/2019.
 */
public class SearchableParking implements ISearchable {

    private Parking parking;

    public SearchableParking(Parking parking) {
        if(parking != null)
            this.parking = parking;
        else {
            IParkingGenerator pg = new MyParkingGenerator();
            this.parking = pg.generate();
        }
    }

    public SearchableParking(Parking parking, int straightMoveCost, int diagonalMoveCost) {
        if(parking != null)
            this.parking = parking;
        else {
            IParkingGenerator pg = new MyParkingGenerator();
            this.parking = pg.generate();
        }
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> states = new ArrayList<AState>();
        ArrayList<Integer> blankPositions = new ArrayList<>();
        String parkingString = state.toString();
        int index = parkingString.indexOf('o');
        while (index >= 0) {
            blankPositions.add(index);
            index = parkingString.indexOf('o', index+1);
        }
        for (int position : blankPositions){
            leftRightMove(states, parkingString, position, state);
            upDownMove(states, parkingString, position, state);
        };
        return states;
    }

    private void upDownMove(ArrayList<AState> states, String parkingString, int position, AState state) {
        char[] stateChar = state.toString().toCharArray();
        if(position+18 <= 35 && stateChar[position+6] == stateChar[position+12] &&
                stateChar[position+12] == stateChar[position+18] && stateChar[position+6] != 'o'){
            stateChar[position] = parkingString.charAt(position+18);
            stateChar[position+18] = 'o';
//            System.out.println("A");
            String newParking = new String(stateChar);
            states.add(new ParkingState(newParking));
            stateChar = state.toString().toCharArray();
        }
        if(position-18 >=0 && parkingString.charAt(position-6) == parkingString.charAt(position-12) &&
                parkingString.charAt(position-12) == parkingString.charAt(position-18) && stateChar[position-6] != 'o') {
            stateChar[position] = parkingString.charAt(position-18);
            stateChar[position-18] = 'o';
//            System.out.println("B");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
        if(((position+18 <= 35 && stateChar[position+12] != stateChar[position+18]) || position/6 == 3) &&
                stateChar[position+6] == stateChar[position+12] &&
                stateChar[position+6] != 'o' && stateChar[position+6] != 'x'){
            stateChar[position] = parkingString.charAt(position+12);
            stateChar[position+12] = 'o';
//            System.out.println("C");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
        if(((position-18 >= 0 && stateChar[position-12] != parkingString.charAt(position-18)) || position/6 == 2 ) &&
                stateChar[position-6] == parkingString.charAt(position-12) &&
                stateChar[position-6] != 'o' && stateChar[position-6] != 'x') {
            stateChar[position] = parkingString.charAt(position-12);
            stateChar[position-12] = 'o';
//            System.out.println("D");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
    }

    private void leftRightMove(ArrayList<AState> states, String parkingString, int position, AState state) {
        char[] stateChar = state.toString().toCharArray();
        if(position%6 != 5 && position+3 <= 35 && stateChar[position+1] == stateChar[position+2] &&
                stateChar[position+2] == stateChar[position+3] && stateChar[position+1] != 'o'){
            stateChar[position] = parkingString.charAt(position+3);
            stateChar[position+3] = 'o';
//            System.out.println("1");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
        if(position%6 != 0 && position-3 >=0 && parkingString.charAt(position-1) == parkingString.charAt(position-2) &&
                parkingString.charAt(position-2) == parkingString.charAt(position-3) && stateChar[position-1] != 'o') {
            stateChar[position] = parkingString.charAt(position-3);
            stateChar[position-3] = 'o';
//            System.out.println("2");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
        if(position%6 != 5 && (position+3 <= 35 && stateChar[position+2] != stateChar[position+3] || position == 33) &&
                stateChar[position+1] == stateChar[position+2] &&
                stateChar[position+1] != 'o' && stateChar[position+1] != 'x'){
            stateChar[position] = parkingString.charAt(position+2);
            stateChar[position+2] = 'o';
//            System.out.println("3");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
        if(position%6 != 0 && ((position-3 >= 0 && stateChar[position-2] != stateChar[position-3]) || position == 2)
                && stateChar[position-1] == parkingString.charAt(position-2) &&
                stateChar[position-1] != 'o' &&
                stateChar[position-1] != 'x') {
            stateChar[position] = parkingString.charAt(position-2);
            stateChar[position-2] = 'o';
//            System.out.println("4");
            states.add(new ParkingState(new String(stateChar)));
            stateChar = state.toString().toCharArray();
        }
    }

    @Override
    public AState getStartPoint() {
        return new ParkingState(parking.getStartPosition());
    }

    @Override
    public AState getGoalPoint() {
        return new ParkingState(parking.getGoalPosition());
    }

    protected boolean isCheckedStateIsParent(AState state,int row,int column) {
        if(state.getParent() == null)
            return false;
        return state.getParent().state.toString().equals(state.toString());
    }

}

