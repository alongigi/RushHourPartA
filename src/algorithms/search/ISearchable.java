package algorithms.search;
import java.util.ArrayList;

/**
 * Created by Alon on 01/03/2019.
 */
public interface ISearchable {
    ArrayList getAllPossibleStates(AState state);
    AState getStartPoint();
    AState getGoalPoint();


}
