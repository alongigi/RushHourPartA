package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alon on 02/03/2019.
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] toByteArray) throws IOException {
        return read(toByteArray, 0, toByteArray.length);
    }

    @Override
    public int read(byte[] toByteArray, int off, int len) throws IOException {
        if (toByteArray == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > toByteArray.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
        int c ;
        int fileCursor = 0;
        try {
            fileCursor = getParkingInfo(toByteArray,fileCursor);
            int putChoice = 0; /* 0 for corridor, 1 for wall */
            while (fileCursor < len ) {
                c = read();
                if (c == -1) {
                    break;
                }
                while(c-- > 0) {
                    toByteArray[off + fileCursor++] = (byte) putChoice;
                }

                putChoice = ( putChoice + 1 ) % 2;

            }
        } catch (IOException ignored) {
        }


        return fileCursor;
    }

    private int getParkingInfo(byte[] toByteArray,int fileCursor){

        fileCursor =  readInfo(toByteArray,fileCursor);

//        fileCursor = getParkingRowSizeData(toByteArray,fileCursor);
//        fileCursor = getParkingColumnSizeData(toByteArray,fileCursor);
//        fileCursor = getStartPointRowData(toByteArray,fileCursor);
//        fileCursor = getStartPointColumnData(toByteArray,fileCursor);
//        fileCursor = getGoalPointRowData(toByteArray,fileCursor);
//        fileCursor = getGoalPointColumnData(toByteArray,fileCursor);

        return fileCursor;
    }

    private int getParkingRowSizeData(byte[] toByteArray, int fileCursor){
        return readInfo(toByteArray,fileCursor);
    }

    private int getParkingColumnSizeData(byte[] toByteArray, int fileCursor){
        return readInfo(toByteArray,fileCursor);
    }

    private int getStartPointRowData(byte[] toByteArray,int fileCursor){
        return readInfo(toByteArray,fileCursor);
    }

    private int getStartPointColumnData(byte[] toByteArray,int fileCursor){
        return readInfo(toByteArray,fileCursor);
    }

    private int getGoalPointRowData(byte[] toByteArray,int fileCursor){
        return readInfo(toByteArray,fileCursor);
    }

    private int getGoalPointColumnData(byte[] toByteArray,int fileCursor){
        return readInfo(toByteArray,fileCursor);
    }

    private int readInfo(byte[] toByteArray,int fileCursor){

        try {
            int capacity = read();
            toByteArray[fileCursor++] = (byte) capacity;
            while(fileCursor < toByteArray.length && capacity > 0){
                try {
                    while(capacity-- > 0){
                        int c = read();
                        if (c == -1) {
                            break;
                        }
                        if(fileCursor >= toByteArray.length)
                            System.arraycopy(toByteArray, 0, toByteArray, 0, toByteArray.length);

                        toByteArray[fileCursor++] = (byte)c;
                    }
                } catch (IOException ignored) {
                }
            }

        }catch (IOException ignored) {
        }

        return fileCursor;
    }
}
