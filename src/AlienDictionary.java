import java.util.*;
public class AlienDictionary {


    public static String alienOrder(String[] words) {
        //This data structure contains all letters adjacent to the given letter
        HashMap<Character,Set<Character>> adjacent = new HashMap();
        //This one keeps track of the current in-degree of a letter
        HashMap<Character, Integer> indegree = new HashMap();

        //build the relationships between the letters based on the provided, sorted array of strings

        for(int i=1; i< words.length; i++){
            char[] w1 = words[i-1].toCharArray();
            char[] w2 = words[i].toCharArray();
            int min_len = Math.min(w1.length, w2.length);
            for(int j=0; j<min_len; j++){
                if(!indegree.containsKey(w1[j]))
                    indegree.put(w1[j], 0);
                if(!indegree.containsKey(w2[j]))
                    indegree.put(w2[j], 0);
                if(w1[j]!=w2[j]){
                    if(!adjacent.containsKey(w1[j]))
                        adjacent.put(w1[j], new HashSet<Character>());
                    if(adjacent.get(w1[j]).add(w2[j]))
                        indegree.put(w2[j], indegree.get(w2[j])+1);

                }
            }
        }

        //Traverse the graph and build the string according to topological sorting
        //Start from the nodes with 0 in-degree,and at each step remove edges
        //And add new nodes with 0 in-degree
        StringBuilder finalString = new StringBuilder();
        LinkedList<Character> queue = new LinkedList();
        for(char c:indegree.keySet())
            if(indegree.get(c)==0)
                queue.addLast(c);
        while(!queue.isEmpty()){
            char current_c = queue.poll();
            finalString.append(current_c);
            if(adjacent.containsKey(current_c)) {
                for (char child : adjacent.get(current_c)) {
                    indegree.put(child, indegree.get(child) - 1);
                    if (indegree.get(child) == 0)
                        queue.addLast(child);
                }
            }

        }

        //if(finalString.length() < indegree.keySet().size())
        //  return "";

        return finalString.toString();


    }
    public static void main(String[] args){
        String[] input = {"wrt","wrf","er","ett","rftt"};
        String result = alienOrder(input);
        System.out.println(result);
    }
}