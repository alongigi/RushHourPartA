package algorithms.search;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alon on 4/14/2017.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> Open = new LinkedList<AState>();
    protected HashSet<AState> Closed = new HashSet<AState>();

    /**
     * Finds and returns a path between the start state to the goal state by using breadth first search.
     *https://en.wikipedia.org/wiki/Breadth-first_search
     * @param domain the searchable.
     * @return a path between the start state to the goal state by using breadth first search.
     */
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

    /**
     * Return the state from the open list if exist,null otherwise.
     * @param s the wanted state.
     * @return the state from the open list if exist,null otherwise.
     */
    protected AState getStateFromOpenList(AState s)
    {
        List checkStateList  = Open.stream().filter(state -> !state.equals(s)).collect(Collectors.toList());
        if(checkStateList.size() > 0)
            return  (AState) checkStateList.get(0);
        else
            return null;
    }

    /**
     * Checks if the current state is in the open list.
     * @param state the current state.
     * @return true if the current state is in theopen list,false otherwise.
     */
    protected boolean isOpenContains(AState state)
    {
        return Open.contains(state);
    }

    /**
     * Returns true if the open list is empty,otherwise false.
     * @return true if the open list is empty,otherwise false.
     */
    protected boolean isOpenEmpty()
    {
        return Open.isEmpty();
    }

    /**
     * Adds the state to the open list.
     * @param state The added state.
     */
    protected void addToOpen(AState state){
        Open.add(state);
    }

    /**
     * Removes from the list the first state.
     * @return from the list the first state.
     */
    protected AState removeFromOpen()
    {
        evaluatedNodes++;
        return Open.remove();
    }

    /**
     * Removes from the list a state.
     * Return true if the state existed in the open list,false otherwise.
     * @param state The deleted state.
     * @return Return true if the state existed in the open list,false otherwise.
     */
    protected boolean removeFromOpen(AState state)
    {
        return Open.remove(state);
    }

    /**
     * Returns the name of the search.
     * @return the name of the search.
     */
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    /**
     * Creates a sulutuion,which contains the path from start position to goal position.
     * @param currentState the goal state.
     * @param domain
     */
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
