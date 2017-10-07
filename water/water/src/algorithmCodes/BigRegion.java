package algorithmCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09-Jul-17.
 */
public class BigRegion {

    private List < Node > nodesnumbers;
    float totalwaterVolume ;
    float averagelevel;
    double centerx,centery;

    public BigRegion(){

        nodesnumbers = new ArrayList<>(200);
        totalwaterVolume=0;
        averagelevel=0;
    }

    public void setNodesnumbers (List<Node> nodes){
        nodesnumbers = nodes;
    }

    public List<Node> getAllnodenumbers(){
        return this.nodesnumbers;
    }


    public float getTotalwaterVolume(){
        return totalwaterVolume;
    }

    public double getCenterx(){

        return centerx;
    }

    public double getCentery(){
        return centery;
    }

    public void generateTotalVolume(){

        int total=0;
        int n= nodesnumbers.size();
        for(int i=0;i<n; i++){
            total+= nodesnumbers.get(i).getWaterlevel();
        }

        averagelevel = (float)total/(float)n;


        totalwaterVolume = averagelevel*areafinder();


    }

    public void geneatecenter(){

        double totalx=0;
        double totaly=0;


        int n= nodesnumbers.size();

        for(int i=0;i<n; i++){

            totalx += (double) nodesnumbers.get(i).getX();
            totaly += (double) nodesnumbers.get(i).getY();
        }

        centerx = totalx/(double)n;
        centery = totaly/(double)n;

    }

    public float areafinder()
    {

        int sum=0;

        int n=nodesnumbers.size();

        for(int i=0; i<n ; i++)
        {
            sum+=(nodesnumbers.get(i).x*nodesnumbers.get((i+1)%n).y-nodesnumbers.get(i).y*nodesnumbers.get((i+1)%n).x);

        }

        return (float)sum;
    }



}
