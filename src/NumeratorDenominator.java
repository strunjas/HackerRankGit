/**
 * Created by sstrunjas on 1/18/17.
 */
import java.util.*;
public class NumeratorDenominator {
    public static String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)
            return "0";
        StringBuilder ret = new StringBuilder();
        int sign_nom =  Integer.signum(numerator);
        int sign_den = Integer.signum(denominator);
        if(sign_nom != sign_den)
            ret.append("-");
        long numerator_l = 0L;
        long denominator_l = 0L;
        if(numerator < 0)
            numerator_l = (-1L)*numerator;
        else
            numerator_l = numerator;
        if(denominator < 0)
            denominator_l = (-1L)*denominator;
        else
            denominator_l = denominator;


        long res = numerator_l/denominator_l;
        ret.append(res);

        numerator_l = numerator_l%denominator_l;
        if(numerator_l > 0)
            ret.append(".");
        HashMap<Long, Integer> prev_vals_positions = new HashMap();
        long prev_numerator = 0L;

        int counter_pos = 0;
        while(numerator_l > 0){
            counter_pos+=1;
            prev_numerator = numerator_l;
            numerator_l *= 10;
            res = numerator_l/denominator_l;

            numerator_l = numerator_l%denominator_l;
            if(prev_vals_positions.containsKey(prev_numerator))
                break;

            else{
                prev_vals_positions.put(prev_numerator, counter_pos);
                ret.append(res);
            }
        }

        if(numerator_l > 0){
            int dotIndex = ret.indexOf(".");
            ret.insert(dotIndex+prev_vals_positions.get(prev_numerator), "(");
            ret.append(")");
        }

        return ret.toString();


    }
    public static void main(String[] args){
        String r = fractionToDecimal(-1,-2147483648);
        System.out.println(r);
    }
}