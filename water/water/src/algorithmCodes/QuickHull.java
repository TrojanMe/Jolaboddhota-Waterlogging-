package algorithmCodes;
import java.util.ArrayList;
/**
 * Created by User on 09-Jul-17.
 */


public class QuickHull {


    public ArrayList<Node> quickHull(ArrayList<Node> Nodes) {
        ArrayList<Node> convexHull = new ArrayList<Node>();
        if (Nodes.size() < 3)
            return (ArrayList) Nodes.clone();

        int minNode = -1, maxNode = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < Nodes.size(); i++) {
            if (Nodes.get(i).x < minX) {
                minX = Nodes.get(i).x;
                minNode = i;
            }
            if (Nodes.get(i).x > maxX) {
                maxX = Nodes.get(i).x;
                maxNode = i;
            }
        }
        Node A = Nodes.get(minNode);
        Node B = Nodes.get(maxNode);
        convexHull.add(A);
        convexHull.add(B);
        Nodes.remove(A);
        Nodes.remove(B);

        ArrayList<Node> leftSet = new ArrayList<Node>();
        ArrayList<Node> rightSet = new ArrayList<Node>();

        for (int i = 0; i < Nodes.size(); i++) {
            Node p = Nodes.get(i);
            if (NodeLocation(A, B, p) == -1)
                leftSet.add(p);
            else if (NodeLocation(A, B, p) == 1)
                rightSet.add(p);
        }
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);

        return convexHull;
    }

    public int distance(Node A, Node B, Node C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0)
            num = -num;
        return num;
    }

    public void hullSet(Node A, Node B, ArrayList<Node> set,
                        ArrayList<Node> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        if (set.size() == 1) {
            Node p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        int dist = Integer.MIN_VALUE;
        int furthestNode = -1;
        for (int i = 0; i < set.size(); i++) {
            Node p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestNode = i;
            }
        }
        Node P = set.get(furthestNode);
        set.remove(furthestNode);
        hull.add(insertPosition, P);

        // Determine who's to the left of AP
        ArrayList<Node> leftSetAP = new ArrayList<Node>();
        for (int i = 0; i < set.size(); i++) {
            Node M = set.get(i);
            if (NodeLocation(A, P, M) == 1) {
                leftSetAP.add(M);
            }
        }


        ArrayList<Node> leftSetPB = new ArrayList<Node>();
        for (int i = 0; i < set.size(); i++) {
            Node M = set.get(i);
            if (NodeLocation(P, B, M) == 1) {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);

    }

    public int NodeLocation(Node A, Node B, Node P) {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }

}
