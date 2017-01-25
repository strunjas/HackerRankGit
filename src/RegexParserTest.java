/**
 * Created by sstrunjas on 12/4/16.
 */
import sun.awt.image.ImageWatched;

import java.util.*;
public class RegexParserTest {

    public static  void main(String[] args){
        String s = "aaabbcbbabaaaa";
        String regex = "a*a+b*.b+a+.a+";


        boolean match = isMatch2(s, 0, regex, 0);
        if(match)
            System.out.println("yes");
        else
            System.out.println("no");

    }


    public static boolean isMatch2(String input, int index_s, String regex, int index_r){
        if(index_s == input.length()){
            if(index_r >= regex.length())
                return true;
            else{
                while(index_r < regex.length()){
                    if(regex.charAt(index_r) == '.')
                        return false;
                    else if((index_r+1) < regex.length() && regex.charAt(index_r+1)=='+'){
                        if(input.charAt(input.length()-1) == regex.charAt(index_r)){
                            String newRegex = regex.substring(0,index_r+1) + "*" + regex.substring(index_r+2);
                            return isMatch2(input, index_s, newRegex, index_r);
                        }
                        else
                            return false;
                    }
                    else if((index_r + 1) < regex.length() && regex.charAt(index_r+1) == '*'){

                        return isMatch2(input, index_s, regex, index_r+2);

                    }
                    else return false;
                }
            }
        }
        else{
            if(index_r >= regex.length())
                return false;
           else{
                if(regex.charAt(index_r) == '.')
                    return isMatch2(input, index_s+1, regex, index_r+1);
                else if((index_r+1) < regex.length() && regex.charAt(index_r+1)=='+'){
                    if(input.charAt(index_s) == regex.charAt(index_r)){
                        String newRegex = regex.substring(0, index_r+1) + "*" + regex.substring(index_r+2);
                        return isMatch2(input, index_s+1, newRegex, index_r);
                    }
                    else return false;
                }
                else if((index_r+1)<regex.length() && regex.charAt(index_r+1) == '*'){
                    if(input.charAt(index_s) == regex.charAt(index_r)){
                        boolean val1 = isMatch2(input, index_s+1, regex, index_r+2);
                        boolean val2 = isMatch2(input, index_s, regex, index_r+2);
                        boolean val3 = isMatch2(input, index_s+1, regex, index_r);
                        return val1||val2||val3;
                    }
                    else{
                        return isMatch2(input, index_s, regex, index_r+2);
                    }
                }
            }
        }

        return false;
    }


}
