package algorithmCodes;

import static java.lang.Math.pow;

/**
 * Created by User on 21-May-17.
 */
public class Node implements Comparable<Node>
{
    public int x , y , c,  waterlevel;
    public static int sorting_case=0;
    Node pv;

    public Node(int x, int y, int c, int waterlevel) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.waterlevel = waterlevel;
    }

    public Node()
    {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getWaterlevel() {
        return waterlevel;
    }

    public void setWaterlevel(int waterlevel) {
        this.waterlevel = waterlevel;
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


    public  void setPv(Node p){
        pv = p;
    }


    @Override
    public int compareTo(Node o) {
        if(sorting_case==0)
        {
            if(this.y < o.y) return 1;
            if(this.y == o.y)
            {
                if(this.x >= o.x) return 1;
                else return 0;
            }
            return 0;
        }
        else
        {
            if((this.c!=0 && o.c!=0)){
                int res = cross(pv , o, this);
                if(res ==-1)return 1;
                else if(res==0 && dist(pv,this)<dist(pv,o)) { this.c=1;  return 1;}
                else return 0;
            }
            return 0;
        }
    }


}
