package algorithms.parkingGenerators;

/**
 * Created by Alon on 03/02/2019.
 */
public class Position {
    String position;

    public Position(String position) {
        this.position = position;
    }

    public String toString() {
        return position;
    }

    @Override
    public boolean  equals (Object object) {
        Position pos = (Position) object;
        return  pos.equals(position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}
