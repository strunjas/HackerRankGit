import java.util.*;
public class WordSearch {

    public class TrieNode{
        TrieNode[] next;
        String word;

        public TrieNode(){

           next = new TrieNode[26];


        }
    }
    public class Trie{
        TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        public void InsertWord(String word){
            TrieNode currentNode = root;
            char[] letters = word.toCharArray();
            for(int i=0; i<letters.length; i++){
                if(currentNode.next[letters[i]-'a'] == null){
                    currentNode.next[letters[i]-'a'] = new TrieNode();
                }
                currentNode = currentNode.next[letters[i]-'a'];
            }
            currentNode.word = word;
        }


    }
    public static List<String> findWords(char[][] board, String[] words) {
        WordSearch w = new WordSearch();
        List<String> wordsFound = new ArrayList();

        Trie t = w.new Trie();

        for(String word: words)
            t.InsertWord(word);

        for(int i=0; i< board.length; i++)
            for(int j=0; j<board[0].length; j++){

                traverseFromNode(i, j, board, t.root, wordsFound);


            }

        return wordsFound;

    }
    public static void traverseFromNode( int i, int j, char[][] board, TrieNode p, List<String> wordsFound){
        char c = board[i][j];

        if(c == '#' || p.next[c-'a'] == null)
            return;
        if(p.next[c-'a'].word != null){
            wordsFound.add(p.next[c-'a'].word);
            p.next[c-'a'].word = null;
        }
        p = p.next[c-'a'];

        board[i][j] = '#';
        if((i+1) < board.length)
            traverseFromNode(i+1, j, board, p, wordsFound);
        if((j+1) < board[0].length)
            traverseFromNode(i, j+1, board, p, wordsFound);
        if((i-1)>=0)
            traverseFromNode(i-1, j, board, p, wordsFound);
        if((j-1)>=0)
            traverseFromNode(i,j-1, board, p, wordsFound);

        board[i][j] = c;
        return;


    }

    public static void main(String[] args){

        char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
        };

        String[] dict = {"acdb"};

        List<String> l = findWords(board, dict);
        for(String w: l){
            System.out.println(w);
        }

    }
}