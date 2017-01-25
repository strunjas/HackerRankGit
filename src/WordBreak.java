/**
 * Created by sstrunjas on 1/22/17.
 */
import java.util.*;
public class WordBreak {


    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList();
        if(s.equals(""))
            return ret;
        HashMap<String, List<String>> memoized = new HashMap();
        ret = findAllSolutions(s, memoized, wordDict);
        return ret;

    }
    public static List<String> findAllSolutions(String s, HashMap<String, List<String>> memoized, List<String> dict){
        if(memoized.containsKey(s))
            return memoized.get(s);

        List<String> ret = new ArrayList();
        if(s.equals("")){
            ret.add(s);
            return ret;

        }

        for(String word:dict){
            if(s.startsWith(word)){
                List<String> substring_res = findAllSolutions(s.substring(word.length()), memoized, dict);
                for(String substring_word:substring_res){
                    if(substring_word.length() > 0){
                        ret.add(word + " " + substring_word);
                    }
                    else
                        ret.add(word);
                }
            }
        }

        memoized.put(s, ret);
        return ret;
    }



    public static void main(String[] args){
        String[] wordDict = {"aaaa","aaa"};
        List<String> dict = new ArrayList(Arrays.asList(wordDict));
        List<String> results = wordBreak("aaaaaaa", dict);
    }
}
