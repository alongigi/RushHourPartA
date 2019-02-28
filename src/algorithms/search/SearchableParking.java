package algorithms.search;
import algorithms.parkingGenerators.*;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Alon on 4/14/2017.
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
        ArrayList<Integer> blankPlace = new ArrayList<>();
        ArrayList<AState> states = new ArrayList<AState>();
        String parkingString = state.toString();
        int index = parkingString.indexOf('o');
        while (index >= 0) {
            blankPlace.add(index);
            index = parkingString.indexOf('o', index + 1);
        }
        //
        for (int position : blankPlace){
            char[] stateChar = state.toString().toCharArray();
            //right position%6 >= 0 && position % 6 != 0
            if(position%6 != 5 && position+3 <= 35 && stateChar[position+1] == stateChar[position+2] &&
                    stateChar[position+2] == stateChar[position+3] && stateChar[position+1] != 'o'){
                stateChar[position] = parkingString.charAt(position+3);
                stateChar[position+3] = 'o';
                System.out.println("1");
                String newParking = new String(stateChar);
                states.add(new ParkingState(newParking));
            }
            //left position%6 != 5 &&
            else if(position-3 >=0 && parkingString.charAt(position-1) == parkingString.charAt(position-2) &&
                    parkingString.charAt(position-2) == parkingString.charAt(position-3) && stateChar[position-1] != 'o') {
                stateChar[position] = parkingString.charAt(position-3);
                stateChar[position-3] = 'o';
                System.out.println("2");
                String newParking = new String(stateChar);
                states.add(new ParkingState(newParking));
            }
            //position%6 >= 0 && position % 6 != 0 position % 6 != 0 &&
            else if(position%6 != 5 && position+2 <= 35 && stateChar[position+1] == stateChar[position+2]
                    && stateChar[position+1] != 'o'){
                stateChar[position] = parkingString.charAt(position+2);
                stateChar[position+2] = 'o';
                System.out.println("3");
                String newParking = new String(stateChar);
                states.add(new ParkingState(newParking));
            }
            else if(position % 6 != 0 && position-2 >= 0 && stateChar[position-1] == parkingString.charAt(position-2) && stateChar[position-1] != 'o') {
                stateChar[position] = parkingString.charAt(position-2);
                stateChar[position-2] = 'o';
                System.out.println("4");
                String newParking = new String(stateChar);
                states.add(new ParkingState(newParking));
            }
        };
        return states;
    }

    @Override
    public AState getStartPoint() {
        return null;
    }

    @Override
    public AState getGoalPoint() {
        return null;
    }

    protected boolean isCheckedStateIsParent(AState state,int row,int column) {
        if(state.getParent() == null)
            return false;
        return state.getParent().state.toString().equals(state.toString());
    }

}

