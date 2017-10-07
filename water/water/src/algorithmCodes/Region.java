package algorithmCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01-Jun-17.
 */
public class Region {


    public int nodecount;
    public List < Node > nodes ;
    public List<Integer> neighbours;

    public Region()
    {
        this.nodecount=0;
        nodes = new ArrayList<>(3000);
        neighbours = new ArrayList<>(3000);
    }
}
