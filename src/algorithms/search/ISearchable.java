package algorithms.search;


import java.util.ArrayList;

/**
 * Created by Alon on 4/14/2017.
 */
public interface ISearchable {

    /**
     * Returns a list of state neighbours.
     * @param state current state.
     * @return a list of state neighbours
     */
    ArrayList getAllPossibleStates(AState state);

    /**
     * Returns the start point.
     * @return the start point.
     */
    AState getStartPoint();

    /**
     * Returns the goal point.
     * @return the goal point.
     */
    AState getGoalPoint();


}
