package puzzle8;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by loster on 15.11.15.
 */
public class Solver {

    private class Node implements Comparable <Node>{
        private Node previous;
        private Board board;
        private int movesNum;
        private boolean isTwin;

        public Node(Board b){
            board = b;
            movesNum = 0;
            previous = null;
        }
        public Node(Board b, int moves, Node prev, boolean twin){
            board = b;
            movesNum = moves;
            previous = prev;
            isTwin = twin;
        }

        private boolean isTwin(){ return isTwin;}
        private int movesNum(){ return movesNum;}

        public int compareTo(Node n){
            if (board.manhattan() + movesNum > n.board.manhattan() + n.movesNum) return 1;
            if (board.manhattan() + movesNum < n.board.manhattan() + n.movesNum) return -1;
            if (isTwin && ! n.isTwin) return 1;
            if (! isTwin && n.isTwin) return -1;
            if (board.hamming() + movesNum > n.board.hamming() + n.movesNum) return 1;
            if (board.hamming() + movesNum < n.board.hamming() + n.movesNum) return -1;
            return 0;
        }
    }

    private boolean isSolvable;
    private int moves;
    private Node node;

    public Solver(Board initial) {         // find a solution to the initial board (using the A* algorithm)
        // initial state
        node = new Node(initial);
        Node twin = new Node(node.board.twin(),0,null,true); //twin board for checking whether the game is solvable

        // create a priority queue - ordinary and twin
        MinPQ<Node> pq = new MinPQ<>();
        pq.insert(node);
        pq.insert(twin);

    //   A* Algorithm
        while (!node.board.isGoal()) {
        //  first operate with ordinary board, and then with twin board
            node = pq.delMin();
      //      StdOut.println("pq.min, twin = " + node.isTwin() + ", manh = " + node.board.manhattan());
        //    StdOut.println(node.board.toString());
            for (Board temp : node.board.neighbors()) {
                Node neighbor = new Node(temp, node.movesNum() + 1, node, node.isTwin());
                if (node.previous == null || !neighbor.board.equals(node.previous.board)) {
                    pq.insert(neighbor);
      //              StdOut.println("neighbor insert, manh = " + neighbor.board.manhattan());
        //            StdOut.println(neighbor.board.toString());
                }
            }
        }
        if (node.board.isGoal()) {
            if (node.isTwin()) {
                isSolvable = false;
                moves = -1;
            }
            else {
               isSolvable = true;
                moves = node.movesNum();
            }
        }
    }

    public boolean isSolvable() {           // is the initial board solvable?
        return isSolvable;
    }

    public int moves() {  return moves; } // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
        Stack<Board> stack = new Stack<>();

        if (isSolvable){
            while(node != null){
                stack.push(node.board);
                node = node.previous;
            }
            return stack;
        }
        return null;
    }

    public static void main(String[] args){ // solve a slider puzzle (given below)
    }
}
