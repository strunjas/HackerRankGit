/**
 * Created by svetlana on 1/25/17.
 */
import java.util.*;
public class AlienDictionary2 {



    public static List<Character> alienDictionaryCompute(List<String> inputWords){

        HashMap<Character, Set<Character>> graph = new HashMap();
        List<Character> ret = new ArrayList();

        if(inputWords.size() == 0)
            return ret;


        for(int i=0; i < (inputWords.size()-1); i++){
            String word1 = inputWords.get(i);
            String word2 = inputWords.get(i+1);


            int index = 0;

            while(index < Math.min(word1.length(), word2.length()) && word1.charAt(index) == word2.charAt(index))
                index++;

            if(index < Math.min(word1.length(), word2.length())){

                if(!graph.containsKey(word1.charAt(index)))
                    graph.put(word1.charAt(index), new HashSet<Character>());
                if(!graph.containsKey(word2.charAt(index)))
                    graph.put(word2.charAt(index), new HashSet<Character>());
                graph.get(word1.charAt(index)).add(word2.charAt(index));

            }
        }



        List<Character> results = topologicalSort(graph);
        return results;



    }

    public static List<Character> topologicalSort(HashMap<Character, Set<Character>> graph){
        Set<Character> visited = new HashSet();
        Stack<Character> topSortStack = new Stack();

        Iterator<Character> it = graph.keySet().iterator();
        while(it.hasNext()){
            char cc = it.next();
            topSortUtil(cc, topSortStack, visited, graph);

        }

        List<Character> ret = new ArrayList();
        while(!topSortStack.isEmpty()){
            ret.add(topSortStack.pop());
        }

        return ret;

    }
    public static void topSortUtil(char start, Stack<Character> topSortStack, Set<Character> visited,
                                   HashMap<Character, Set<Character>> graph){

        if(!visited.contains(start)){
            visited.add(start);
            for(char adjacent : graph.get(start)){
                if(!visited.contains(adjacent))
                    topSortUtil(adjacent, topSortStack, visited, graph);
            }

            topSortStack.push(start);
        }

        return;

    }

    public static void main(String[] args){
        String[] input = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };
        List<Character> results = alienDictionaryCompute(Arrays.asList(input));

    }

}
