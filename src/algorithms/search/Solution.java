package algorithms.search;

import java.util.ArrayList;

/**
 * Created by Alon on 4/14/2017.
 */
public class Solution {

    protected ArrayList<AState> solution;

    public Solution() {
        this.solution = new ArrayList<AState>();
    }

    /**
     * Add to the solution a new state.
     * @param state The new state.
     */
    public void addState(AState state)
    {
        solution.add(state);
    }

    /**
     * Return the solution path.
     * @return the solution path.
     */
    public ArrayList getSolutionPath() {
        return solution;
    }

    /**
     * Returns the size of the soultion.
     * @return the size of the soultion.
     */
    public int getSize() {
        return solution.size();
    }

    /**
     * Return the state at the index i.
     * @param i The index.
     * @return the state at the index i
     */
    public AState getState(int i)
    {
        return solution.get(i);
    }

    /**
     * Delete all the states from the solution.
     */
    public void deleteAllStates() {
        while(!solution.isEmpty()) {
            solution.remove(0);
        }
    }
}
