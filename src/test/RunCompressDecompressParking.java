package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.parkingGenerators.AParkingGenerator;
import algorithms.parkingGenerators.MyParkingGenerator;
import algorithms.parkingGenerators.Parking;

import java.io.*;
import java.util.Arrays;

/**
 * Created by אלון on 02/03/2019.
 */
public class RunCompressDecompressParking {
    public static void main(String[] args) {

        String parkingFileName = "savedParking.parking";
        AParkingGenerator parkingGenerator = new MyParkingGenerator();
        Parking parking = parkingGenerator.generate(); //Generate new parking

        try {
            // save parking to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(parkingFileName));
            out.write(parking.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte savedParkingBytes[] = new byte[0];
        try {
            //read parking from file
            savedParkingBytes = new byte[parking.toByteArray().length];
            InputStream in = new MyDecompressorInputStream(new FileInputStream(parkingFileName));
            in.read(savedParkingBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parking loadedParking = new Parking(savedParkingBytes);

        boolean areParkingEquals = Arrays.equals(loadedParking.toByteArray(), parking.toByteArray());
        System.out.println(String.format("Parking equal: %s",areParkingEquals)); //parking should be equal to loadedParking
    }
}
