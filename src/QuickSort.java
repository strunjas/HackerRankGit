import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class QuickSort {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] array = new int[n];
        for(int i=0; i<n; i++)
            array[i] = s.nextInt();
        QuickSort(array, 0, n-1, 0, n-1);
    }
    public static void QuickSort(int[] array, int lo, int hi, int start, int end){
        if(lo < hi){
            int split = partition(array,lo, hi, start, end);
            for(int k=start ;k<=end; k++)
                System.out.print(array[k] + " ");
            System.out.println();
            QuickSort(array, lo, split-1, start, end);
            QuickSort(array, split+1, hi, start, end);
        }

    }
    private static int partition(int[] array, int lo, int hi, int start, int end){
        int i = lo;
        for(int j=lo; j<hi; j++){
            if(array[j] <= array[hi]){
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }
        int tmp2 = array[hi];
        array[hi] = array[i];
        array[i] = tmp2;
        return i;


    }
}