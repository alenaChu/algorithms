package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Chuikina Alena on 15.11.15.
 *
 */
public class Board {
    //private final int [][] tiles;
    private final char [] tiles;             // 1D-array from 0 to N^2 -1
    private final int N;                   // Grid-size
    private int hamming;                   // number of blocks out of place
    private int manhattan;                 // sum of Manhattan distances between blocks and goal

    public Board(int[][] blocks) {         // construct a board from an N-by-N array of blocks
        N = blocks.length;
        tiles = new char [N*N];

        for (int i =0; i<N; i++){
            for (int j =0; j< N; j++){
                tiles[i*N+j] = (char)blocks[i][j];
            }
        }
    }
    public Board(char[] blocks, int size) {         // construct a board from an N-by-N array of blocks
        N = size;
        tiles = Arrays.copyOf(blocks,N*N);
    }

    public int dimension() {  return N; }             // board dimension N

   /**
    *@return number of blocks out of place
    */
    public int hamming() {               //
        int count = 0;
        if (hamming > 0) return hamming;
        for (int i=0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i*N+j] != 0 && tiles[i*N+j] != i*N+j+1) count++;
            }
        }
        hamming = count;
        return count;
    }

    /**
     *@return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {              // sum of Manhattan distances between blocks and goal
        if (manhattan > 0) return manhattan;
        int count =0;
        int diff;
        for (int i=0; i < N; i++){
            for (int j=0; j < N;j++){
                if (tiles[i*N+j] != 0 && tiles[i*N+j] != i*N+j+1) {
                    diff = Math.abs(tiles[i*N+j] - i*N-j-1);
                    count += diff/N + diff%N;
                }
            }
        }
        manhattan = count;
        return count;
    }

    /**
     *@return true or false depending on whether this board is goal
     */
    public boolean isGoal() {
        for (int i=0; i < N*N; i++){
            if (tiles[i] != 0 && tiles[i] != i+1) return false;
        }
        return true;
    }

    /**
     *@return a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        char [] twinBlocks = new char[N*N];
        int space = 0;
        for (int i= 0; i< N*N; i++){
            twinBlocks[i] = tiles[i];
            if (twinBlocks[i]== 0) space = i;
        }

    //    exchange 2 first blocks or 2 last blocks
        if (space != 0 && space!= 1){
            twinBlocks = exch(0,1,twinBlocks);
        } else {
            twinBlocks = exch(N*N-1, N*N-2, twinBlocks);
        }

        return new Board(twinBlocks, N);
    }

    private char[] exch(int index1, int index2, char[] array){
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return array;
    }

    /** override equals function
     *@return does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board b = (Board) y;
        if (N !=b.N) return false;
        for (int i=0; i<(N*N); i++){
            if (tiles[i] != b.tiles[i]) return false;
        }
        return true;
    }

    public Iterable<Board> neighbors() {    // all neighboring boards
        char [] tempArray = new char [N*N];
        ArrayList<Board> neighbor = new ArrayList<Board>(4);

        int spaceRow =0;
        int spaceCol =0;
        for (int i=0; i<N*N; i++) {
            tempArray[i] = tiles [i];
            if (tiles[i] == 0){
                spaceRow = i/N;
                spaceCol = i%N;
            }
        }

        // shift the tile from the top
        if (spaceRow > 0) {
            tempArray = exch(spaceRow*N+spaceCol, (spaceRow-1)*N+spaceCol, tempArray);
            Board b = new Board(tempArray,N);
            neighbor.add(b);
            tempArray = exch((spaceRow-1)*N+spaceCol, spaceRow*N+spaceCol, tempArray);
        }
        // shift the tile from the bottom
        if (spaceRow < N-1) {
            tempArray = exch(spaceRow*N+spaceCol, (spaceRow+1)*N+spaceCol, tempArray);
            Board b = new Board(tempArray,N);
            neighbor.add(b);
            tempArray = exch((spaceRow+1)*N+spaceCol, spaceRow*N+spaceCol, tempArray);
        }
        // shift the tile from the right
        if (spaceCol < N-1) {
            tempArray = exch(spaceRow*N+spaceCol, spaceRow*N+spaceCol+1, tempArray);
            Board b = new Board(tempArray,N);
            neighbor.add(b);
            tempArray = exch(spaceRow*N+spaceCol+1, spaceRow*N+spaceCol, tempArray);
        }
        // shift the tile from the left
        if (spaceCol > 0) {
            tempArray = exch(spaceRow*N+spaceCol, spaceRow*N+spaceCol-1, tempArray);
            Board b = new Board(tempArray,N);
            neighbor.add(b);
        }

        return neighbor;
    }

    public String toString() {              // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
    //    s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int) tiles[i*N+j]));
            }
            s.append("\n");
        }
        return s.toString();
    }


    public static void main(String[] args){ // unit tests (not graded)
    }
}
