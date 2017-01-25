import java.util.*;
public class UTF8 {
    public static boolean validUtf8(int[] data) {

        BitSet b = new BitSet(data.length*8);

        for(int i=0; i<data.length;i++){
            int position = (data.length-i)*8 - 1;
            int mask1 = 255;
            int mask2 = 128;
            int result = mask1&data[i];
            String result_b = "";
            String result2_b = "";
            String result_b_after = "";

            while(result > 0){
                result_b = Integer.toBinaryString(result);
                while(result_b.length() < 8)
                    result_b = "0" + result_b;
                int result2 = result&mask2;
                result2_b = Integer.toBinaryString(result2);
                while(result2_b.length()<8)
                    result2_b = "0" + result2_b;
                if(result2 > 0)
                    b.set(position);
                position -= 1;
                result = (result<<1)&mask1;
                result_b_after = Integer.toBinaryString(result);
                while(result_b_after.length()<8)
                    result_b_after = "0" + result_b_after;
            }

        }

        int current_position = data.length*8 - 1;

        while(current_position > 0){

            int leading_ones = getLeadingOnes(b, current_position);
            if(leading_ones > 4)
                return false;
            if(leading_ones == 0){
                current_position -= 8;
                if(current_position < -1)
                    return false;
            }
            else{
                current_position -= 8;
                if(current_position <= 0)
                    return false;
                for(int k=0; k< (leading_ones-1); k++){
                    if(current_position <= 0)
                        return false;
                    if(!b.get(current_position) || b.get(current_position-1))
                        return false;
                    current_position -= 8;
                }

            }


        }
        if(current_position < -1)
            return false;
        return true;

    }

    public static int getLeadingOnes(BitSet b, int startPosition){
        int counter = 0;
        if(startPosition < 0)
            return 0;
        while(startPosition >=0 && b.get(startPosition)){
            counter++;
            startPosition--;
        }

        return counter;
    }

