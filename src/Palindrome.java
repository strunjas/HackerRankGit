import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Palindrome {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        in.nextLine();
        String input_s = in.nextLine();

        String res = maxPalindrome(input_s.toCharArray(), k);
        System.out.print(res);
    }
    public static String maxPalindrome(char[] chars, int max_changes){

        if(max_changes >= chars.length){
            for(int i=0; i<chars.length; i++)
                chars[i] = '9';
            return new String(chars);
        }
        else{
            boolean[] changes = new boolean[chars.length];
            int start = 0;
            int end = chars.length-1;
            int num_changes = 0;

            while(start < end && num_changes < max_changes){

                if(chars[start] > chars[end]){
                    chars[end] = chars[start];
                    changes[end] = true;
                    num_changes++;
                }
                else if(chars[start] < chars[end]){
                    chars[start] = chars[end];
                    changes[start] = true;
                    num_changes++;
                }
                start++;
                end--;
            }


            for(int i=start,j=end; i<j; i++, j--)
                if(chars[i]!=chars[j])
                    return "-1";

            if(num_changes == max_changes)
                return new String(chars);

            start = 0;
            end = chars.length-1;

            while(start < end && num_changes< max_changes) {
                if (chars[start] < '9') {
                    if (!changes[start] && !changes[end] && (num_changes + 2) <= max_changes) {
                        chars[start] = '9';
                        chars[end] = '9';
                        changes[start] = true;
                        changes[end] = true;
                        num_changes += 2;

                    } else if(changes[start] || changes[end]){
                        chars[start] = '9';
                        chars[end] = '9';
                        if(!changes[start])
                            changes[start] = true;
                        if(!changes[end])
                            changes[end] = true;
                        num_changes++;
                    }


                }
                start++;
                end--;
            }

            start = 0;
            end = chars.length-1;

            while(start < end && max_changes>num_changes){
                if(chars[start] < '9'){
                    if(changes[start]|| changes[end]) {
                        chars[start] = '9';
                        chars[end] = '9';
                        if(!changes[start])
                            changes[start] = true;
                        if(!changes[end])
                            changes[end] = true;
                        num_changes++;
                    }
                    else if((num_changes+2)<=max_changes){
                        chars[start] = '9';
                        chars[end] = '9';
                        changes[start] = true;
                        changes[end] = true;
                        num_changes+=2;

                    }
                }

                start++;
                end--;
            }

            if(chars.length%2==1 && num_changes < max_changes){
                if(chars[chars.length/2] < '9'){
                    chars[chars.length/2] = '9';
                    num_changes++;
                }
            }






        }
        return new String(chars);
    }
}
