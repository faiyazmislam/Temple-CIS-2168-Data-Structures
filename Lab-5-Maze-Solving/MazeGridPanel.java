//Faiyaz Islam
//CIS 2168
//Project: Maze
//The maze solver uses a stack to record its positions and move until it reaches the end

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;

	// extra credit
	public void genDFSMaze() {		//will attempt later
		boolean[][] visited;
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	//homework
	public void solveMaze() {
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
		stack.push(start);


		while(!stack.peek().equals(finish) && !stack.isEmpty()){	//loop is set to work while maze is unsolved
			Cell currentPosition = stack.peek();					//the first position is targeted
			currentPosition.setBackground(Color.BLUE);				//sets the current position as blue

			//if it can move up, then it will move and set the position as green
			if(!currentPosition.northWall && !visited(currentPosition.row-1, currentPosition.col)){
				stack.push(maze[currentPosition.row-1][currentPosition.col]);
				currentPosition.setBackground(Color.GREEN);
			}

			//if it can move down, then it will move and set the position as green
			else if(!currentPosition.southWall && !visited(currentPosition.row+1, currentPosition.col)){
				stack.push(maze[currentPosition.row+1][currentPosition.col]);
				currentPosition.setBackground(Color.GREEN);
			}

			//if it can move right, then it will move and set the position as green
			else if(!currentPosition.eastWall && !visited(currentPosition.row, currentPosition.col+1)){
				stack.push(maze[currentPosition.row][currentPosition.col+1]);
				currentPosition.setBackground(Color.GREEN);
			}

			//if it can move left, then it will move and set the position as green
			else if(!currentPosition.westWall && !visited(currentPosition.row, currentPosition.col-1)){
				stack.push(maze[currentPosition.row][currentPosition.col-1]);
				currentPosition.setBackground(Color.GREEN);
			}

			//if it cannot move anywhere else, then the stack will pop and the entire path will be made blue
			//the stack will go back to the last place that it could move
			else{
				stack.pop();
			}
		}
	}




	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  ) {
			return false;
		}


		return true;


	}


	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {

				if(row == 0 && col ==0) {
					continue;
				}

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}


		this.genNWMaze();
		this.solveMaze();
		
	}




}
