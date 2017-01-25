/**
 * Created by sstrunjas on 12/8/16.
 */
import java.util.*;
public class SumOfTwo {

    public static void main(String[] args){
        int[] example = {3,2,4};
        int[] res = twoSum(example, 6);
    }

    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int[] solution = new int[2];
        solution[0] = -1;
        solution[1] = -1;
        boolean found = false;
        int current_index = 0;
        while(!found && current_index < nums.length){
            solution[0] = current_index;
            int index = Arrays.binarySearch(nums, current_index+1, nums.length, target-nums[current_index]);
            if(index >= 0 ){
                solution[1] = index;
                found = true;

            }
            current_index++;

        }
        if(!found){
            solution[0]=-1;
            solution[1]=-1;
        }

        return solution;

    }
}
