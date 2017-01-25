import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Sherlock {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_cases = s.nextInt();
        int[] results = new int[num_cases];
        for(int i=0; i<num_cases; i++){
            int num_el = s.nextInt();
            int[] input = new int[num_el];
            for(int j=0; j<num_el; j++)
                input[j] = s.nextInt();
            results[i] = maxSum(input);

        }

        for(int i=0; i<num_cases; i++)
            System.out.println(results[i]);
    }
    public static int maxSum(int[] b){
        int n = b.length;
        int[] a = new int[n];
        int sum_so_far = 0;
        int sum_so_far_opposite = 0;
        if(n < 2)
            return 0;

        a[0]=b[0];
        for(int i=1;i<n;i++){
            if(a[i-1] == 1){
                if(b[i] >= b[i-1]){
                    sum_so_far = b[i] - a[i-1] + sum_so_far;
                    sum_so_far_opposite += b[i-1] - 1;
                    a[i] = b[i];
                }
                else{
                    if((sum_so_far + b[i] - 1) >= (sum_so_far_opposite + b[i-1] - 1)){
                        sum_so_far = sum_so_far + b[i] - a[i-1];
                        sum_so_far_opposite += b[i-1] - 1;
                        a[i] = b[i];
                    }
                    else{
                        int tmp = sum_so_far + b[i] - a[i-1];
                        sum_so_far = sum_so_far_opposite + b[i-1] - 1;
                        sum_so_far_opposite = tmp;
                        a[i] = 1;
                        for(int k=(i-1); k>=0; k--)
                            if(a[k] == b[k])
                                a[k] = 1;
                            else
                                a[k]=b[k];
                    }
                }
            }
            else{
                if(b[i] < b[i-1]){
                    a[i] = 1;
                    sum_so_far += a[i-1] - a[i];
                    sum_so_far_opposite += b[i] - 1;
                }
                else{
                    if((sum_so_far + a[i-1] - 1) >= (sum_so_far_opposite + b[i] - 1)){
                        a[i] = 1;
                        sum_so_far += a[i-1] - a[i];
                        sum_so_far_opposite += b[i] - 1;
                    }
                    else{
                        a[i] = b[i];
                        int tmp = sum_so_far + a[i-1] - 1;
                        sum_so_far = sum_so_far_opposite + b[i] - 1;
                        sum_so_far_opposite = tmp;
                        for(int k=(i-1); k>=0; k--)
                            if(a[k] == b[k])
                                a[k] = 1;
                            else
                                a[k]=b[k];

                    }
                }
            }

        }
        return sum_so_far;



    }

}