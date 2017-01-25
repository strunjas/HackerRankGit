import java.util.*;
public class CombinationSum4 {
    static HashMap<Integer,List<List<Integer>>> num_solutions = new HashMap();
    public static List<List<Integer>> combinationSum4(int[] nums, int target) {
        if(target == 0) {
            List<List<Integer>> a = new ArrayList();
            List<Integer> el = new ArrayList();
            a.add(el);
            return a;
        }

        if(num_solutions.containsKey(target))
            return num_solutions.get(target);
        int solutions = 0;
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for(int i=0; i<nums.length; i++){
            if(target >= nums[i]) {
                List<List<Integer>> s1 = combinationSum4(nums, target - nums[i]);
                for(int k=0; k<s1.size(); k++){
                    List<Integer> retret = new ArrayList<>();
                    for(int element:s1.get(k))
                        retret.add(element);
                    retret.add(nums[i]);
                    ret.add(retret);
                }


            }

        }
        if(!num_solutions.containsKey(target)) {

            num_solutions.put(target, ret);
        }
        return ret;

    }
    public static void main(String[] args){
        int[] nums = {1,2,3};
        int target = 4;
        List<List<Integer>> res = combinationSum4(nums, target);
        for(List<Integer> l:res){
            for(int el:l)
                System.out.print(el + " ");
            System.out.println();
        }

    }
}