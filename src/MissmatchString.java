import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MissmatchString {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_cases = s.nextInt();
        int[] results = new int[num_cases];
        for(int i=0; i< num_cases; i++){
            int S = s.nextInt();
            String[] p_q = s.nextLine().trim().split(" ");
            results[i] = maxLength(p_q[0].toCharArray(), p_q[1].toCharArray(), S);
        }

        for(int i=0; i<num_cases;i++)
            System.out.println(results[i]);
    }
    public static int maxLength(char[] p, char[] q, int S){

        int maxLength = 0;
        for(int j=0; j<q.length; j++){
            int f1 = findMaxLength(p, q,j,0,q.length, S);
            int f2 = findMaxLength(p, q, 0 ,j , q.length, S);
            maxLength = Math.max(Math.max(maxLength,f1),f2);
        }

        return maxLength;



    }
    private static int findMaxLength(char[] p, char[] q, int i, int j, int n, int k){
        int l =0;
        int best_length = Integer.MIN_VALUE;
        int bad = 0;
        while( (i+l)<n && (j+l)<n){
            if(p[i+l] != q[j+l])
                bad+=1;
            if(bad > k){
                i = i+1;
                j = j+1;
                if(l > best_length)
                    best_length = l;
                if(p[i-1] != q[j-1])
                    bad = k-1;
                else
                    bad = k;
                if(l>0)
                    l--;

            }
            else
                l++;

        }

        return Math.max(best_length, l);


    }
}