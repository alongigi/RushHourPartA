package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alon on 5/17/2017.
 */
public class Server {

    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop = false;

    public Server(int port, int listeningInterval, IServerStrategy clientHandler) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = clientHandler;
    }

    /**
     * Starts the servers.
     */
    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    /**
     * Run the server.
     */
    private void runServer() {
        ExecutorService executor = Executors.newFixedThreadPool(ProjectProperties.getNumOfRunningThreads());
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            while(!stop) {
                try {
                    Socket aClient = server.accept(); // blocking call
                    executor.execute(new Thread(() -> {
                        handleClient(aClient);
                    }));
                } catch (SocketTimeoutException e) {
                }
            }
            server.close();
        } catch (IOException e) {
        }
        executor.shutdown();
    }

    /**
     * handles with the clients request
     * @param aClient the client's socket.
     */
    private void handleClient(Socket aClient) {
        try {
            serverStrategy.serverStrategy(aClient.getInputStream(), aClient.getOutputStream());
            aClient.getInputStream().close();
            aClient.getOutputStream().close();
            aClient.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * sets the stop boolean to true;
     * stops the server
     */
    public void stop() {
        stop = true;
    }

}
