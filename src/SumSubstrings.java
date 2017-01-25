import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SumSubstrings {
    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        sumOfSubstrings(input.toCharArray());
    }
    public static void sumOfSubstrings(char[] nums){

        String[] sumSubstrings = new String[nums.length];
        int final_sum = 0;
        for(int i=0; i<nums.length; i++) {
            sumSubstrings[i] = nums[i] + "";
            System.out.println(sumSubstrings[i]);
        }
        int length = 1;
        while(length < nums.length){
            for(int i=0; i<(nums.length - length); i++) {
                sumSubstrings[i] = sumSubstrings[i] + "" + nums[i + length];
                System.out.println(sumSubstrings[i]);

            }
            length++;
        }




    }
}