import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Nikita {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num_cases = Integer.parseInt(br.readLine());
            int[] num_results = new int[num_cases];
            for(int i=0; i<num_cases; i++){
                int num_elements = Integer.parseInt(br.readLine());
                int[] current_example = new int[num_elements];
                String[] example = br.readLine().split(" ");
                int current_sum = 0;
                for(int k=0; k<num_elements; k++) {
                    current_example[k] = Integer.parseInt(example[k]);
                    current_sum += current_example[k];
                }
                num_results[i] = max_number_splits(current_example, 0, current_example.length-1, current_sum);
            }

            for(int t=0; t<num_results.length;t++)
                System.out.println(num_results[t]);

        }
        catch(Exception e){

        }
    }
    public static int max_number_splits(int[] input, int l, int r, int sum){
        if(l == r)
            return 0;
        int sum_l = input[l];
        int pointer_l = l;

        while((pointer_l+1)<r && (sum_l < (sum - sum_l)))
            sum_l += input[++pointer_l];
        pointer_l--;

        if(sum_l == (sum - sum_l)){
            return (1 + Math.max(max_number_splits(input,l,pointer_l,sum_l), max_number_splits(input, pointer_l+1,r,sum_l)));
        }

        return 0;

    }

}