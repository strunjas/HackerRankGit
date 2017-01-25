import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaximumCut {

    public class Node{
        int label;
        long nodes_count;
        List<Integer> children;
        int parent;
        public Node(int s_label, int s_parent){
            label = s_label;
            parent = s_parent;
            nodes_count = 1;
            children = new ArrayList<Integer>();
        }

    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        MaximumCut ns = new MaximumCut();
        Scanner s = new Scanner(System.in);
        int num_nodes = s.nextInt();
        int num_edges = s.nextInt();

        Node[] tree = new Node[num_nodes];
        for(int i=0; i<num_nodes; i++)
            tree[i] = ns.new Node(i,-1);

        for(int j=0; j<num_edges; j++){
            int child = s.nextInt()-1;
            int parent = s.nextInt()-1;
            tree[parent].children.add(child);
            tree[child].parent = parent;
            tree[parent].nodes_count += tree[child].nodes_count;
            //update parents nodes counts
            int current_parent = tree[parent].parent;
            long add_nodes = tree[child].nodes_count;
            while(current_parent != -1){
                tree[current_parent].nodes_count += add_nodes;
                current_parent = tree[current_parent].parent;
            }

        }

        System.out.println(maxCut(tree, 0));


    }
    public static long maxCut(Node[] tree, int root){
        if(tree[root].nodes_count % 2 == 1)
            return 0L;
        long cut_size = 0L;
        LinkedList<Node> subtrees = new LinkedList();
        subtrees.add(tree[root]);
        while(subtrees.size() > 0){
            Node current_root = subtrees.removeFirst();
            for(int i=0; i<current_root.children.size(); i++){
                Node current_child = tree[current_root.children.get(i)];
                if(current_child.nodes_count%2==0){
                    cut_size += 1L;
                    subtrees.addLast(current_child);

                }

            }

        }

        return cut_size;
    }
}