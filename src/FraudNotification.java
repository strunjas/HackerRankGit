import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FraudNotification {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_days = s.nextInt();
        int history = s.nextInt();
        int[] trans = new int[num_days];
        for(int i=0; i<num_days; i++)
            trans[i] = s.nextInt();

        System.out.print(numOfNotifications(trans,history));
    }
    public static int numOfNotifications(int[] array, int history){
        int[] median_array = array.clone();
        int num_notifications = 0;
        int i=0;
        while(i < history)
            i++;
        Arrays.sort(median_array, 0, i);
        for(int j=i; j<array.length;j++){
            double median = 0;
            if(j%2==1)
                median = (double)(median_array[(j-1)/2]);
            else
                median = (median_array[(j-1)/2] + median_array[(j-1)/2 + 1])/2d;

            if(array[j] >= 2* median)
                num_notifications++;
            bin_search_insert(median_array, 0, j-1, array[j]);

        }

        return num_notifications;
    }
    public static void bin_search_insert(int[] sorted_array, int start, int end, int insert_val){
        int low = start;
        int high = end;
        boolean found = false;
        int med_index = -1;

        while(low < high && !found){
            med_index = (low+high)/2;
            if(sorted_array[med_index] == insert_val){
                found = true;

            }
            else if(sorted_array[med_index] < insert_val){
                low = (med_index < end)?(med_index + 1):med_index;

            }
            else
                high = (med_index >0)?(med_index - 1):med_index;


        }

        int med_pos = (low+high)/2;
        int left = -1;
        int right = -1;
        int insert_pos = -1;
        for(left = med_pos; left>0 && sorted_array[left-1]==sorted_array[left];left--);
        for(right = med_pos; right<end && sorted_array[right] == sorted_array[right+1]; right++);
        if(insert_val >= sorted_array[right])
            insert_pos = right+1;
        else
            insert_pos = left;
        for(int k=(end+1);k>insert_pos && k>0; k--)
            sorted_array[k] = sorted_array[k-1];
        sorted_array[insert_pos] = insert_val;

    }

}