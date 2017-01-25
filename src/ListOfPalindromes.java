/**
 * Created by sstrunjas on 11/28/16.
 */
import java.util.*;
public class ListOfPalindromes {

    public  static void main(String[] args){
        //ListOfPalindromes l = new ListOfPalindromes();
        String[] words = {"hellol", "hello", "olleh","tap", "pat", "leh", "glass", "bottle", "watter", "pot", "top", "potter", "ettop"};
       // l.printPalindromes(words);
        printPalindromesWithHash(words);
       // String s = "geeksogeeks";
       // String ret = canBeArrangedIntoPalindrome(s);
      //  System.out.println(ret);



    }
    public static String canBeArrangedIntoPalindrome(String input){
        char[] input_chars = input.toCharArray();
        int start = 0;
        int end = input.length()-1;
        while(start < end){
            if(input_chars[start] == input_chars[end]){
                start++;
                end--;
            }
            else{
                boolean found = false;
                for(int j= (end-1); j>start; j--){
                    if(input_chars[j] == input_chars[start]){
                        char tmp = input_chars[end];
                        input_chars[end] = input_chars[j];
                        input_chars[j] = tmp;
                        found = true;
                        break;
                    }
                }
                if(!found)
                    return "";
                start++;
                end--;
            }

        }

        return new String(input_chars);
    }
    public static int hashFun(String input, int start, int end, int base){
        int sum = 0;
        int mod = 1000000007;
        int a = 1;
        for(int j=start; j<=end; j++){
            sum =((sum*a)%mod + input.charAt(j))%mod;
            a = (a*base)%mod;

        }
        return sum;
    }
    public static boolean isPalindrome(String str, int start, int end){
        int s = start;
        int e = end;
        while(s < e){
            if(str.charAt(s) != str.charAt(e))
                return false;
            s++;
            e--;
        }

        return true;
    }
    public static void printPalindromesWithHash(String[] inputs){
        ArrayList<Integer>[] prefixHashes = new ArrayList[inputs.length];
        ArrayList<Integer>[] reverseHashes = new ArrayList[inputs.length];
        //precompute hashes
        int mod = 1000000007;
        for(int i=0; i<inputs.length; i++){
            prefixHashes[i] = new ArrayList<Integer>();
            reverseHashes[i] = new ArrayList<Integer>();
            int sum = 0;
            int sum_reverse = 0;
            int a=1;
            for(int j=0; j<inputs[i].length(); j++){
                sum = ((sum*a)%mod + inputs[i].charAt(j) + 0)%mod;
                sum_reverse = ((sum_reverse*a)%mod + inputs[i].charAt(inputs[i].length()-1-j) + 0)%mod;
                a= (a*10)%mod;
                prefixHashes[i].add(j,sum);
                reverseHashes[i].add(j,sum_reverse);

            }

        }
        for(int i=0; i<inputs.length; i++)
            for(int j=(i+1); j<inputs.length; j++){
                int min_length = Math.min(inputs[i].length(), inputs[j].length());
                if(prefixHashes[i].get(min_length-1).intValue() == reverseHashes[j].get(min_length-1).intValue()){
                    if(inputs[i].length() == inputs[j].length()){
                        System.out.println(inputs[i] + " " + inputs[j]);
                    }
                    else{
                        boolean restMatch = false;
                        if(inputs[i].length() > inputs[j].length())
                            restMatch = isPalindrome(inputs[i], min_length, inputs[i].length()-1);
                        else
                            restMatch = isPalindrome(inputs[j], min_length, inputs[j].length()-1);
                        if(restMatch)
                            System.out.println(inputs[i] + " " + inputs[j]);
                    }

                }

            }

    }
    public void printPalindromes(String[] inputs){
        PrefixNode root = new PrefixNode();
        for(int i=0; i<inputs.length; i++){

            String reverted_word = revert(inputs[i]);
            root = Insert(reverted_word,i,root);

        }

        for(int i=0; i<inputs.length; i++) {
            Map<String, Integer> matchedWords = maxMatchInTree(inputs[i], root);
            for(String el:matchedWords.keySet()) {
                String revert_back = revert(el);
                if (matchedWords.get(el) != i && isPalindrome(inputs[i] + revert_back))
                    System.out.println(inputs[i] + " " + revert_back);
            }
        }


    }
    public String revert(String s){
        StringBuffer ret = new StringBuffer();
        for(int i=(s.length()-1); i>=0; i--)
            ret.append(s.charAt(i));
        return ret.toString();
    }

    public boolean isPalindrome(String input){
        int front = 0;
        int back = input.length() - 1;

        while(front < back){
            if(input.charAt(front) != input.charAt(back))
                return false;
            front++;
            back--;
        }

        return true;

    }

    public class PrefixNode{
        HashMap<Character, PrefixNode> children;
        boolean isWord;
        int index;

        public PrefixNode(){
            children = new HashMap();
            isWord = false;
            index = -1;
        }
    }

    public  PrefixNode Insert(String word, int index_in_list, PrefixNode root){
        PrefixNode r = root;
        for(int i=0; i<word.length(); i++){
            if(r.children.containsKey(word.charAt(i))){
                r = r.children.get(word.charAt(i));
            }
            else{
                PrefixNode c = new PrefixNode();
                r.children.put(word.charAt(i), c);
                r = c;
            }

        }
        r.isWord = true;
        r.index = index_in_list;

        return root;

    }

    public Map<String, Integer> maxMatchInTree(String s, PrefixNode root){
        StringBuffer s_r = new StringBuffer();
        Map<String, Integer> all_words = new HashMap();
        PrefixNode r = root;
        int current_index = 0;
        boolean isWord = false;

        while(r != null && current_index < s.length()){
            r = r.children.get(s.charAt(current_index));
            if(r!= null) {
                s_r.append(s.charAt(current_index));
                current_index++;
                if(r.isWord && s_r.length() > 0)
                    all_words.put(s_r.toString(), r.index);

            }
        }
        return all_words;
    }
}
