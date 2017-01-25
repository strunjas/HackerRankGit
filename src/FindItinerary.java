/**
 * Created by sstrunjas on 1/22/17.
 */
import java.util.*;
public class FindItinerary {

    public static List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                stack.push(targets.get(stack.peek()).poll());
            route.add(0, stack.pop());
        }
        return route;
    }

    public static void main(String[] args){
        String[][] tickets = new String[][]{{"JFK","A"},{"A","C"},{"C","D"},{"D","A"},{"JFK","D"}, {"C", "JFK"},{"D", "B"}, {"B", "C"}};
        List<String> results = findItinerary(tickets);
    }
}
