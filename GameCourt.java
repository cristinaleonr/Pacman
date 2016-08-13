/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	
	private Clyde clyde;
	private Blinky blinky;
	private Pinky pinky;
	private Inky inky;
	private Pacman pacman;
	private Maze maze;
	public int[][] bounds = new int[COURT_WIDTH][COURT_HEIGHT];
	public static final int COURT_WIDTH = 400;
	public static final int COURT_HEIGHT = 600;
	public static final int PACMAN_VELOCITY = 4;
	public static final int INTERVAL = 35;
	public boolean playing = false; // whether the game is running
	private JLabel status;
	
	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tick();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		timer.start(); 
		
		setFocusable(true);
		
	addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
				pacman.v_x = -PACMAN_VELOCITY;
			    pacman.v_y = 0;
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				pacman.v_x = PACMAN_VELOCITY;
				pacman.v_y = 0;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				pacman.v_y = PACMAN_VELOCITY;
				pacman.v_x = 0;
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				pacman.v_y = -PACMAN_VELOCITY;
				pacman.v_x = 0;
			}
		}
	});
	this.status = status;
	}
	

	void tick() throws IOException {
		if (playing) {
			repaint();
			
			// move all the people
			pacman.move();
			clyde.move();
			blinky.move();
			pinky.move();
			inky.move();

			// make them bounce when they hit a wall
			pacman.bounce(pacman.hitWall());
			clyde.bounce(clyde.hitWall());
			blinky.bounce(blinky.hitWall());
			pinky.bounce(pinky.hitWall());
			inky.bounce(inky.hitWall());
			
			// make ghosts bounce when they hit each other
			clyde.bounce(clyde.hitObj(blinky));
			clyde.bounce(clyde.hitObj(pinky));
			clyde.bounce(clyde.hitObj(inky));
			blinky.bounce(clyde.hitObj(clyde));
			blinky.bounce(clyde.hitObj(pinky));
			blinky.bounce(clyde.hitObj(inky));
			pinky.bounce(clyde.hitObj(clyde));
			pinky.bounce(clyde.hitObj(blinky));
			pinky.bounce(clyde.hitObj(inky));
			
			pacman.eat(maze);

			if (clyde.intersects(pacman) || blinky.intersects(pacman) || pinky.intersects(pacman)
					|| inky.intersects(pacman)) {
				playing = false;
				status.setText("You lose!");
			}
			
			else if (maze.numFood == 0) {
				playing = false;
				status.setText("You win!");
			}
			
			// update the display
			repaint();
		}
	}
	
	public void reset() {

		try {
			maze = new Maze();
			bounds = maze.returnMaze();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// initialize the people
		pacman = new Pacman(COURT_WIDTH, COURT_HEIGHT, bounds);
		clyde = new Clyde(COURT_WIDTH, COURT_HEIGHT, bounds);
		blinky = new Blinky(COURT_WIDTH, COURT_HEIGHT, bounds);
		pinky = new Pinky(COURT_WIDTH, COURT_HEIGHT, bounds);
		inky = new Inky(COURT_WIDTH, COURT_HEIGHT, bounds);
		
		playing = true;
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		maze.draw(g);
		pacman.draw(g);
		clyde.draw(g);
		blinky.draw(g);
		pinky.draw(g);
		inky.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
