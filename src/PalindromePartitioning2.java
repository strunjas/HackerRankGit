public class PalindromePartitioning2 {

    public static int minCut(String s) {
        //partition according to the trivial cut first
        int[] cut = new int[s.length()+1];
        char[] char_s = s.toCharArray();
        for(int i=0; i<cut.length; i++){
            cut[i] = i-1;
        }

        for(int i=0; i<char_s.length; i++){
            for(int j=0; (i-j)>=0 && (i+j)<char_s.length && char_s[i+j] == char_s[i-j]; j++)
                cut[i+j+1] = Math.min(cut[i+j+1], 1+cut[i-j]);
            for(int j=1; (i-j+1)>=0 && (i+j)<char_s.length && char_s[i-j+1] == char_s[i+j]; j++)
                cut[i+j+1] = Math.min(cut[i+j+1], 1+cut[i-j+1]);
        }

        return cut[s.length()];

    }

    public static void  main(String[] args){
        String s = "aabab";
        int m_cut = minCut(s);
        System.out.println(m_cut);
    }





}