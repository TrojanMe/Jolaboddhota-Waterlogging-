package algorithmCodes;

/**
 * Created by User on 10-Jul-17.
 */
public class SNode implements Comparable<SNode>{

    int id;
    double dist;

    public SNode(int id, double dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(SNode o) {
        if(this.dist < o.dist) return 1;
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
}
