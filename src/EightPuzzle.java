import java.util.*;


/**
 * Created by sstrunjas on 1/25/17.
 */
public class EightPuzzle {
    public class boardObject implements Comparable<boardObject>{
        int[][] currentState;
        int misplaced;
        int blank_x;
        int blank_y;
        String s;

        public boardObject(int[][] inputBoard, int b_misplaced, int b_x, int b_y, String string_representation){
            currentState = inputBoard;
            misplaced = b_misplaced;
            blank_x = b_x;
            blank_y = b_y;
            s = string_representation;

        }

        @Override
        public int compareTo(boardObject o) {
            return Integer.compare(misplaced, o.misplaced);
        }
    }
    public static void solveEightPuzzle(int[][] currentState, int[][] finalState){
        EightPuzzle ep = new EightPuzzle();
        int numInv = numberOfInversions(currentState);
        if(numInv%2 == 1) {
            System.out.println("Number of inversions is odd. This is not a solvable board.");
            return;
        }
        if(numInv == 0){
            System.out.println("The board is already solved.");
            return;

        }


        Set<String> visited = new HashSet();
        int blank_x = -1;
        int blank_y = -1;

        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){
                if(currentState[i][j] == 0){
                    blank_x = i;
                    blank_y = j;
                    break;
                }
            }
        int cost = Integer.MAX_VALUE;
        PriorityQueue<boardObject> q = new PriorityQueue();
        int[] x_moves = {0,0,1,-1};
        int[] y_moves = {1,-1,0,0};
        int level = 0;
        visited.add(mToString(currentState));

        while(cost > 0){
            level++;

            for(int t=0; t<4; t++){
                int next_blank_x = blank_x + x_moves[t];
                int next_blank_y = blank_y + y_moves[t];

                if(next_blank_x < 0 || next_blank_x >= 3)
                    continue;;
                if(next_blank_y < 0 || next_blank_y >= 3)
                    continue;

                int[][] next_board = new int[3][3];
                for(int i=0; i<3; i++)
                    for(int j=0; j<3; j++){
                        if((i == blank_x && j == blank_y) || (i == next_blank_x && j == next_blank_y))
                            continue;
                        next_board[i][j] = currentState[i][j];
                    }

                next_board[blank_x][blank_y] = currentState[next_blank_x][next_blank_y];
                next_board[next_blank_x][next_blank_y] = 0;

                int next_cost = numberOfInversions(next_board);
                String matrixString = mToString(next_board);
                if(!visited.contains(matrixString))
                    q.add(ep.new boardObject(next_board, next_cost, next_blank_x, next_blank_y, matrixString));



            }

            boardObject bestMove = q.poll();
            cost = bestMove.misplaced;
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    currentState[i][j] = bestMove.currentState[i][j];
            blank_x = bestMove.blank_x;
            blank_y = bestMove.blank_y;
            visited.add(bestMove.s);
            q.clear();

            System.out.println("Next chosen state:");
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++)
                    if(currentState[i][j] == 0)
                        System.out.print("  ");
                    else
                        System.out.print(currentState[i][j] + " ");


                System.out.println();
            }



        }



    }
    public static int numberOfInversions(int[][] board){
        int numInversions = 0;
        for(int i = 0; i< 8; i++){
            for(int j=(i+1); j< 9; j++){

                if(board[i/3][i%3] != 0 && board[j/3][j%3] != 0 && board[i/3][i%3] > board[j/3][j%3])
                    numInversions++;
            }


        }
        return numInversions;
    }

    public static String mToString(int[][] matrix){
        StringBuilder s = new StringBuilder();
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                s.append(matrix[i][j]);
        return s.toString();
    }

    public static void main(String[] args){
        int[][] inputMatrix = {{1,8,2}, {0,4,3}, {7,6,5}};
        int[][] finalMatrix = {{1,2,3}, {4,5,6}, {7,8,0}};

        solveEightPuzzle(inputMatrix,finalMatrix);
    }
}
