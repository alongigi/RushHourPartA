package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Alon on 01/03/2019.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

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
