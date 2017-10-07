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
    private  List < Node > nodes ;
    private List < BigRegion > bigRegions;

    public  AreaDivider(int scale , int n)
    {
        l=2500;
        w=2500;
        this.scale = scale;
        this.n=n;
        regionmap = new HashMap<>(n);
        nodes = new ArrayList<>(n);
        bigRegions = new ArrayList<>(1000);
    }

    public void process()
    {
        m = (((float)l/(float)scale)!=(l/scale)) ? (l/scale+1): l/scale ;
        r = (((float)w/(float)scale)!=(w/scale)) ? (w/scale+1): w/scale ;

        //System.out.println("Size of the region grid is " +  m  +  "  X  " + r );
        int idx , idy;


        //System.out.println("Printing all regions : ");

        for(int i=0; i<m+1 ; i++) {
            for (int j = 0; j < r+1; j++) {
                Region a = new Region();
                regionmap.put(i*m+j, a);
                System.out.println(i*m+j);
            }


        }

        //System.out.println("Size of nodes : "+ nodes.size());

        for(int i=0;i<n;i++)
        {
            idx = ((nodes.get(i).x)/scale);
            idy = ((nodes.get(i).y)/scale);
            regionmap.get(idx*m+idy).nodes.add(nodes.get(i));
            regionmap.get(idx*m+idy).nodecount++;
            //System.out.println( "Node : "  + nodes.get(i).x + "  " + nodes.get(i).y + " entering  region " + idx + " " + idy);
        }
    }

    public void   setNodes (List < Node > nodes){
        this.nodes = nodes;
    }


   public void areaConnector() {

       int d = (m + 1) * (r + 1);

       int i, j, k;
       for (i = 0; i < m + 1; i++) {
           for (j = 0; j < r + 1; j++) {

               for (k = 0; k < 3; k++) {

                   if (0 <= (i * (m - 1) + j - 1 + k) && (i * (m - 1) + j - 1 + k) < d) {
                       if (regionmap.get(i * (m - 1) + j - 1 + k) != null && regionmap.get(i * (m - 1) + j - 1 + k).nodecount != 0 && (i * m + j) != (i * (m - 1) + j - 1 + k)) {

                           int flag = 0;
                           for (int we = 0; we < regionmap.get(i * m + j).neighbours.size(); we++) {
                               if (regionmap.get(i * m + j).neighbours.get(we) == i * (m - 1) + j - 1 + k) {
                                   flag = 1;
                                   break;
                               }
                           }

                           if (flag == 0)
                               regionmap.get(i * m + j).neighbours.add(i * (m - 1) + j - 1 + k);
                       }

                   }
               }
               for (k = 0; k < 3; k++) {
                   if (0 <= (i * (m + 1) + j - 1 + k) && (i * (m + 1) + j - 1 + k) < d) {
                       if (regionmap.get(i * (m + 1) + j - 1 + k) != null && regionmap.get(i * (m + 1) + j - 1 + k).nodecount != 0 && (i * m + j) != (i * (m + 1) + j - 1 + k)) {

                           int flag = 0;
                           for (int we = 0; we < regionmap.get(i * m + j).neighbours.size(); we++) {
                               if (regionmap.get(i * m + j).neighbours.get(we) == i * (m + 1) + j - 1 + k) {
                                   flag = 1;
                                   break;
                               }
                           }

                           if (flag == 0)
                               regionmap.get(i * m + j).neighbours.add(i * (m + 1) + j - 1 + k);
                       }
                   }

               }
                   for (k = 0; k < 3; k += 2) {
                       if (0 <= (i * (m) + j - 1 + k) && (i * (m) + j - 1 + k) < d) {
                           if (regionmap.get(i * (m) + j - 1 + k) != null && regionmap.get(i * (m) + j - 1 + k).nodecount != 0 && (i * m + j) != (i * (m) + j - 1 + k)) {

                               int flag = 0;
                               for (int we = 0; we < regionmap.get(i * m + j).neighbours.size(); we++) {
                                   if (regionmap.get(i * m + j).neighbours.get(we) == i * (m) + j - 1 + k) {
                                       flag = 1;
                                       break;
                                   }
                               }

                               if (flag == 0)
                                   regionmap.get(i * m + j).neighbours.add(i * (m) + j - 1 + k);
                           }
                       }
                   }


               System.out.println("Area connection :");
               for (i = 0; i < m + 1; i++)
                   for (j = 0; j < r + 1; j++) {
                       System.out.println("Neighbors of : " + (i * m + j) + " are :    ");
                       for (k = 0; k < regionmap.get(i * m + j).neighbours.size(); k++)
                           System.out.println(regionmap.get(i * m + j).neighbours.get(k) + "  ");
                       System.out.println();
                   }


           }

       }

   }

    public void bigAreaGenerator(){

        int d = (m+1)*(r+1);
        int[] regionChecker = new int[d];

        for(int i=0; i<d ; i++){
            regionChecker[i]=0;
        }

        for(int i=0; i<m+1 ; i++){

            for(int j=0; j< r+1 ; j++){

                if(regionChecker[i*m+j]==0){

                    List < Node > biggernodeslist = new ArrayList<>(1000);

                    for(int k=0 ; k < regionmap.get(i*m+j).nodes.size() ; k++){
                        biggernodeslist.add(regionmap.get(i*m+j).nodes.get(k));
                    }

                    int p;
                    for(p=0 ; p < regionmap.get(i*m+j).neighbours.size() ; p++){

                        int ri = regionmap.get(i*m+j).neighbours.get(p);

                        if(regionChecker[ri]==0){

                            for(int k=0 ; k < regionmap.get(ri).nodes.size() ; k++){
                                biggernodeslist.add(regionmap.get(ri).nodes.get(k));
                            }

                            regionChecker[ri]=1;
                        }
                    }

                    BigRegion bigRegion = new BigRegion();
                    bigRegion.setNodesnumbers(biggernodeslist);
                    bigRegions.add(bigRegion);


                    regionChecker[i*m+j]=1;
                }

            }
        }

    }

    public List < BigRegion > getBigRegions(){
        return this.bigRegions;
    }

}
