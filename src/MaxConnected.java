import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaxConnected {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n= s.nextInt();
        int[][] array = new int[m][n];
        for(int i=0; i<m;i++)
            for(int j=0; j<n; j++)
                array[i][j] = s.nextInt();

        System.out.println(MaxConnectedCell(array, m, n));

    }
    public static int MaxConnectedCell(int[][] array, int m, int n){
        Boolean[] visited = new Boolean[m*n];
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                visited[i*n+j] = false;
        LinkedList<Integer> queue = new LinkedList();
        int max_connected = 0;
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                if(!visited[i*n+j] && array[i][j] == 1){
                    queue.addLast(i*n+j);
                    visited[i*n+j] = true;
                }
                int current_region = 0;
                while(!queue.isEmpty()){
                    int current_index = queue.poll();
                    current_region++;
                    int row = current_index/n;
                    int col = current_index%n;
                    if((col+1) < n && array[row][col+1]==1 && !visited[row*n+col+1]){
                        queue.addLast(row*n+col+1);
                        visited[row*n+col+1] = true;
                    }
                    if((row+1)<m && array[row+1][col] == 1 && !visited[(row+1)*n +col]){
                        queue.addLast((row+1)*n+col);
                        visited[(row+1)*n+col] = true;
                    }
                    if((row+1)<m && (col+1)<n && array[row+1][col+1] == 1 && !visited[(row+1)*n+(col+1)]){
                        queue.addLast((row+1)*n+col+1);
                        visited[(row+1)*n+col+1] = true;
                    }
                }
                if(current_region > 0)
                    max_connected = Math.max(max_connected, current_region);
            }
        return max_connected;
    }
}
