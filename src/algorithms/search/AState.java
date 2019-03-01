package algorithms.search;

/**
 * Created by Alon on 01/03/2019.
 */

public abstract class AState <T> {
    protected T state;
    protected T Parent;
    protected double cost;

    public AState(T state)
    {
        this.state = state;
        this.cost = 0;
    }

    public abstract String toString();

    public  AState getParent() { return (AState) Parent; };

    public  void setParent(AState parent)
    {
        this.Parent = (T) parent;
        setCost(getCost()+ calculateMoveCost(parent) +parent.cost);
    }

    protected abstract double calculateMoveCost(AState parent);

    public void setCost(double cost) {
        if(cost < 0)
            this.cost = 0;
        else
            this.cost = cost;
    }

    public double getCost() { return cost; }
}