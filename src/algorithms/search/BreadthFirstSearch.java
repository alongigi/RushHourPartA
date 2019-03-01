package algorithms.search;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alon on 01/03/2019.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> Open = new LinkedList<AState>();
    protected HashSet<AState> Closed = new HashSet<AState>();

    @Override
    public Solution solve(ISearchable domain) {
        addToOpen(domain.getStartPoint());
        while(!isOpenEmpty()) {
            AState n = removeFromOpen();
            Closed.add(n);
            if(n.equals(domain.getGoalPoint())) {
                return makeSolution(n,domain);
            }
            for (Object object  : domain.getAllPossibleStates(n)) {
                AState s = (AState) object;
                if( !Closed.contains(s) && !isOpenContains(s) ) {
                    s.setParent(n);
                    addToOpen(s);
                }
                else if( isCostConsidered()  ) {
                   if( !Closed.contains(s) && !isOpenContains(s) ) {
                       s.setParent(n);
                       addToOpen(s);
                   }
                   else if ( !Closed.contains(s) && isOpenContains(s) ){
                       AState checkState = getStateFromOpenList(s);
                       if( checkState != null && checkState.getCost() < s.getCost()) {
                           checkState.setParent(n);
                           removeFromOpen(s);
                           addToOpen(checkState);
                       }
                   }
                }
            }
        }
        return null;
    }

    protected AState getStateFromOpenList(AState s)
    {
        List checkStateList  = Open.stream().filter(state -> !state.equals(s)).collect(Collectors.toList());
        if(checkStateList.size() > 0)
            return  (AState) checkStateList.get(0);
        else
            return null;
    }

    protected boolean isOpenContains(AState state)
    {
        return Open.contains(state);
    }

    protected boolean isOpenEmpty()
    {
        return Open.isEmpty();
    }

    protected void addToOpen(AState state){
        Open.add(state);
    }

    protected AState removeFromOpen()
    {
        evaluatedNodes++;
        return Open.remove();
    }

    protected boolean removeFromOpen(AState state)
    {
        return Open.remove(state);
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    private Solution makeSolution(AState currentState,ISearchable domain)
    {
        Solution solution = new Solution();
        while(!currentState.equals(domain.getStartPoint()))
        {
            solution.addState(currentState);
            currentState = currentState.getParent();
        }
        solution.addState(domain.getStartPoint());
        reverseDirections(solution);
        return solution;
    }
}
