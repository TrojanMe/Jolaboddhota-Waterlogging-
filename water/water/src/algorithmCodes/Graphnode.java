package algorithmCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01-Jun-17.
 */
public class Graphnode {
    int id;
    List<Integer> neighbours ;

    public Graphnode(){
        neighbours = new ArrayList<>(2000);
    }
}
