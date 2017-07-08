package algorithmCodes;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.util.Collections.sort;

/**
 * Created by User on 21-May-17.
 */
public class ConvexHull {

    Node pivotpoint;
    int sorting_case = 0;
    public List<Node> nodes = new ArrayList<Node>(3000);
    public List<Node> snodes = new ArrayList<Node>(3000);

    public void areavolumefinder(int n)
    {
        double avgwl = 0;
        int sum=0;
        for(int i=0; i<n ; i++)
        {
            sum+=(snodes.get(i).x*snodes.get((i+1)%n).y-snodes.get(i).y*snodes.get((i+1)%n).x);
            avgwl += (double)snodes.get(i).waterlevel;
        }
    }

    int cross(Node a , Node b , Node c)
    {
        Node p= new Node();
        Node q= new Node();
        p.x = b.x - a.x;
        p.y = b.y - a.y;
        q.x = c.x - a.x;
        q.y = c.y - a.y;
        int res = p.x*q.y - p.y*q.x;
        if(res==0) return 0;
        else if  ( res < 0) return -1;
        return 1;
    }


    double dist(Node a , Node  b)
    {
        return pow( pow(a.x-b.x , 2)+pow(a.y-b.y,2) ,0.5);
    }

    void furthercheck(int k)
    {
        while(k!=0)
        {
            int res = cross(snodes.get(k-2) , snodes.get(k-1) , snodes.get(k));
            if(res==1) return;
            if(res == -1 ) {
                snodes.get(k-1).x = snodes.get(k).x;
                snodes.get(k-1).y = snodes.get(k).y;
                snodes.get(k-1).c = snodes.get(k).c;
                snodes.get(k-1).waterlevel = snodes.get(k).waterlevel;
                k--;
            }
        }
    }

    public void procedure(int nv) {
        pivotpoint = new Node();
        Node t = new Node();
        sort(nodes);
        pivotpoint.x = snodes.get(0).x;
        pivotpoint.y = snodes.get(0).y;
        pivotpoint.c = snodes.get(0).c;
        pivotpoint.waterlevel = snodes.get(0).waterlevel;

        for(int i=0;i<nodes.size() ; i++)
        {
            nodes.get(i).sorting_case=1;
        }

        sort(nodes);
        int res;
        for (int i = 0; i < 2; i++) snodes.add(nodes.get(i));
        int k = 1;
        for (int i = 2; i < nv; i++) {
            t.x = snodes.get(i).x;
            t.y = snodes.get(i).y;
            t.c = snodes.get(i).c;
            t.waterlevel = snodes.get(i).waterlevel;
            if (t.c != 0) {
                res = cross(snodes.get(k - 1), snodes.get(k), t);
                if (res == -1) {
                    snodes.get(k).x = t.x;
                    snodes.get(k).y = t.y;
                    snodes.get(k).c = t.c;
                    snodes.get(k).waterlevel = t.waterlevel;
                    furthercheck(k);

                } else if (res == 0) {
                    if (dist(snodes.get(k - 1), snodes.get(k)) <= dist(snodes.get(k - 1), t)) {
                        snodes.get(k).x = t.x;
                        snodes.get(k).y = t.y;
                        snodes.get(k).c = t.c;
                        snodes.get(k).waterlevel = t.waterlevel;
                        furthercheck(k);
                    } else {
                        k++;
                        snodes.add(t);
                    }
                }
            }
        }

    }
}
