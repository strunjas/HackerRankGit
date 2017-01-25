/**
 * Created by sstrunjas on 1/20/17.
 */
public class TicTacToe {
    int[][] board;
    int size;
    int[][] player_rows;
    int[][] player_columns;
    int[]   player_diag1;
    int[]   player_diag2;




    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        size = n;
        board = new int[n][n];
        player_rows = new int[2][n];
        player_columns = new int[2][n];
        player_diag1 = new int[2];
        player_diag2 = new int[2];

        for(int i=0; i<n; i++){
            if(i<2) {
                player_diag1[i] = 0;
                player_diag2[i] = 0;
            }
            for(int j=0; j<n; j++) {

                board[i][j] = 0;
                if(i < 2){
                    player_rows[i][j] = 0;
                    player_columns[i][j] = 0;

                    }
            }
        }

    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(board[row][col] > 0)
            return 0;
        board[row][col] = player;
        if(row == col){
            player_diag1[player-1] += 1;
            if(player_diag1[player-1] == size)
                return player;
        }
        else if((row+col) == size){
            player_diag2[player - 1] += 1;
            if(player_diag2[player - 1] == size)
                return player;
        }
        player_rows[player-1][row] += 1;
        if(player_rows[player-1][row] == size)
            return player;
        player_columns[player-1][col] += 1;
        if(player_columns[player-1][col] == size)
            return player;

        return 0;


    }

    public static void main(String[] args){
        TicTacToe t = new TicTacToe(2);
        int m = t.move(0,1,1);
        m = t.move(1,1,2);
        m = t.move(1,0,1);
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */