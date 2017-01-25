import java.io.InputStreamReader;

/**
 * Created by sstrunjas on 12/4/16.
 */
import java.io.*;
import java.util.*;
public class MaxSumNonCosecutive {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] list_l = r.readLine().split(" ");
        int[] bookings = new int[list_l.length];
        for(int i=0; i<list_l.length; i++)
            bookings[i] = Integer.parseInt(list_l[i]);

        System.out.println(MaxSumIterative(bookings));

    }
    public static int MaxSumIterative(int[] bookings){
        int[] results = new int[bookings.length];
        if(bookings.length == 0)
            return 0;
        results[0] = bookings[0];
        if(bookings.length > 1){
            results[1] = Math.max(bookings[0], bookings[1]);
        }
        for(int i=2; i<bookings.length; i++){
            results[i] = Math.max(results[i-1], bookings[i] + results[i-2]);
        }

        return results[bookings.length-1];

    }
    public static int maxSum(int[] bookings, int position, HashMap<Integer, Integer> memoized){
        if(memoized.containsKey(position))
            return memoized.get(position);
        if(position == 0)
            return bookings[0];
        if(position == 1){
            int r_v = Math.max(bookings[0],bookings[1]);
            memoized.put(1,r_v);
            return r_v;
        }


        int prev_max = 0;
        int prev_prev_max = 0;
        prev_max = maxSum(bookings, position-1, memoized);
        prev_prev_max = bookings[position] + maxSum(bookings, position-2, memoized);
        int ret_val = Math.max(prev_max, prev_prev_max);
        memoized.put(position, ret_val);
        return ret_val;
    }
}
