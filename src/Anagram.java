import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_cases = s.nextInt();
        s.nextLine();
        int[] results = new int[num_cases];
        for(int i=0; i<num_cases; i++){
            String st = s.nextLine();
            results[i] = numAnagramPairs(st.toCharArray());
        }
        for(int j=0; j<num_cases; j++)
            System.out.println(results[j]);


    }
    public static int numAnagramPairs(char[] input){
        HashMap<Integer, List<Integer>> counts_positions = new HashMap();
        int anagrams_sum = 0;
        for(int i=1; i<input.length; i++){
            counts_positions.clear();
            int start = 0;
            while((start+i-1) < input.length){
                int sum = getSumChars(input, start, i);
                if(counts_positions.containsKey(sum)){
                    for(int start_other:counts_positions.get(sum)){
                        if(areAnagrams(input, start_other, start, i ))
                            anagrams_sum++;
                    }

                }
                else{
                    counts_positions.put(sum, new ArrayList<Integer>());
                }
                counts_positions.get(sum).add(start);

                start++;
            }
        }

        return anagrams_sum;
    }
    private static int getSumChars(char[] input, int start, int length){
        int sum = 0;
        for(int j=start; (j-start) < length; j++)
            sum += input[j]-'a';
        return sum;
    }
    private static boolean areAnagrams(char[] input, int start_a, int start_b, int length){
        int[] a_counts = new int[26];
        int[] b_counts = new int[26];
        for(int i=0; i<26; i++){
            a_counts[i] = 0;
            b_counts[i] = 0;
        }
        for(int i=start_a, j=start_b; (i-start_a) < length; i++, j++){
            a_counts[input[i]-'a']++;
            b_counts[input[j] - 'a']++;
        }
        for(int i=0; i<26; i++)
            if(a_counts[i] != b_counts[i])
                return false;
        return true;
    }
}

    