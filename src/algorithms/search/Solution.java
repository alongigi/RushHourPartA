package algorithms.search;

import java.util.ArrayList;

/**
 * Created by Alon on 01/03/2019.
 */
public class Solution {

    protected ArrayList<AState> solution;

    public Solution() {
        this.solution = new ArrayList<AState>();
    }

    public Solution(ArrayList<AState> solution) {
        this.solution = solution;
    }

    public void addState(AState state)
    {
        solution.add(state);
    }

    public ArrayList getSolutionPath() {
        return solution;
    }

    public int getSize() {
        return solution.size();
    }

    public AState getState(int i)
    {
        return solution.get(i);
    }

    public void deleteAllStates() {
        while(!solution.isEmpty()) {
            solution.remove(0);
        }
    }

    public ArrayList<AState> getSolution(){
        return solution;
    }
}
