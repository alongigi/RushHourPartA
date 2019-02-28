package algorithms.search;

import java.util.ArrayList;

/**
 * Created by Alon on 4/14/2017.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int evaluatedNodes = 0;

    /**
     * Returns a list of states which represent the path from the start point to the goal point.
     * @param domain the searchable.
     * @return a list of states which represent the path from the start point to the goal point.
     */
    public abstract Solution solve(ISearchable domain);

    /**
     * reverse the states in the solution.
     * @param solution the current solution
     */
    protected void reverseDirections(Solution solution)
    {
        ArrayList<AState> reversedSolution = new ArrayList<AState>();
        for (int i = solution.getSize() - 1; i >= 0; i--)
        {
            reversedSolution.add(solution.getState(i));
        }
        solution.deleteAllStates();
        for (int i = 0; i < reversedSolution.size(); i++)
        {
            solution.addState(reversedSolution.get(i));
        }
    }

    /**
     * Returns the number of nodes which were evaluated.
     * @return the number of nodes which were evaluated.
     */
    public String getNumberOfNodesEvaluated() {
        return "" + evaluatedNodes;
    }

    protected boolean isCostConsidered(){ return false; };




}