    public static void main(String[] args){
        int[] input = new int[]{234,175,134,83,245,153,182,153,13,207,143,227,141,171,241,180,140,159,213,146,233,
                139,139,243,158,146,156,58,217,168,232,138,158,245,149,191,169,206,136,224,128,158,202,150,238,130,
                166,232,130,153,240,152,182,185,222,139,244,132,175,178,228,177,132,243,157,138,139,69,236,170,178,
                8,201,140,237,187,153,241,166,148,182,202,182,230,171,190,247,165,135,130,211,164,236,176,133,244,
                158,156,189,219,136,230,190,164,193,136,247,186,174,146,225,151,187,116,204,144,246,170,183,178,9,231,
                153,129,65,224,166,185,5,225,154,188,123,229,139,145,55,211,139,231,173,166,246,178,131,147,232,160,173,
                247,169,187,131,84,239,172,143,58,242,190,171,158,66,226,140,154,211,157,247,129,147,166,205,174,229,185,
                148,217,168,244,181,165,169,228,132,191,247,165,136,162,241,155,139,160,205,158,222,173,230,129,140,246,
                163,170,158,225,159,159,206,185,237,174,176,214,164,231,176,139,243,181,171,153,209,159,237,138,142,230,
                173,166,241,165,181,173,213,185,235,165,137,245,184,172,132,78,199,135,227,140,148,25,201,134,243,183,
                175,148,221,185,233,189,157,211,144,244,189,146,183,0,203,182,227,165,149,241,178,163,178,197,143,238,
                140,131,196,183,205,189,224,160,138,46,230,180,131,122,209,189,241,142,176,187,229,178,148,83,239,171,
                145,246,191,157,160,214,177,199,132,242,128,176,176,67,216,178,245,140,168,179,77,242,143,191,135,209,161,241,
                165,142,158,118,239,176,172,243,152,168,176,193,143,232,146,180,243,150,137,181,198,151,236,177,164,196,169,247,
                140,164,128,72,101,227,138,139,92,195,189,231,130,173,247,172,159,155,122,214,128,234,138,163,80,206,142,229,144,
                190,194,151,225,141,170,246,164,154,149,95,214,147,225,164,150,101,238,137,143,243,167,151,188,210,166,231,139,145,
                90,238,154,185,236,187,181,195,144,230,163,130,85,200,189,236,166,169,103,203,141,230,131,172,79,122,208,144,233,141,
                153,243,130,172,146,79,244,164,165,153,40,204,158,241,164,159,169,238,169,174,202,176,214,166,238,153,160,240,143,150,
                132,86,213,130,238,177,175,243,141,163,171,8,238,148,165,245,134,154,141,196,175,236,164,170,242,155,161,155,233,166,
                138,247,137,187,156,98,26,230,155,160,194,165,232,142,186,239,151,189,242,151,177,159,218,149,232,152,162,242,157,163,
                167,118,235,162,171,201,156,218,132,232,172,180,209,158,237,148,189,92,209,158,226,187,177,19,225,131,154,15,196,128,
                53,245,175,163,172,93,105,247,167,171,174,46,210,183,226,155,166,108,201,184,226,132,187,210,191,224,159,164,243,172,
                144,141,223,177,233,180,144,231,130,187,229,168,157,244,168,178,139,230,174,171,237,130,175,198,154,227,130,163,67,
                244,141,190,176,18,236,158,165,124,244,187,150,166,215,151,230,137,189,247,173,138,172,235,153,187,114,230,187,176,
                125,246,165,164,169,226,176,141,212,156,125,242,145,183,162,211,130,224,148,166,246,151,167,181,126,245,135,155,186,
                115,216,170,229,136,189,56,214,146,237,185,187,246,185,166,176,119,243,144,168,179,233,145,142,241,174,159,155,235,155,
                168,243,161,164,131,26,226,163,141,244,144,184,141,86,192,178,58,239,175,138,206,171,244,140,190,165,6,204,178,236,186,
                189,234,190,161,112,209,156,224,128,173,203,165,238,129,161,228,142,130,230,130,132,114,232,161,189,246,162,161,135,218,
                150,1,213,161,236,163,172,81,197,169,226,172,190,235,130,158,241,131,168,130,192,170,237,136,141,194,156,239,155,186,241,130,133,172,101,76,192,182,237,190,128,240,164,175,154,220,158,241,139,169,166,108,228,149,169,245,159,146,130,230,141,144,241,153,147,144,203,186,234,137,131,70,214,155,232,158,139,49,209,168,234,188,138,54,1,204,135,227,162,178,240,190,134,188,5,10,200,134,126,227,186,147,230,168,175,247,166,184,183,227,183,137,242,183,161,189,74,202,157,231,147,139,221,155,229,147,171,220,149,238,140,155,242,171,181,171,76,231,173,176,244,173,167,146,209,191,226,187,158,212,172,234,151,183,243,148,131,187,245,191,132,167,102,215,161,227,138,142,247,186,173,169,6,217,137,103,196,167,236,163,185,228,158,154,245,149,188,166,108,230,141,148,244,188,146,168,247,175,191,187,89,231,171,175,242,144,170,175,227,128,187,243,132,148,138,240,175,146,143,96,212,139,224,138,138,23,222,162,21,232,128,141,240,164,161,171,53,246,185,160,163,122,236,129,182,29,237,160,128,244,161,168,160,70,217,156,247,156,162,186,224,131,183,240,138,159,189,122,127,232,177,180,240,144,180,170,218,190,241,164,155,136,40,212,169,226,188,188,243,149,136,151,218,163,232,164,150,32,236,185,133,246,129,150,153,221,150,241,148,144,157,210,166,229,143,190,247,134,154,153,15,207,168,237,153,160,242,184,157,134,206,128,241,152,132,144,22,220,162,235,175,151,211,136,235,165,134,247,183,129,159,210,186,231,158,151,242,185,191,166,90,219,167,227,167,153,237,167,158,231,156,177,245,183,142,160,229,140,178,244,150,174,188,225,174,156,247,170,132,137,92,216,170,231,166,162,220,131,235,191,186,243,158,174,129,224,170,157,122,217,177,246,147,137,157,235,156,184,243,166,160,181,90,203,184,225,179,191,58,223,141,236,188,157,207,135,240,171,184,169,84,229,177,159,239,175,180,243,145,138,162,17,219,167,230,159,146,236,155,185,233,165,145,240,162,175,179,243,135,149,138,236,158,172,230,157,155,246,128,172,144,109,195,139,239,146,168,244,170,158,173,222,148,239,182,144,232,186,130,247,165,154,178,5,225,138,161,56,210,146,232,162,154,198,190,237,166,180,221,140,239,150,133,247,163,165,182,84,218,174,232,178,133,240,148,172,150,213,134,225,170,155,15,206,133,230,153,165,104,194,183,230,164,128,240,175,180,142,57,205,169,236,188,142,244,180,186,137,39,218,137,225,147,179,246,168,136,165,5,228,180,145,125,234,170,139,103,232,133,189,244,166,128,185,127,242,176,155,145,205,178,233,161,186,208,178,231,134,146,247,174,153,190,223,168,17,216,189,247,191,161,163,85,245,160,155,155,56,203,184,93,229,151,169,209,150,231,177,153,61,210,151,227,130,170,236,153,141,242,187,177,171,1,202,178,236,144,189,97,227,166,143,240,153,180,165,72,234,159,136,241,179,135,147,100,221,174,97,83,209,148,217,173,237,180,154,247,190,186,165,35,229,166,157,246,189,182,156,50,192,131,234,136,170,68,234,185,184,15,210,171,237,157,182,247,176,175,142,203,186,228,189,171,216,150,238,177,146,100,234,154,185,241,135,188,188,54,247,148,146,157,218,153,237,155,168,217,162,228,172,134,226,147,146,247,164,174,190,89,230,181,155,244,128,133,186,56,235,156,157,194,145,241,178,161,188,126,199,153,241,181,181,186,66,216,158,242,141,160,164,215,191,227,159,177,246,184,170,166,200,190,230,128,171,193,131,229,161,144,68,206,181,246,134,139,161,74,224,137,143,18,200,149,233,165,178,244,182,172,163,239,147,187,242,133,162,159,46,211,180,236,188,187,242,180,184,157,203,147,229,142,183,245,156,178,155,15,193,140,235,178,176,241,133,185,173,237,181,145,242,159,182,161,199,130,247,153,131,167,23,206,157,236,154,187,201,136,84,203,187,227,178,170,245,145,161,170,89,209,181,236,187,184,246,161,189,140,4,204,143,239,178,171,247,160,154,145,30,241,168,188,148,10,206,151,243,167,182,170,28,223,140,233,157,182,202,179,230,135,142,242,143,165,169,4,197,174,214,184,227,174,175,220,162,228,176,183,217,171,235,176,177,105,225,183,133,247,180,152,155,61,243,175,173,128,209,129,226,158,167,247,171,175,168,119,224,165,150,243,130,190,144,125,228,145,140,245,174,131,148,226,163,176,122,239,181,177,246,151,138,131,103,209,141,239,144,145,101,212,149,225,174,177,245,144,169,143,38,210,164,234,179,141,247,172,183,128,220,160,226,151,166,227,173,135,246,170,168,165,29,244,168,143,177,88,208,178,245,134,144,167,75,202,138,39,227,188,156,118,242,151,138,172,234,139,191,245,137,182,136,197,188,240,191,140,165,75,193,162,237,175,130,193,167,234,157,147,241,130,168,165,125,242,180,174,161,244,173,162,128,39,229,161,145,20,194,145,244,167,140,162,219,133,243,148,176,182,4,229,151,139,49,239,137,165,15,218,170,237,153,176,241,166,153,161,125,214,136,120,226,189,174,244,189,143,191,236,173,138,12,245,177,178,163,28,195,158,226,181,178,244,177,183,144,21,233,161,131,242,167,179,128,200,173,239,189,130,242,141,186,184,215,191,237,135,144,219,187,236,180,150,244,131,147,188,115,194,173,225,149,182,244,191,134,156,195,167,238,149,188,79,223,139,55,232,129,181,246,180,151,178,55,202,183,239,180,152,247,167,154,158,208,133,236,131,162,235,166,176,245,151,185,148,8,232,155,190,222,141,234,144,134,229,146,165,233,135,140,243,180,169,169,212,180,232,143,142,88,246,178,178,182,231,134,149,214,181,232,160,128,214,186,232,176,158,209,188,114,209,138,225,186,168,247,190,128,167,206,155,237,140,168,212,181,238,165,142,245,147,144,139,81,247,169,188,171,211,148,246,163,176,138,104,243,142,146,150,103,230,133,175,228,176,144,246,179,170,176,24,226,181,146,203,154,236,182,176,204,180,236,128,154,243,172,175,160,12,208,158,236,183,165,36,228,174,180,243,168,161,183,12,200,187,229,150,160,229,162,171,245,143,143,152,12,216,180,124,239,181,131,241,184,141,148,235,183,140,240,132,140,186,8,217,189,238,168,183,101,238,144,170,242,142,172,141,206,188,241,154,177,162,209,150,121,240,176,181,130,16,229,179,149,115,210,182,239,165,146,243,170,175,155,192,130,233,183,145,53,218,157,229,145,181,247,142,135,172,203,166,234,152,140,209,187,238,168,166,242,132,187,151,31,230,136,160,66,234,158,191,195,180,242,190,141,158,51,196,143,234,152,139,202,140,243,152,177,142,2,192,156,238,187,137,246,180,151,190,22,230,169,168,230,159,147,246,136,160,140,98,201,176,233,136,158,242,150,185,189,101,237,136,167,45,196,187,228,172,146,38,227,188,156,118,193,154,236,135,135,245,137,137,133,193,176,237,187,175,216,132,232,189,166,246,128,178,186,25,194,162,239,166,191,78,226,173,134,245,138,169,190,95,11,238,156,150,243,149,174,154,238,153,168,247,178,177,157,224,142,128,242,186,155,168,101,244,139,143,160,57,232,136,150,242,150,178,162,205,154,232,130,180,240,142,160,187,11,232,156,174,244,183,163,170,46,220,150,240,161,159,144,200,132,224,160,130,65,210,187,238,156,183,242,174,170,160,80,218,160,236,155,142,247,159,161,177,229,156,186,224,182,162,81,225,142,180,245,181,188,188,57,215,183,227,177,147,23,210,185,224,151,185,195,183,233,179,128,93,208,160,226,180,137,247,160,145,161,82,210,170,236,145,131,213,140,238,170,159,242,138,161,190,235,175,142,6,245,131,183,147,230,183,182,244,161,141,153,209,190,232,170,166,247,169,178,186,213,151,245,144,171,173,231,171,133,50,207,156,234,131,137,243,187,146,128,232,128,186,246,183,134,162,192,140,227,152,132,13,242,188,179,170,21,199,147,237,138,148,241,132,181,129,237,143,164,103,203,163,209,149,243,168,179,146,71,199,160,224,189,174,192,177,228,140,183,225,175,146,243,165,147,154,237,146,137,118,232,157,135,230,189,183,240,129,168,181,97,238,175,183,243,157,154,146,229,174,170,244,129,161,153,105,207,158,226,128,191,246,175,174,165,246,150,156,131,192,135,234,128,151,241,167,130,135,85,244,177,147,179,119,198,152,230,146,155,215,155,242,191,169,151,87,200,191,236,165,182,243,136,146,169,226,141,175,203,151,113,245,179,139,159,69,223,135,238,171,181,230,138,129,2,219,152,243,137,149,146,64,231,163,147,245,169,175,158,205,141,179,203,167,52,197,190,231,143,143,207,149,236,190,147,240,160,184,163,47,201,159,234,148,133,242,151,183,161,108,206,155,226,168,188,243,172,133,139,13,200,150,224,176,178,199,165,68,219,150,232,187,168,243,139,151,178,36,215,169,238,142,179,56,204,137,224,157,172,241,183,152,163,7,224,163,181,247,170,159,168,84,209,153,237,162,132,247,176,158,176,219,149,239,172,139,242,138,186,140,230,159,168,242,133,177,158,14,230,182,181,244,144,145,184,231,134,180,199,172,239,143,131,247,148,179,186,216,140,202,185,226,180,174,75,234,188,139,244,153,133,186,238,141,149,217,155,236,143,151,236,174,153,243,158,177,153,79,214,157,78,247,171,184,134,233,134,150,246,169,129,143,78,210,164,232,144,129,199,183,239,190,171,53,201,152,211,186,229,158,149,86,212,148,224,140,143,235,168,148,243,190,156,178,80,222,166,242,129,147,135,82,216,187,224,143,180,246,186,155,139,200,132,232,184,143,244,162,185,189,247,151,142,171,31,233,141,134,247,173,183,172,240,175,136,158,108,220,131,234,174,145,245,131,131,166,22,244,181,157,136,209,181,229,190,179,242,182,179,132,11,200,142,233,152,173,118,239,134,133,204,184,229,146,152,246,188,190,161,237,168,155,247,161,138,143,227,164,180,246,161,167,182,62,245,162,176,138,85,201,153,237,150,184,244,176,154,137,15,229,172,170,244,164,152,137,233,169,168,216,184,237,168,161,21,234,128,170,245,142,181,142,34,235,140,152,38,227,173,144,242,173,190,142,215,181,229,158,189,30,221,133,235,168,169,242,163,168,144,41,205,158,227,137,183,240,188,172,174,3,206,128,234,172,153,247,171,151,144,37,226,131,159,244,150,179,188,42,239,154,149,210,187,240,172,138,163,47,245,129,150,149,226,156,139,241,141,143,177,45,216,141,226,176,129,52,230,160,173,244,142,169,151,41,226,172,135,42,214,141,225,166,139,243,185,166,161,12,193,168,228,181,130,237,163,175,244,133,148,154,200,142,238,175,129,243,152,172,163,211,168,231,188,128,11,26,232,130,145,219,136,236,136,144,240,153,167,173,13,215,177,240,153,177,148,226,154,139,245,147,188,130,204,165,233,191,148,56,232,159,147,82,223,172,242,137,161,164,210,147,235,146,162,106,224,160,167,245,157,148,175,204,164,238,183,134,229,134,133,7,231,134,169,194,173,229,186,181,230,189,190,242,132,160,183,62,229,137,143,244,150,182,159,52,220,130,233,151,152,244,151,186,166,216,191,234,166,176,245,140,160,139,79,243,143,161,178,77,243,128,128,137,63,195,138,226,153,137,241,131,142,186,195,188,8,216,140,229,175,178,226,174,180,30,227,148,136,95,214,167,238,179,128,243,179,151,181,29,195,145,246,169,141,165,45,209,156,224,178,154,240,141,185,163,197,171,226,156,148,240,165,177,145,228,162,132,206,157,236,151,151,22,235,153,133,240,147,128,180,233,167,184,210,161,228,179,144,93,207,152,228,134,185,246,173,157,139,52,223,186,217,186,233,128,175,115,231,188,161,242,142,140,141,1,236,188,166,13,230,173,179,196,191,224,140,184,246,177,182,138,232,171,140,241,185,174,187,196,186,225,149,131,231,154,168,244,149,170,130,194,157,244,170,191,140,210,133,230,133,155,204,186,224,143,185,245,155,128,135,2,226,145,172,11,224,143,191,241,128,141,155,200,177,241,183,156,160,219,177,225,137,133,233,190,175,243,138,169,169,90,213,140,225,172,150,246,145,131,140,223,138,237,133,145,56,204,141,246,130,159,143,227,159,133,241,176,159,129,72,222,154,237,161,144,245,157,172,150,202,150,237,138,169,223,171,227,132,143,243,169,157,176,71,228,174,165,57,227,179,168,240,174,146,171,235,147,174,203,144,239,191,132,243,147,154,173,240,182,131,140,124,246,188,146,150,103,230,180,190,240,152,182,168,231,167,171,242,180,191,130,224,183,151,26,232,141,132,243,172,167,153,80,218,147,192,185,225,176,148,246,155,153,146,219,163,231,138,187,211,159,226,178,150,242,143,182,147,125,241,160,160,139,199,172,241,186,185,191,78,237,181,177,57,231,177,188,242,136,179,150,244,160,154,171,247,179,182,162,199,143,232,140,170,1,216,131,233,175,134,233,159,156,201,154,233,173,154,247,133,153,181,201,128,247,186,148,161,58,197,135,228,162,178,11,226,131,137,241,163,132,147,76,197,160,234,137,135,246,142,150,146,112,235,146,165,223,146,226,182,133,228,167,180,226,145,185,203,146,233,136,142,242,129,161,135,222,176,229,164,168,247,176,132,153,32,243,132,168,140,231,153,149,244,166,149,140,230,151,181,23,223,136,226,140,178,23,236,184,190,92,13,230,140,160,198,149,93,223,137,240,177,128,172,194,167,228,149,134,110,241,133,155,142,95,194,168,226,153,159,243,152,162,173,233,130,138,231,171,161,246,154,180,175,230,174,174,246,144,174,133,227,165,190,239,155,160,246,149,141,147,219,130,167,113,225,151,149,32,242,164,131,175,234,152,175,241,160,154,177,6,229,167,163,34,229,154,158,198,165,238,148,156,245,182,176,151,208,172,227,182,138,53,235,185,177,246,142,153,131,90,231,156,172,234,152,178,244,142,188,185,53,238,170,130,245,135,177,135,63,221,187,241,177,185,153,210,140,229,143,139,244,154,156,134,13,224,163,144,240,139,141,191,120,194,136,228,149,152,244,164,155,168,111,192,188,225,189,149,244,150,172,154,206,157,230,159,144,240,185,144,156,241,140,164,139,97,205,183,231,140,164,28,193,172,233,165,172,242,165,143,133,208,180,245,174,191,140,231,179,191,244,160,175,128,206,142,244,158,165,165,84,226,159,186,89,198,152,234,184,191,23,194,169,230,136,130,247,155,176,183,196,142,231,168,138,240,154,135,185,233,138,142,246,132,187,168,233,142,130,29,247,185,129,143,221,135,245,189,141,154,234,138,148,204,144,242,149,156,159,230,167,132,230,186,129,240,187,183,160,203,174,232,148,137,102,199,172,235,188,183,240,166,157,146,195,191,241,166,164,191,76,214,184,225,180,148,117,195,190,230,189,134,247,172,131,142,208,140,237,189,187,95,229,133,133,241,148,167,153,197,133,230,145,142,235,164,191,244,184,182,171,112,240,148,146,191,2,201,156,244,137,184,131,56,224,166,181,240,151,164,176,206,189,10,217,157,49,218,169,113,204,136,194,137,224,171,131,23,217,166,217,150,242,164,147,151,214,135,245,190,136,171,197,144,247,154,187,161,120,234,150,143,31,224,131,143,210,165,196,180,88,221,174,239,138,159,245,145,191,160,229,191,128,219,174,230,149,168,245,141,134,156,229,131,177,98,232,143,171,5,228,162,153,209,162,231,137,154,65,227,129,136,240,179,134,129,109,208,143,236,154,172,112,216,139,21,196,150,231,129,149,106,231,132,171,126,207,162,224,183,137,226,154,156,46,214,153,65,224,148,179,246,137,175,174,214,136,233,163,139,247,189,161,172,64,219,148,235,139,152,46,231,164,138,89,229,128,153,241,132,149,148,243,164,152,181,100,196,155,245,165,140,142,83,197,129,233,150,143,246,159,166,134,231,161,164,245,191,174,180,65,236,149,182,246,177,186,172,192,174,241,151,171,160,25,206,136,226,155,147,89,209,176,235,156,169,247,138,162,136,95,247,149,181,189,63,227,185,153,216,140,241,166,160,161,237,141,148,229,167,171,242,184,142,168,217,156,234,157,131,246,165,179,142,208,148,237,162,182,120,217,134,234,150,181,241,141,187,156,124,224,157,133,60,231,142,133,240,155,144,156,220,183,233,178,145,58,231,151,144,222,132,227,159,181,247,146,140,145,72,210,187,231,133,176,247,142,169,174,76,210,170,246,142,149,141,89,21,216,172,232,189,173,20,244,146,174,140,205,185,231,135,140,246,161,158,175,46,204,168,235,149,137,240,138,158,183,215,134,241,139,146,153,205,150,228,161,133,232,143,130,72,239,156,148,99,237,142,171,246,175,164,131,213,146,229,148,131,245,139,137,156,48,211,143,233,173,171,240,174,165,135,210,145,228,162,191,242,187,161,147,237,143,191,247,173,154,175,52,238,182,183,244,151,136,189,205,178,236,177,179,221,145,245,149,166,156,121,78,204,172,233,158,172,247,160,185,173,227,186,164,240,129,172,181,192,144,229,152,168,247,136,180,148,30,212,152,237,184,154,228,171,141,92,228,156,186,244,174,175,154,196,166,247,131,171,140,204,177,238,169,191,245,190,159,185,29,202,183,232,134,186,247,156,190,178,205,131,238,137,132,69,206,191,233,156,167,44,235,137,180,243,187,148,133,122,193,190,113,225,133,144,239,142,177,4,207,137,230,131,142,242,189,177,175,107,225,183,173,80,226,163,179,247,176,140,176,197,186,226,171,165,246,144,155,163,238,179,188,215,187,231,135,130,212,171,240,187,174,161,34,193,142,120,203,143,237,150,140,234,129,158,72,208,153,242,164,176,142,70,229,189,184,242,152,128,144,11,215,165,225,173,180,246,138,154,128,244,149,180,169,228,149,173,197,157,230,138,151,240,161,145,144,94,91,213,176,233,133,159,243,152,172,152,79,68,91,237,191,130,242,181,133,183,234,173,163,246,187,172,142,82,221,153,244,143,168,130,111,192,180,238,174,151,241,132,175,180,233,139,155,241,180,154,148,124,231,153,146,242,165,190,177,217,129,228,156,166,240,128,129,184,194,165,228,173,138,235,142,151,199,167,227,141,175,245,176,172,183,25,241,149,163,165,75,226,138,136,247,143,188,137,231,168,146,39,205,180,226,152,146,100,192,175,229,182,164,247,151,155,183,96,231,178,169,78,213,160,246,164,142,190,19,199,164,239,128,128,243,160,173,180,29,222,187,224,176,168,241,166,131,191,224,170,156,241,156,169,166,195,161,243,177,156,163,209,190,192,142,234,170,148,246,189,185,149,241,132,177,149,227,160,164,115,201,158,227,178,154,247,147,142,183,224,164,144,243,147,151,189,34,237,161,146,209,180,240,160,152,168,198,156,236,167,171,244,177,171,134,193,146,244,162,178,161,105,225,155,163,48,198,155,235,141,188,245,188,181,174,219,161,236,149,181,242,161,150,133,239,164,170,71,199,180,236,163,187,212,146,75,192,143,230,147,187,63,221,176,225,157,182,103,204,155,237,133,185,56,239,179,190,241,179,169,165,95,221,159,241,145,190,187,98,229,129,162,242,148,150,147,109,116,221,147,230,149,154,67,218,145,224,164,162,241,186,128,181,222,157,243,146,129,135,75,237,171,145,213,134,240,152,141,141,55,202,180,237,134,136,240,141,176,188,103,192,140,235,129,177,246,137,177,156,206,145,226,152,177,230,172,166,52,218,175,236,136,175,241,139,172,171,200,188,234,132,182,119,235,179,187,242,149,142,179,98,208,173,236,129,158,242,154,142,132,52,202,171,229,150,167,237,167,155,246,171,188,130,32,234,175,171,244,160,173,158,83,228,151,130,113,224,162,186,244,153,139,134,51,214,148,232,156,130,206,190,237,142,164,240,173,191,146,62,228,189,149,240,139,139,167,212,135,239,149,180,242,130,133,170,105,50,216,135,225,147,186,203,169,234,190,155,81,223,132,247,189,166,138,66,203,163,243,129,167,157,239,160,130,221,152,224,167,172,212,147,231,157,132,246,172,160,170,228,163,187,207,166,242,134,168,174,216,180,237,183,188,247,166,165,187,0,226,161,163,240,137,176,167,224,172,139,215,166,238,141,180,106,207,189,231,153,148,243,132,179,174,247,160,154,140,26,223,135,237,164,131,220,190,233,146,128,245,191,155,151,203,169,244,163,134,180,207,150,231,160,128,109,232,174,166,245,177,179,161,106,209,142,240,190,182,135,79,227,147,165,227,171,183,220,130,237,144,146,241,144,137,144,95,226,171,169,233,134,151,245,175,142,161,46,243,151,139,156,211,150,239,128,160,14,193,166,230,149,187,247,176,181,138,85,241,148,173,156,91,233,160,182,62,227,167,164,247,154,165,142,25,209,180,238,156,157,16,223,171,233,144,148,214,149,239,150,150,243,146,179,154,88,229,146,177,241,145,144,135,213,170,208,183,228,170,159,244,141,128,157,232,151,172,247,179,131,135,218,139,242,131,178,143,225,187,158,206,157,229,172,145,194,158,80,209,177,239,175,170,247,168,128,166,84,244,128,176,163,101,197,153,232,170,132,243,155,152,139,106,225,128,145,240,191,175,141,112,111,197,153,228,161,170,18,245,144,144,141,215,150,105,195,151,225,157,148,246,191,151,187,219,173,235,189,164,81,229,154,140,117,193,142,197,173,226,145,189,246,143,179,171,55,199,135,240,171,183,174};
        boolean t = validUtf8(input);
    }
}