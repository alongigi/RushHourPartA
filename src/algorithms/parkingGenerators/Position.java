package algorithms.parkingGenerators;

/**
 * Created by Alon on 03/02/2019.
 */
public class Position {
    protected int row;
    protected int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }


    public int getRowIndex()
    {
        return row;
    }

    public int getColumnIndex()
    {
        return column;
    }

    public String toString() {
        return "{" + getRowIndex() + "," + getColumnIndex() + "}";
    }

    @Override
    public boolean  equals (Object object) {
        Position pos = (Position) object;
        return  pos.getColumnIndex() == column && pos.getRowIndex() == row;
    }

    @Override
    public int hashCode() {
        return ("" + row).hashCode() + ("" + column).hashCode();
    }
}
