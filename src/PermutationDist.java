import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PermutationDist {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_el = s.nextInt();
        long[] array = new long[num_el];
        for(int i=0; i<num_el; i++)
            array[i] = s.nextLong();
        System.out.print(minSwaps(array));
    }
    //Find minimum number of swaps between some random permutation to the "beautiful" permutation -- all numbers in         //increasing order
    public static long minSwaps(long[] array){
        long[] sorted_array = array.clone();
        Arrays.sort(sorted_array);
        //find all cycles in the current permutation
        HashMap<Long, Integer> sorted_mapping = new HashMap();
        HashMap<Long, Integer> inversed_sorted_mapping = new HashMap();
        for(int i=0; i<sorted_array.length; i++){
            sorted_mapping.put(sorted_array[i], i);
            inversed_sorted_mapping.put(sorted_array[i], (sorted_array.length-1-i));
        }

        int[] mapping_btw_two = new int[array.length];
        int[] mapping_btw_two_inv = new int[array.length];
        for(int j = 0; j<array.length; j++){
            mapping_btw_two[sorted_mapping.get(array[j])] =  j;
            mapping_btw_two_inv[inversed_sorted_mapping.get(array[j])] = j;

        }
        int num_swaps = countCycles(mapping_btw_two);
        int num_swaps_r = countCycles(mapping_btw_two_inv);

        return Math.min(num_swaps,num_swaps_r);

    }
    public static int countCycles(int[] mapping)
    {
        int[] in_cycle = new int[mapping.length];
        for(int i=0; i<in_cycle.length;i++)
            in_cycle[i] = 0;
        int all_cycles = 0;
        for(int i=0; i<mapping.length; i++){
            if(in_cycle[i]==0){
                in_cycle[i]=1;
                int current_cycle = 1;
                int mapping_v = mapping[i];

                while(mapping_v != i){
                    in_cycle[mapping_v] = 1;
                    current_cycle++;
                    mapping_v = mapping[mapping_v];
                }
                all_cycles += current_cycle - 1;
            }
        }
        return all_cycles;
    }
}