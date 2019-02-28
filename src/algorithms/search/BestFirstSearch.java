package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Alon on 4/14/2017.
 */
public class BestFirstSearch extends BreadthFirstSearch {
    protected PriorityQueue<AState> Open = new PriorityQueue<AState>(new DoubleComparator());

    public static class DoubleComparator implements Comparator<AState> {
        @Override
        public int compare(AState o1, AState o2) {
            if(o1.getCost() > o2.getCost())
                return 1;
            return 0;
        }
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    /**
     * Removes from the queue the first state which has the lowest cost.
     * @return first state which has the lowest cost.
     */

    /**
     * The cose is considered in best first search.
     * @return true.
     */
    protected boolean isCostConsidered(){ return true; };
}
