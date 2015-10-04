package Puzzle8;

import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private Stack<Board> solution;
	private SearchNode finalNode;
	private boolean isSolvable;
	
	private class SearchNode implements Comparable<SearchNode>{
		public Board board;
		public int numMoves;
		public SearchNode previous;
		private int cachedPriority = -1;
		
		SearchNode(Board board, int numMoves, SearchNode previous) {
			this.board = board;
			this.numMoves = numMoves;
			this.previous = previous;
		}
		
		private int priority() {
			if (cachedPriority == -1) {
				cachedPriority = numMoves + board.manhattan();
			}
			return cachedPriority;
		}
		
		
		//make sure the priority queue will use the correct comparison method to comprae two node!!
		@Override
		public int compareTo(SearchNode that) {
			if (this.priority() < that.priority()) {
				return -1;
			} else if (this.priority() > that.priority()) {
				return 1;
			} else {
				return 0;
			}	
		}
	}
	
	public Solver(Board initial) {
		// TODO Auto-generated constructor stub
		MinPQ<SearchNode> origin = new MinPQ<SearchNode>();
		MinPQ<SearchNode> twin = new MinPQ<SearchNode>();
		isSolvable = false;
		
		//create two initial node for origin and twin
		SearchNode initialNode = new SearchNode(initial, 0, null);
		SearchNode twinNode = new SearchNode(initial.twin(), 0, null);
		
		//insert them into different PQ
		origin.insert(initialNode);
		twin.insert(twinNode);
		
		//main loop
		while (!origin.isEmpty()) {
			SearchNode temp = origin.delMin();//in first round-- temp is initial Node!!
			SearchNode twinTemp = twin.delMin();
			
			//check if the temp is the goal and the twin's temp is the goal
			if (twinTemp.board.isGoal()) {
				isSolvable = false;//if twin is the goal--just terminate and set false
				return;
			}
			if (temp.board.isGoal()) {
				storeSolution(temp);//if it is--store the path from bottom to top and terminate
				isSolvable = true;
				return;
			} 
			
			//adding all children into queue for origin and twin respectively
			for (Board b: temp.board.neighbors()) {
				if (temp.previous != null && b.equals(temp.previous)) {
					continue;
				}
				
				//for a child--create a node to represent that child board with b, number of moves + 1 and previous board temp
				SearchNode tempNode = new SearchNode(b, temp.numMoves + 1, temp);
				origin.insert(tempNode);
			}
			for (Board b: twinTemp.board.neighbors()) {
				if (twinTemp.previous != null && b.equals(twinTemp.previous)) {
					continue;
				}
				
				//for a child--create a node to represent that child board with b, number of moves + 1 and previous board temp
				SearchNode tempNode = new SearchNode(b, twinTemp.numMoves + 1, twinTemp);
				twin.insert(tempNode);
			}
		}

	}
	
	private void storeSolution(SearchNode temp) {
		// TODO Auto-generated method stub
		solution = new Stack<Board>();
		finalNode = temp;
		while (temp != null) {
			solution.push(temp.board);
			temp = temp.previous;
		}
	}



	public boolean isSolvable() {
		return isSolvable;
	}
	
	//if solvable return number of moves--if not, return -1
	public int moves() {
		return isSolvable? finalNode.numMoves: -1;
	}
	
	//if solvable return solution stack -- if not, return null
	public Iterable<Board> solution() {
		return isSolvable? solution: null;
	}

	public static void main (String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
	}
	
}
