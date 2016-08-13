

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
	public int[][] maze;
	static int width = 400;
	static int height = 600;
    String readLine;
    BufferedReader read;
    public int numFood;

	public Maze() throws IOException {
		
		try {
			if (read == null) {
			    read = new BufferedReader(new FileReader(("Maze.txt")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        maze = new int[width][height];
        for (int y = 0; y < height; y ++) {
	        for(int x = 0; x < width; x++) {
				maze[x][y] = read.read() - 48;
	        }
        }
	}
	
	public void draw(Graphics g) {
		numFood = 0;
		for (int y = 0; y < height; y ++) {
	        for(int x = 0; x < width; x++) {
	        	if (maze[x][y] == 2) {
	        		numFood++;
	        		g.setColor(Color.yellow);
	        		g.fillOval(x, y, 10, 10);
	        	}
	        	else if (maze[x][y] == 0) {
	        		g.setColor(Color.black);
	        		g.fillRect(x, y, 1, 1);
	        	}
	        	else {
	        		g.setColor(Color.white);
	        		g.fillRect(x, y, 1, 1);
	        	}
	        	
	        }
		}
	}
	
	public int[][] returnMaze() {
		return this.maze;
	}
}

