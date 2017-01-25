import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Collections;
import java.util.Arrays;

public class MinSum {



    public static void main(String[] args)  throws Exception {
        Scanner  br = new Scanner(System.in);
        int N = br.nextInt();
        int K = br.nextInt();
        int [] arr = new int[N];
        for(int i = 0; i < N; i++)
            arr[i] = br.nextInt();
        System.out.println(minSum(arr, K));
    }
    public static int minSum(int[] array, int k){
        Arrays.sort(array);
        ArrayList<Integer>[] diffs = (ArrayList<Integer>[])new ArrayList[array.length];
        for(int i=0; i<array.length; i++){
            diffs[i] = new ArrayList<Integer>();
            for(int j=i; j<array.length; j++)
                diffs[i].add(array[j] - array[i]);
        }
        int min_interval = Integer.MAX_VALUE;
        int min_sum = Integer.MAX_VALUE;
        int sum =0;
        for(int i = 0; i<array.length; i++){
            if((i+k-1) < array.length){
                if(sum == 0){
                    int sub = 0;
                    for(int t1=i; t1<(i+k-1); t1++) {
                        int t2 = 1;
                        sub++;
                        int min_col = Math.min(k-sub,diffs[t1].size()-1);
                        while(t2  <= min_col) {
                            sum += diffs[t1].get(t2++);
                        }

                    }


                }
                else{
                    //subtract previous step
                    int index = i-1;
                    int t2 = 1;
                    int min_col = Math.min(k-1,diffs[index].size()-1);
                    while(t2 <= min_col)
                        sum -= diffs[index].get(t2++);
                    //add current step
                    int sub = 0;
                    int k_index = k;
                    for(int k1=i; k1<(i+k-1); k1++){
                        sub++;
                        sum+=diffs[k1].get(k_index-sub);

                    }

                }


                if(sum < min_sum)
                    min_sum = sum;
            }

        }

        return min_sum;
    }
}

