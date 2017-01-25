import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Prim {

    class Pair implements Comparable<Pair>{
        int label;
        long cost;
        public Pair(int s_l, long s_c){
            label = s_l;
            cost = s_c;
        }
        public int compareTo(Pair other){
            return Long.compare(cost, other.cost);
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Prim ns = new Prim();
        Scanner s = new Scanner(System.in);
        int num_nodes = s.nextInt();
        int num_edges = s.nextInt();
        List<Pair>[] graph = (List<Pair>[])new List[num_nodes];
        for(int i=0; i<num_nodes; i++)
            graph[i] = new ArrayList<Pair>();
        for(int j=0; j< num_edges; j++){
            int a = s.nextInt();
            int b = s.nextInt();
            long weight = s.nextLong();
            graph[a-1].add(ns.new Pair(b-1, weight));
            graph[b-1].add(ns.new Pair(a-1, weight));
        }
        int starting_node = s.nextInt() - 1;

        System.out.println(PrimsAlgorithm(graph, starting_node, ns));

    }

    public static long PrimsAlgorithm(List<Pair>[] graph, int start, Prim s){

        Set<Integer> visited = new HashSet();
        PriorityQueue<Pair> pq = new PriorityQueue();
        long[] vertex_cost = new long[graph.length];
        int[] vertex_prev = new int[graph.length];
        for(int i=0; i<vertex_cost.length; i++) {
            vertex_cost[i] = Long.MAX_VALUE;
            vertex_prev[i] = -1;
        }
        vertex_cost[start] = 0L;
        pq.add(s.new Pair(start,0L));
        while(visited.size() < graph.length){
            Pair current_pair = pq.poll();
            visited.add(current_pair.label);
            for(Pair adjacent:graph[current_pair.label]){
                if(adjacent.cost < vertex_cost[adjacent.label]) {
                    vertex_cost[adjacent.label] = adjacent.cost;
                    vertex_prev[adjacent.label] = current_pair.label;

                }

                pq.clear();
                for(int i=0; i< graph.length; i++)
                    if(!visited.contains(i) && vertex_cost[i] < Long.MAX_VALUE)
                        pq.add(s.new Pair(i, vertex_cost[i]));

            }
        }


        long sum = 0L;
        for(int i=0; i<vertex_cost.length; i++) {
            sum += vertex_cost[i];
            System.out.println((i + 1) + " " + (vertex_prev[i]+1));
        }
        return sum;




    }
}