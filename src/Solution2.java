/**
 * Created by sstrunjas on 11/17/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution2 {

    static final long M = 1000000007;
    int[] s = new int[1001];

    public void run() throws IOException {

        countSplits();

        Scanner sc = new Scanner(System.in);
        int tcs = sc.nextInt();

        for (int t = 0; t < tcs; t++) {

            int h = sc.nextInt();
            int w = sc.nextInt();

            int[] pows = new int[w + 1];
            for (int i = 0; i <= w; i++) {
                pows[i] = powmod(s[i], h);
            }

            int[] sConstr = new int[w + 1];
            sConstr[1] = 1;

            for (int i = 2; i <= w; i++) {
                sConstr[i] = pows[i];
                for (int j = 1; j < i; j++) {
                    sConstr[i] = subMod(sConstr[i], mulMod(sConstr[j], pows[i - j]));
                }
            }

            System.out.println(sConstr[w]);
        }
    }

    public int mulMod(int a, int b) {
        return (int) (((long) a * b) % M);
    }

    public int subMod(int a, int b) {
        return (int) (((long) a + M - b) % M);
    }

    public int addMod(int a, int b) {
        return (int) (((long)a + b) % M);
    }

    public int powmod(int a, int b) {
        long res = 1;
        long base = a % M;
        while (b != 0) {
            if (b % 2 == 1) {
                res = (res * base) % M;
            }
            base = (base * base) % M;
            b >>= 1;
        }
        return (int) (res % M);
    }

    private void countSplits() {
        for (int i = 1; i <= 4; i++) {
            s[i] = 1;
        }

        for (int i = 1; i < 1001; i++) {
            for (int j = 1; j <= 4; j++) {
                if (i - j >= 0) {
                    s[i] = addMod(s[i], s[i - j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Solution2().run();
    }
}