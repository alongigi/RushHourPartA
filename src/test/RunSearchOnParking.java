package test;

import algorithms.parkingGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnParking {
    public static void main(String[] args) {
        IParkingGenerator pg = new MyParkingGenerator();
        Parking parking = pg.generate();
        SearchableParking searchableParking = new SearchableParking(parking);
        solveProblem(searchableParking, new BreadthFirstSearch());
        solveProblem(searchableParking, new DepthFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
    }
}