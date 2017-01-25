/**
 * Created by sstrunjas on 11/19/16.
 */

import java.io.*;
import java.util.*;
import java.text.*;
public class TimeSeries{
class Point implements Comparable<Point>{
    Date timeStamp;
    Double timeVal;
    boolean isMissing;
    Point(Date someTime, Double someVal, boolean missing){
        timeStamp = someTime;
        timeVal = someVal;
        isMissing = missing;
    }

    public int compareTo(Point otherPoint){
        return timeStamp.compareTo(otherPoint.timeStamp);
    }
}
public Point createNewPoint(Date someTime, Double someVal, boolean missing){
    return new Point(someTime, someVal, missing);
}
public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int num_lines = Integer.parseInt(br.readLine());
    Point[] all_points = new Point[num_lines];
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
    TimeSeries s = new TimeSeries();

for(int i=0; i<num_lines; i++){

        String[] inputLine = br.readLine().split("\t");
        boolean missing = false;
        double reading = -1d;
        if(inputLine[1].contains("MISSING"))
        missing = true;
        if(!missing)
        reading = Double.parseDouble(inputLine[1]);
        all_points[i] = s.createNewPoint(df.parse(inputLine[0]), reading, missing);


        }

        Arrays.sort(all_points);
        for(int m=0; m<all_points.length; m++){
        if(all_points[m].isMissing)
        all_points[m].timeVal = weightedAverage(all_points, m);
        }

        for(int t=0; t<all_points.length; t++)
        if(all_points[t].isMissing)
        System.out.println(all_points[t].timeVal);

        }

public static double weightedAverage(Point[] sortedArray, int index){

        double weightedAvgBefore = 0d;
        double weightedAvgAfter = 0d;
        //find weighted average before
        //and weighted average after
        //and compute the average of two

        for(int i = (index-5); i < index; i++){
        if(i >=0 && !sortedArray[i].isMissing){
        weightedAvgBefore = 0.5*weightedAvgBefore + 0.5*sortedArray[i].timeVal;
        }
        }

        for(int j= (index+5); j > index; j--){
        if(j < sortedArray.length && !sortedArray[j].isMissing){
        weightedAvgAfter = 0.5*weightedAvgAfter + 0.5*sortedArray[j].timeVal;
        }
        }

        if(weightedAvgBefore > 0 && weightedAvgAfter > 0)
        return (weightedAvgBefore + weightedAvgAfter)/2d;

        return (weightedAvgBefore > 0)?weightedAvgBefore:weightedAvgAfter;

        }
        }



