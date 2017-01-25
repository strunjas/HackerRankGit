import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaxSubarraySum {


    public class BSTNode{
        long node_val;
        BSTNode left, right;
        public BSTNode(long some_val){
            node_val = some_val;
            left = null;
            right = null;
        }
    }
    public static BSTNode InsertIntoBST(long some_val, BSTNode root, MaxSubarraySum s){
        BSTNode current = root;
        BSTNode toInsert = s.new BSTNode(some_val);
        if(current == null){
            current = toInsert;
            root = current;
            return root;


        }
        else{
            BSTNode prev = current;
            while(current!=null){
                prev = current;
                if(current.node_val == some_val)
                    return root;
                else if(current.node_val > some_val)
                    current = current.left;
                else
                    current = current.right;
            }
            if(prev.node_val > some_val){
                prev.left = toInsert;
                return root;

            }
            else{
                prev.right = toInsert;
                return root;

            }

        }
    }
    public static long GetSmallestLargerVal(BSTNode root, long some_val){
        if(root == null)
            return Long.MAX_VALUE;
        if(root.node_val <= some_val)
            return GetSmallestLargerVal(root.right, some_val);
        else return Math.min(root.node_val, GetSmallestLargerVal(root.left, some_val));



    }
    public static long GetLargestSumSubarray(long[] array, long modulo){
        MaxSubarraySum s = new MaxSubarraySum();
        BSTNode root = null;
        long max_sum = 0L;
        long current_sum = 0L;
        for(int i=0; i<array.length; i++){
            current_sum = (current_sum + array[i])%modulo;
            root = InsertIntoBST(current_sum, root,s);
            long minMax = GetSmallestLargerVal(root, current_sum);
            if(minMax == Long.MAX_VALUE) {
                if (current_sum > max_sum)
                    max_sum = current_sum;
                }
                else{
                    long subsum = (current_sum - minMax + modulo)%modulo;
                    if(max_sum < subsum)
                        max_sum = subsum;
                }

        }

        return max_sum;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_cases = s.nextInt();
        long[] results = new long[num_cases];
        for(int i=0; i<num_cases; i++){
            int n = s.nextInt();
            long[] array = new long[n];
            long m = s.nextLong();
            for(int j=0; j<n; j++)
                array[j] = s.nextLong();
            results[i] = GetLargestSumSubarray(array,m);
        }

        for(int i=0; i<num_cases; i++)
            System.out.println(results[i]);
    }


}