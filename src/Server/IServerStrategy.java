package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Alon on 5/17/2017.
 */
public interface IServerStrategy {
    /**
     *
     * @param inFromClient client input stream
     * @param outToClient output stream.
     */
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
