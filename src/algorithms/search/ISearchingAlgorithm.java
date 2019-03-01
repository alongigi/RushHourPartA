package algorithms.search;

/**
 * Created by Alon on 01/03/2019.
 */
public interface ISearchingAlgorithm {
    public Solution solve(ISearchable domain);
    public String getName();
    public String getNumberOfNodesEvaluated();

}
