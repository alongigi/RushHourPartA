package algorithms.parkingGenerators;
import java.net.*;
import java.io.*;

/**
 * Created by אלון on 21/01/2019.
 */
public class MyParkingGenerator extends AParkingGenerator {
    /**
     * Creates a parking based on the database.
     * @return  parking.
     */
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
        System.out.println(newParking);
        return new Parking(newParking);
    }

    /**
     * get the name of generator.
     * @return the name of generator.
     */
    @Override
    public String getName() {
        return null;
    }
}
