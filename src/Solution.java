import java.io.*;
import java.util.*;
public class Solution {
    public static final long MOD = 1000000007L;
    public static long[] t_values = new long[1001];
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = null;
        t_values[0] = 1L;
        t_values[1] = 1L;
        t_values[2] = 2L;
        t_values[3] = 4L;
        for(int i=4; i<=1000;i++)
            t_values[i] = (t_values[i-1]+t_values[i-2]+t_values[i-3]+t_values[i-4])%MOD;

        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            int num_examples = Integer.parseInt(br.readLine());
            long[] results = new long[num_examples];
            for(int i=0; i<num_examples;i++){
                String[] numrows_numcols= br.readLine().split(" ");
                int rows = Integer.parseInt(numrows_numcols[0]);
                int cols = Integer.parseInt(numrows_numcols[1]);
                long[] allcombosforheight = new long[cols+1];
                for(int k=0; k<=cols; k++)
                    allcombosforheight[k] = fastPower(t_values[k], rows);
                long[] correct_walls = new long[cols+1];
                correct_walls[1] = 1;
                for(int p=2; p<=cols;p++){
                    correct_walls[p] = allcombosforheight[p];
                    for(int l=1;l<p;l++)
                        correct_walls[p] = modminus(correct_walls[p], (correct_walls[l]*allcombosforheight[p-l])%MOD);
                }
                results[i] = correct_walls[cols];
            }

            for(int j=0; j<num_examples; j++)
                System.out.println(results[j]);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
       public static long modminus(long a, long b){
        return (a+MOD-b)%MOD;
    }
    public static long fastPower(long a, long b){
        long result = 1;
        long base = a % MOD;

        while(b > 0){
            if(b%2==1) // odd number
                result = (result * base)%MOD;

            b >>= 1;
            base = (base * base)%MOD;

        }

        return result%MOD;
    }


}