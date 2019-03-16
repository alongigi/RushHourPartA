package Server;

import algorithms.parkingGenerators.IParkingGenerator;
import algorithms.parkingGenerators.MyParkingGenerator;
import algorithms.parkingGenerators.IParkingGenerator;
import algorithms.parkingGenerators.MyParkingGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Properties;

/**
 * Created by Alon on 5/26/2017.
 */
public  class ProjectProperties {
    private static int threadPoolSize = 5;
    private static ISearchingAlgorithm searcher = new BreadthFirstSearch();
    private static IParkingGenerator parkingGenerator = new MyParkingGenerator();
    private static Properties prop = new Properties();

    /**
     * Sets the key to the wanted value in the properties.
     * Thread_pool_size for thread pool size.
     * Searcher for the searcher - BreadthFirstSearch \ BestFirstSearch \ DepthFirstSearch
     * Maze_Generator for the maze generator type - MyMazeGenerator \ SimpleMazeGenerator
     */
    private ProjectProperties() {}

    /**
     * Gets the number of running threads.
     *
     * @return the thread pool size
     */
    static int getNumOfRunningThreads()
    {
        saveProperties();
        try(InputStream input = new FileInputStream("config.properties"))
        {
            threadPoolSize = Integer.parseInt(prop.getProperty("Thread_pool_size"));
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveProperties();
        return threadPoolSize;
    }

    /**
     *
     * @return the wanted searcher
     */
    static ISearchingAlgorithm getSearcher()
    {
        saveProperties();
        try(InputStream input = new FileInputStream("config.properties"))
        {
            searcher = getSearcher(prop.getProperty("Searcher"));
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searcher;
    }

    /**
     *
     * @param wantedSearcher the searcher which the user needed.
     * @return the searching algorithm
     */
    private static ISearchingAlgorithm getSearcher(String wantedSearcher) {
        switch (wantedSearcher) {
            case "BreadthFirstSearch":
                return new BreadthFirstSearch();
            default:
                return new DepthFirstSearch();
        }
    }

    /**
     *
     * @return the maze generator.
     */
    static IParkingGenerator getParkingGenerator(){
        saveProperties();
        try(InputStream input = new FileInputStream("config.properties"))
        {
            parkingGenerator = getParkingGenerator(prop.getProperty("Searcher"));
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  parkingGenerator;
    }

    /**
     *
     * @param wantedSearcher the searcher which the user needed.
     * @return maze generator.
     */
    private static IParkingGenerator getParkingGenerator(String wantedSearcher) {
            return new MyParkingGenerator();
    }

    /**
     * Saves the properties.
     */
    private static void saveProperties() {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            prop.setProperty("Searcher", searcher.getName());
            prop.setProperty("Thread_pool_size", "" + threadPoolSize);
            prop.setProperty("Parking_Generator", parkingGenerator.getName());
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
