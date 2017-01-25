import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GridlandMetro {

    public class Pair implements Comparable<Pair>{
        long start;
        long end;

        public Pair(long s, long e){
            start = s;
            end = e;
        }

        public int compareTo(Pair other){
            return Long.compare(start, other.start);
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        GridlandMetro ts = new GridlandMetro();
        long rows = s.nextLong();
        long cols = s.nextLong();
        long numTracks = s.nextLong();
        HashMap<Long, List<Pair>> map_t = new HashMap();
        long numLampposts = 0L;


        for(long j=0; j<numTracks; j+=1L){

            long current_row = s.nextLong();
            long c1 = s.nextLong();
            long c2 = s.nextLong();
            if(!map_t.containsKey(current_row))
                map_t.put(current_row, new ArrayList<Pair>());
            map_t.get(current_row).add(ts.new Pair(c1,c2));

        }

        for(long i=(map_t.keySet().size()+1); i<=rows; i++)
            numLampposts += cols;

        for(Long current_key:map_t.keySet()){
            Collections.sort(map_t.get(current_key));
            long current_max = 1L;
            for(int i=0; i<map_t.get(current_key).size(); i++){
                if(current_max < map_t.get(current_key).get(i).start)
                    numLampposts += map_t.get(current_key).get(i).start - current_max;
                if(current_max <= map_t.get(current_key).get(i).end)
                    current_max = map_t.get(current_key).get(i).end + 1L;
            }
            numLampposts += cols-current_max+1;
        }




        System.out.println(numLampposts);
    }


}