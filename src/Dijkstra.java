import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



public class Dijkstra {

    public class Pair{
        int label;
        long weight;
        public Pair(int s_label, long s_weight){
            label = s_label;
            weight = s_weight;
        }

    };

    public class minHeap{
        Pair[] elements = null;
        Integer[] indices = null;
        int current_size;


        public minHeap(int size){
            elements = new Pair[size];
            indices = new Integer[size];
            for(int i=0; i<size; i++)
                indices[i] = -1;
            current_size = 0;
        }
        public boolean isEmpty(){
            return current_size == 0;
        }
        public void Insert(Pair n){
            elements[current_size++] = n;
            indices[n.label] = current_size-1;
            reheapify_up(current_size-1);


        }
        public void Update(long new_weight, int index){
            if(indices[index] > -1){
                int start_pos = indices[index];
                elements[start_pos].weight = new_weight;
                reheapify_up(start_pos);
            }
        }
        private void reheapify_up(int start){
            int from = start;
            while(from > 0){
                int parent = (from%2==0)?((from-1)/2):from/2;
                if(elements[parent].weight > elements[from].weight){
                    swap(elements[parent].label, elements[from].label, indices);
                    swap(parent, from, elements);
                    from = parent;

                }
                else
                    break;

            }

        }
        private void reheapify_down(){
            int from = 0;

            while(from < (current_size-1)){
                long child_1 = ((2*from +1) < current_size)?elements[2*from+1].weight:Integer.MAX_VALUE;
                long child_2 = ((2*from +2) < current_size)?elements[2*from+2].weight:Integer.MAX_VALUE;
                long min_c1_c2 = Math.min(child_1, child_2);
                if(elements[from].weight > min_c1_c2){
                    int swap_index = (min_c1_c2 == child_1)?(2*from+1):(2*from+1);
                    swap(elements[from].label, elements[swap_index].label, indices);
                    swap(from, swap_index, elements);
                    from = swap_index;
                }
                else
                    break;



            }

        }
        public Pair Remove(){
            if(current_size > 0){
                Pair ret = elements[0];
                indices[ret.label] = -1;
                elements[0] = elements[current_size-1];
                indices[elements[0].label] = 0;
                current_size --;
                reheapify_down();
                return ret;
            }
            return null;
        }
        private <T> void swap(int index_i, int index_j, T[] elements){
            T prev = elements[index_i];
            elements[index_i] = elements[index_j];
            elements[index_j] = prev;
        }
        public boolean containsNode(int nodeLabel){
            return (indices[nodeLabel] > -1);
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_q = s.nextInt();
        String[] results = new String[num_q];
        Dijkstra new_s = new Dijkstra();

        for(int k=0; k<num_q; k++){
            int num_nodes = s.nextInt();
            int num_edges = s.nextInt();
            List<Pair>[] graph = (List<Pair>[])new List[num_nodes];
            for(int i=0; i< num_nodes; i++)
                graph[i] = new LinkedList<Pair>();

            for(int t=0; t<num_edges; t++){
                int s_node = s.nextInt();
                int e_node = s.nextInt();
                long weight = s.nextLong();

                graph[s_node-1].add(new_s.new Pair(e_node-1,weight));
                graph[e_node-1].add(new_s.new Pair(s_node-1,weight));
            }

            int source = s.nextInt();
            results[k] = Dijkstra(graph, source-1, new_s);
        }

        for(int m=0; m<results.length; m++)
            System.out.println(results[m]);
    }
    public static int minIndexUnvisited(long[] distances, Set<Integer> unvisited){
        long minValue = Long.MAX_VALUE;
        int index = -1;
        int num_nodes = distances.length;
        for(int el:unvisited)
            if(distances[el] < minValue){

                minValue =distances[el];
                index = el;
            }

        return index;
    }

    public static String Dijkstra(List<Pair>[] graph, int source, Dijkstra s){
        int num_nodes = graph.length;


        long[] distances = new long[num_nodes];
        for(int i=0; i<num_nodes; i++)
            distances[i] = Long.MAX_VALUE;
        distances[source] = 0L;
        Set<Integer> unvisited = new HashSet();
        for(int i=0; i<num_nodes; i++)
            unvisited.add(i);
        int next_visited = -1;
        minHeap h = s.new minHeap(num_nodes);
        h.Insert(s.new Pair(source,0L));

        while(unvisited.size() > 0){
            Pair next = h.Remove();
            if(next != null){
                unvisited.remove(next.label);
                for(Pair adjacent:graph[next.label]){
                    if(unvisited.contains(adjacent.label) && (distances[next.label] + adjacent.weight) < distances[adjacent.label]){
                        distances[adjacent.label] = distances[next.label] + adjacent.weight;
                        if(h.containsNode(adjacent.label))
                            h.Update(distances[adjacent.label],adjacent.label);
                        else
                            h.Insert(s.new Pair(adjacent.label, distances[adjacent.label]));
                    }

                }

            }
            else
                break;
        }




        StringBuffer res = new StringBuffer();
        for(int t=0; t<num_nodes; t++)
            if(t!=source)
                if(distances[t] == Long.MAX_VALUE)
                    res.append("-1 ");
                else
                    res.append(distances[t] + " ");


        return res.toString().trim();



    }
}