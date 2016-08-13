import java.awt.Color;
import java.awt.Graphics;

public class Pacman extends Person {

	public static final int SIZE = 13;
	public static final int INIT_X = 190;
	public static final int INIT_Y = 196;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public Pacman(int courtWidth, int courtHeight, int[][] bounds) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, courtWidth,
		    courtHeight, bounds);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillArc(pos_x,pos_y,SIZE,SIZE,30,300);
	}
	
	public void eat(Maze maze) {
		for (int i = pos_y; i < pos_y + height; i++) {
			for (int j = pos_x; j < pos_x + width; j ++) {
				if (maze.maze[j][i] == 2) {
					maze.numFood--;
					maze.maze[j][i] = 0;
				}
			}
		}
	}

}
