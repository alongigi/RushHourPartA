package algorithms.search;

/**
 * Created by Alon on 4/14/2017.
 */
public interface ISearchingAlgorithm {

    public Solution solve(ISearchable domain);
    /**
     * Returns the name of the search.
     * @return the name of the search.
     */
    public String getName();
    /**
     * Returns the number of nodes which were evaluated.
     * @return the number of nodes which were evaluated.
     */
    public String getNumberOfNodesEvaluated();

}
