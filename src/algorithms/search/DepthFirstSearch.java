package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Alon on 4/14/2017.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {


    /**
     * Finds and returns a path between the start state to the goal state by using depth first search.
     *https://en.wikipedia.org/wiki/Depth-first_search
     * @param domain the searchable.
     * @return a path between the start state to the goal state by using depth first search.
     */
    @Override
    public Solution solve(ISearchable domain) {
        Solution solution = new Solution();
        ArrayList<AState> visited = new ArrayList<AState>();
        Stack s = new Stack();
        s.push(domain.getStartPoint());
        while(!s.isEmpty()) {
            AState currentState = (AState) s.pop();
            if(!visited.contains(currentState)) {
                visited.add(currentState);
                solution.addState(currentState);
                if(domain.getGoalPoint().equals(currentState)) {
                    evaluatedNodes = visited.size();
                    return solution;
                }
                for (Object object  : domain.getAllPossibleStates(currentState) ) {
                    AState state = (AState) object;
                    if(!visited.contains(state))
                        s.push(state);
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
