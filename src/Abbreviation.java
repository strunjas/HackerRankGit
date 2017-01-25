import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Abbreviation {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int num_queries = s.nextInt();
        s.nextLine();
        boolean[] results = new boolean[num_queries];
        for(int i=0; i<num_queries; i++){
            String a = s.nextLine().trim();
            String b = s.nextLine().trim();
            results[i] = isMatch(a.toCharArray(), 0, b.toCharArray(), 0);
        }

        for(int j=0; j<results.length; j++)
            if(results[j])
                System.out.println("YES");
            else
                System.out.println("NO");

    }

    public static boolean isMatch(char[] a, int pos_a, char[] b, int pos_b){
        if(pos_b == b.length){
            for(int i=pos_a; i<a.length; i++){
                if(isUpperCase(a[i]))
                    return false;
            }

            return true;
        }
        else if(pos_b < b.length && pos_a >= a.length)
            return false;
        else if(isLowerCase(a[pos_a]) && toUpperCase(a[pos_a])!=b[pos_b])
            return isMatch(a, pos_a+1, b, pos_b);
        else if(isLowerCase(a[pos_a]) && toUpperCase(a[pos_a]) == b[pos_b])
            return isMatch(a,pos_a+1, b, pos_b) || isMatch(a, pos_a+1, b, pos_b+1);
        else if(a[pos_a] == b[pos_b])
            return isMatch(a, pos_a+1, b, pos_b+1);
        return false;


    }
    public static boolean isLowerCase(char c){
        return (c >= 'a' && c<='z');
    }
    public static boolean isUpperCase(char c){
        return (c >='A' && c <='Z');
    }
    public static char toUpperCase(char c){
        if(isLowerCase(c))
            return (char)(c-'a'+'A');
        return c;
    }

}