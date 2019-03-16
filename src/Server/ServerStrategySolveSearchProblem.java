package Server;

import algorithms.parkingGenerators.Parking;
import algorithms.parkingGenerators.Parking;
import algorithms.search.*;

import java.io.*;

/**
 * Created by Alon on 5/17/2017.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    Parking parking;
    ObjectInputStream fromClient;
    ObjectOutputStream toClient;
    String mazeFileName ;

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            startObjecInputOutputStream(inFromClient, outToClient);
            getFromClientMaze(fromClient);
            writeToClientSolutionToMaze(toClient);
        }
        catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Starts class objects.
     * @param inFromClient in put stream
     * @param outToClient out put stream
     * @throws IOException
     */
    private void startObjecInputOutputStream(InputStream inFromClient,OutputStream outToClient) throws IOException {
        fromClient = new ObjectInputStream(inFromClient);
        toClient = new ObjectOutputStream(outToClient);
        toClient.flush();
    }

    /**
     * Gets from client maze.
     * @param fromClient input stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void getFromClientMaze(ObjectInputStream fromClient) throws IOException, ClassNotFoundException {
        parking = (Parking) fromClient.readObject();
        mazeFileName = "parking - " + parking.getGoalPosition();
    }

    /**
     * Handles if the maze file is not exist.
     * @param mazeFileName the maze file name
     * @param toClient output stream
     * @throws IOException
     */
    private  void handleNotExistingMazeFile(String mazeFileName ,ObjectOutputStream toClient) throws IOException {
        ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(getMazeFolderName() + mazeFileName));
        Solution sol = getSearchParkingSolution();
        out.writeObject(sol);
        out.close();
        toClient.writeObject(new Solution(sol.getSolution()));

    }

    /**
     * Gets solution for the maze
     * @returnsolution
     */
    private Solution getSearchParkingSolution(){
        ISearchable domain = new SearchableParking(parking);
        ISearchingAlgorithm searcher = ProjectProperties.getSearcher();
        return searcher.solve(domain);
    }

    /**
     * Gets the maze's folder name
     * @return maze's folder name
     */
    private String getMazeFolderName(){
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * Writes to the client solution to the maz.e
     * @param toClient output stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void writeToClientSolutionToMaze(ObjectOutputStream toClient) throws IOException, ClassNotFoundException {
        if(isSolutionFileExist(getMazeFolderName() + mazeFileName))
            handExistingMazeFile(mazeFileName ,toClient);
        else
            handleNotExistingMazeFile(mazeFileName,toClient);
    }

    /**
     * Checks if solution for the maze exist
     * @param solutionFileName the maze solution file name.
     * @return
     */
    private boolean isSolutionFileExist(String solutionFileName){
        File f = new File( solutionFileName);
        return f.exists() && !f.isDirectory();
    }

    /**
     * Handles if the maze solution exists.
     * @param mazeFileName maze file name
     * @param toClient output stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void handExistingMazeFile(String mazeFileName,ObjectOutputStream toClient) throws IOException, ClassNotFoundException {
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(getMazeFolderName() + mazeFileName));
        Solution sol=(Solution) in.readObject();
        toClient.writeObject(new Solution(sol.getSolution()));
        in.close();

    }


}
