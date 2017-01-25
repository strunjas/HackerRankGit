import java.util.HashSet;

/**
 * Created by sstrunjas on 11/18/16.
 */
public class matrix {
    public static final int MOD = 1000000007;
    static int numberOfPaths(int[][] a) {
        java.util.LinkedList<Integer> q = new java.util.LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int[][] computedPath = new int[a.length][a[0].length];
        for(int i=0; i<a.length; i++)
            for(int j=0; j<a[0].length; j++)
                computedPath[i][j] = 0;
        if(a[0][0] == 0)
            return 0;
        computedPath[0][0] = 1;
        q.addLast(0);
        visited.add(0);


        while(!q.isEmpty()){
            int current_index = q.removeFirst();
            int r = current_index/a[0].length;
            int c = current_index%a[0].length;
            if(r >= a.length || c >= a[0].length)
                continue;
            if((r+1) < a.length && a[r+1][c]>0 ){
                computedPath[r+1][c] = (computedPath[r+1][c] + computedPath[r][c])%MOD;
                if(!visited.contains((r+1)*a[0].length+c)) {
                    q.addLast((r + 1) * a[0].length + c);
                    visited.add((r + 1) * a[0].length + c);
                }
            }
            if((c+1) < a[0].length && a[r][c+1]>0 ){
                computedPath[r][c+1] = (computedPath[r][c+1] + computedPath[r][c])%MOD;
                if(!visited.contains(r*a[0].length + c+ 1)) {
                    q.addLast(r * a[0].length + c + 1);
                    visited.add(r * a[0].length + c + 1);
                }
            }
        }

        return computedPath[a.length-1][a[0].length-1];
    }




    public static void main(String[] args) throws Exception{
        int[][] a = new int[3][4];
        for(int i=0; i<3; i++)
            for(int j=0; j<4; j++)
                a[i][j] = 1;

        int paths = numberOfPaths(a);
        System.out.println(paths);

    }

}
