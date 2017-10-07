package algorithmCodes;

/**
 * Created by User on 10-Jul-17.
 */
public class NodeforFF {

    int id;
    double x ;
    double y ;
    float cap;

    public NodeforFF(){

    }

    public NodeforFF(int id, double x, double y, float cap) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.cap = cap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float getCap() {
        return cap;
    }

    public void setCap(float cap) {
        this.cap = cap;
    }
}
