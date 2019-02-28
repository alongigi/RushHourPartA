package algorithms.parkingGenerators;
import java.net.*;
import java.io.*;

/**
 * Created by אלון on 21/01/2019.
 */
public class MyParkingGenerator extends AParkingGenerator {
    @Override
    public Parking generate(){
        String newParking = "";
        try {
            URL oracle = new URL("https://www.michaelfogleman.com/rushserver/random.json");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));
            newParking = in.readLine().split("\"")[3];
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Parking(newParking);
    }
}
