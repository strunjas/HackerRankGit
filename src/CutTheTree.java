import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CutTheTree {
    public class Node{
        int data;
        List<Integer> children;
        public Node(int some_data){
            data = some_data;
            children = new ArrayList();
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        CutTheTree ns = new CutTheTree();
        Scanner s = new Scanner(System.in);
        int num_nodes = s.nextInt();
        Node[] tree = new Node[num_nodes];
        int[] root_sum = new int[num_nodes];
        HashSet<Integer> visited = new HashSet();
        int sum_all = 0;
        for(int i=0; i<num_nodes; i++){
            tree[i] = ns.new Node(s.nextInt());
            root_sum[i] = 0;
            sum_all += tree[i].data;
        }

        for(int i=0; i< (num_nodes-1); i++){

            int u = s.nextInt()-1;
            int v = s.nextInt()-1;
            tree[u].children.add(v);
            tree[v].children.add(u);
        }
        int ms = minSum(tree, 0, visited, root_sum, sum_all);
        for(int k=0; k< root_sum.length; k++)
            System.out.println((k+1) + " " + root_sum[k]);

    }
    public static int minSum(Node[] tree, int index, HashSet<Integer> visited, int[] root_sum,int all_sum ){
        if(!visited.contains(index)){
            visited.add(index);
            int overall_sum = 0;
            int current_min = Integer.MAX_VALUE;
            for(int child:tree[index].children){
                if(!visited.contains(child)){
                    int min_child = minSum(tree, child, visited, root_sum,all_sum);
                    if(current_min > min_child)
                        current_min = min_child;
                    overall_sum += root_sum[child];
                }
            }

            overall_sum += tree[index].data;
            root_sum[index] = overall_sum;
            if(index > 0)
                return Math.min(current_min, Math.abs(all_sum-overall_sum));
            else
                return current_min;


        }
        return -1;
    }
}