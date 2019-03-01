package algorithms.search;

import java.util.ArrayList;

/**
 * Created by Alon on 01/03/2019.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int evaluatedNodes = 0;

    public abstract Solution solve(ISearchable domain);

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

    public String getNumberOfNodesEvaluated() {
        return "" + evaluatedNodes;
    }

    protected boolean isCostConsidered(){ return false; };




}
