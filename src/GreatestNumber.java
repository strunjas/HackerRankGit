/**
 * Created by sstrunjas on 1/20/17.
 */
import java.util.*;
public class GreatestNumber {


    public static String largestNumber(int[] nums) {

        String[] nums_s = new String[nums.length];
        for(int i=0; i<nums.length; i++)
            nums_s[i] = "" + nums[i];

        Arrays.sort(nums_s, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                System.out.println("Comparing " + s1 + " and " + s2);
                for(int i=0; i<c1.length(); i++){
                    if((c1.charAt(i) - '0') > (c2.charAt(i) - '0')){
                        System.out.println("Greater is " + s1 );
                        return -1;

                    }
                    else if((c1.charAt(i) - '0') < (c2.charAt(i) - '0')){
                        System.out.println("Greater is " + s2 );
                        return 1;
                    }
                }
                System.out.println("They are equal: " + s1 + " and " + s2 );
                return 0;

            }
        });

        StringBuilder results = new StringBuilder();
        for(int i=0; i<nums_s.length; i++){
            results.append(nums_s[i]);
        }
        return results.toString();




    }
    public static void main(String[] args){
        int[] nums = {1,2,3};
        System.out.println(largestNumber(nums));
    }


}