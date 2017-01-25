import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Candies {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_elements = s.nextInt();
        int[] ratings = new int[num_elements];
        for(int i=0; i<num_elements; i++)
            ratings[i] = s.nextInt();

        System.out.println(minCandies(ratings));
    }

    public static long minCandies(int[] ratings){
        long[] candies = new long[ratings.length];
        for(int i=0; i<candies.length; i++)
            candies[i] = 1L;
        long sum = 0L;
        int i=0;
        int current_start = 0;
        while(i < (ratings.length - 1)){
            if(ratings[i] == ratings[i+1]){
                minCandiesNoEquals(ratings, candies, current_start, i);
                i++;
                while(i < (ratings.length-1) && ratings[i] == ratings[i+1])
                    i++;
                current_start = i;
            }
            else
                i++;

        }
        minCandiesNoEquals(ratings, candies, current_start, ratings.length-1);
        for(int t=0; t<candies.length; t++) {
           // System.out.println(candies[t] + " ");
            sum += candies[t];
        }
        return sum;
    }
    private static void minCandiesNoEquals(int[] ratings, long[] candies, int start, int end){

        int current_start = start;
        while(current_start < end){
            int current_minima_1 = local_minima(current_start, end,ratings);
            int current_maxima = local_maxima(current_start, end, ratings);
            int current_minima_2 = local_minima(current_maxima, end,ratings);
            int k1 = 1;
            for(int i=current_minima_1; i<current_maxima; i++){
                candies[i] = k1 ;
                k1 += 1;
            }
            int k2 = 1;
            for(int j = current_minima_2; j>current_maxima; j--){

                candies[j] = k2 ;
                k2 += 1;
            }

            candies[current_maxima] = Math.max(k1-1, k2-1) + 1;
            current_start = current_minima_2 ;

        }

    }
    private static int local_minima(int start_pos, int end_pos, int[] input){
        int i = start_pos;
        while(i < end_pos && input[i] > input[i+1])
            i++;
        return i;
    }
    private static int local_maxima(int start_pos, int end_pos, int[] input){
        int i = start_pos;
        while(i < end_pos && input[i] < input[i+1])
            i++;
        return i;

    }
}