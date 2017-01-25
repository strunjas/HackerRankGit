import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Lists {
    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_elements = s.nextInt();
        int[] elements = new int[num_elements];
        for(int i=0; i<num_elements; i++)
            elements[i] = s.nextInt();
        int sum = sumLists(elements);
        System.out.println(sum);
    }
    public static int sumLists(int[] numbers){

        List<List<List<Integer>>> all_results = findAllLists(numbers, 0);
        int sum = 0;
        for(List<List<Integer>> l: all_results){
            System.out.print("( ");
            for(List<Integer> t:l){
                System.out.print("( ");
                sum = (sum + sumList(t)*t.size())%MOD;
                for(int el:t)
                    System.out.print(el + " ");
                System.out.print(")");
            }
            System.out.println(" )");

        }

        return sum;


    }
    public static int sumList(List<Integer> l){
        int sum = 0;
        for(int i=0; i<l.size(); i++)
            sum = (sum + l.get(i))%MOD;
        return sum;
    }
    public static List<Integer> deepCopy1(List<Integer> l){
        List<Integer> ret = new ArrayList<>();
        for(int el:l)
            ret.add(el);
        return ret;
    }
    public static List<List<Integer>> deepCopy2(List<List<Integer>> l){
        List<List<Integer>> ret = new ArrayList<>();
        for(List<Integer> t:l){
            List d_copy1 = deepCopy1(t);
            ret.add(d_copy1);

        }

        return ret;
    }
    public static List<List<List<Integer>>> findAllLists(int[] array, int index){
        if(index == (array.length-1)){
            List level_1 = new ArrayList<Integer>();
            level_1.add(array[index]);
            List level_2 = new ArrayList<List<Integer>>();
            level_2.add(level_1);
            List level_3 = new ArrayList<List<List<Integer>>>();
            level_3.add(level_2);
            return level_3;
        }
        List<List<List<Integer>>> result = new ArrayList<>();
        List<List<List<Integer>>> subset = findAllLists(array, index+1);
        for(List<List<Integer>> l:subset){
            List new_list = deepCopy2(l);
            ArrayList new_el = new ArrayList();
            new_el.add(array[index]);
            new_list.add(0, new_el);
            result.add(new_list);
            List<List<Integer>> new_list2 = deepCopy2(l);
            new_list2.get(0).add(0, array[index]);
            result.add(new_list2);

        }

        return result;
    }
}

