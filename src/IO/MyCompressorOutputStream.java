package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alon on 02/03/2019.
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;


    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(compress(b));
    }

    public static byte[] compress(byte[] b){

//        List<Byte> listCompressedData = new ArrayList<>();
//
//        int fileCursor = putRowSizeData(listCompressedData,b);
//        fileCursor = putColumnSizeData(listCompressedData,b,fileCursor);
//        fileCursor = putStartPointRowData(listCompressedData,b,fileCursor);
//        fileCursor = putStartPointColumnData(listCompressedData,b,fileCursor);
//        fileCursor = putGoalPointRowData(listCompressedData,b,fileCursor);
//        fileCursor = putGoalPointColumnData(listCompressedData,b,fileCursor);
//        putCorridorsAndWalls(listCompressedData,b,fileCursor);
//
//        return convertByteListTobyteArray(listCompressedData);
        return b;
    }

    private static byte[] convertByteListTobyteArray(List<Byte> listCompressedData){
        byte[] result = new byte[listCompressedData.size()];
        for(int i = 0; i < listCompressedData.size(); i++) {
            result[i] = listCompressedData.get(i);
        }

        return result;
    }

    private static int putRowSizeData(List<Byte> listCompressedData, byte[] b){
        return insertListData(listCompressedData,b, 0);
    }

    private static int putColumnSizeData(List<Byte> listCompressedData,byte[] b,int fileCursor){
        return insertListData(listCompressedData,b,fileCursor);
    }

    private static int putStartPointRowData(List<Byte> listCompressedData,byte[] b,int fileCursor){
        return insertListData(listCompressedData,b,fileCursor);
    }

    private static int putStartPointColumnData(List<Byte> listCompressedData,byte[] b,int fileCursor){
        return insertListData(listCompressedData,b,fileCursor);
    }

    private static int putGoalPointRowData(List<Byte> listCompressedData,byte[] b,int fileCursor){
        return insertListData(listCompressedData,b,fileCursor);
    }

    private static int putGoalPointColumnData(List<Byte> listCompressedData,byte[] b,int fileCursor){
        return insertListData(listCompressedData,b,fileCursor);
    }

    private static int insertListData(List<Byte> listCompressedData,byte[] b,int fileCursor){
        int capacity = b[fileCursor++];
        listCompressedData.add((byte)capacity);
        while(capacity-- > 0)
            listCompressedData.add(b[fileCursor++]);

        return fileCursor ;
    }

    private static void putCorridorsAndWalls(List<Byte> listCompressedData,byte[] b,int index){
        int putChoice = 0; /* 0 for corridor, 1 for wall */
        while(index < b.length){
            int capacity = 0;
            if(putChoice != b[index]){
                listCompressedData.add((byte)0);
                putChoice = ( putChoice + 1 ) % 2;
                continue;
            }
            while(index < b.length && capacity <= 127 && b[index] == putChoice) {
                capacity++;
                index++;
            }
            listCompressedData.add((byte)capacity);
            putChoice = ( putChoice + 1 ) % 2;
        }
    }
}
