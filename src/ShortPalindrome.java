import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ShortPalindrome {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        String new_s = s.next();
        System.out.println(numTupples(new_s.toCharArray()));
    }
    public static long numTupples(char[] array){
        HashMap<Integer, Integer> rightReadSoFar = new HashMap();
        HashMap<Integer, Integer> leftReadSoFar = new HashMap();
        long[][] right = new long[26][26];
        long[][] left = new long[26][26];
        for(int i=0; i<26; i++){
            rightReadSoFar.put(i,0);
            leftReadSoFar.put(i,0);
            for(int j=0; j<26; j++){
                right[i][j] = 0L;
                left[i][j] = 0L;
            }
        }
        long sum = 0L;
        for(int i=(array.length-1); i>1; i--){
            for(int k=0; k<26; k++)
                right[array[i]-'a'][k] = (right[array[i]-'a'][k] + rightReadSoFar.get(k))%1000000007;
            rightReadSoFar.put(array[i]-'a', rightReadSoFar.get(array[i]-'a')+1);

        }

        leftReadSoFar.put(array[0]-'a', leftReadSoFar.get(array[0]-'a')+1);
        left[array[0]-'a'][array[1]-'a'] = left[array[0]-'a'][array[1]-'a'] + leftReadSoFar.get(array[0]-'a');
        leftReadSoFar.put(array[1]-'a', leftReadSoFar.get(array[1]-'a')+1);
        sum = (sum + (left[array[0]-'a'][array[1]-'a']*right[array[1]-'a'][array[0]-'a'])%1000000007)%1000000007;

        for(int i=2; i<(array.length-2); i++){
            rightReadSoFar.put(array[i]-'a', rightReadSoFar.get(array[i]-'a')-1);
            for(int t1=0; t1<26;t1++){
                left[t1][array[i]-'a'] = (left[t1][array[i]-'a'] + leftReadSoFar.get(t1))%1000000007;

                right[array[i]-'a'][t1] = (right[array[i]-'a'][t1] - rightReadSoFar.get(t1) + 1000000007)%1000000007;

            }


                for (int k = 0; k < 26; k++)
                    sum = (sum + (leftReadSoFar.get(k) * right[array[i] - 'a'][k]) % 1000000007) % 1000000007;
            leftReadSoFar.put(array[i]-'a', leftReadSoFar.get(array[i]-'a')+1);



        }

        return sum;



    }
}