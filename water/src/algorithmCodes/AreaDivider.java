package algorithmCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 01-Jun-17.
 */
public class AreaDivider {

    int l,w,m,r;
    int scale;
    int n;

    HashMap < Integer , Region > regionmap ;
    public List < Node > nodes ;

    public  AreaDivider(int scale , int n)
    {
        l=2500;
        w=2500;
        this.scale = scale;
        this.n=n;
        regionmap = new HashMap<>(n);
        nodes = new ArrayList<>(n);
    }

    public void process()
    {
        m = (((float)l/(float)scale)!=(l/scale)) ? (l/scale+1): l/scale ;
        r = (((float)w/(float)scale)!=(w/scale)) ? (w/scale+1): w/scale ;

        System.out.println("Size of the region grid is " +  m  +  "  X  " + r );
        int idx , idy;


        for(int i=0; i<m+1 ; i++) {
            for (int j = 0; j < r + 1; j++) {
                Region a = new Region();
                regionmap.put(i*l+j, a);
            }


        }

        for(int i=0;i<n;i++)
        {
            idx = ((nodes.get(i).x)/scale);
            idy = ((nodes.get(i).y)/scale);
            regionmap.get(idx*m+idy).nodes.add(nodes.get(i));
            regionmap.get(idx*m+idy).nodecount++;
            System.out.println( "Node : "  + nodes.get(i).x + "  " + nodes.get(i).y + " entering  region " + idx + " " + idy);
        }
    }

    public void areaConnector()
    {
        int i,j,k;
        for(i=0;i<m+1; i++) for(j=0;j<r+1;j++) {
        for(k=0;k<3;k++){if (regionmap.get(i*(m-1)+j-1+k).nodecount !=0 && (0<=(j-1+k)) &&(j-1+k<r+1) && (0<=i) && (i<m+1) ) regionmap.get(i*m+j).neighbours.add(i*(m-1)+j-1+k);}
        for(k=0;k<3;k++){if (regionmap.get(i*(m+1)+j-1+k).nodecount !=0 &&  (0<=(j-1+k)) &&(j-1+k<r+1) && (0<=i) && (i<m+1)) regionmap.get(i*m+j).neighbours.add(i*(m+1)+j-1+k);}
        for(k=0;k<3;k+=2) {if (regionmap.get(i*(m)+j-1+k).nodecount !=0 &&  (0<=(j-1+k)) &&(j-1+k<r+1) && (0<=i) && (i<m+1)) regionmap.get(i*m+j).neighbours.add(i*(m)+j-1+k); }
    }
        System.out.println("Area connection :");
        for(i=0;i<m+1; i++) for(j=0;j<r+1;j++) {
            System.out.println("Neighbors of : " + i*m+j + " are :    ");
        for(k=0;k<regionmap.get(i*m+j).neighbours.size();k++) System.out.println(regionmap.get(i*m+j).neighbours.get(k)  + "  ") ;
        System.out.println();
    }
    }


}
