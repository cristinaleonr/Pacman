/*

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer; 

@SuppressWarnings("serial")
public class Court extends JPanel {

	public Ghost ghost;
	public Pacman pacman;
	public Maze maze;
	public static final int COURT_WIDTH = 400;
	public static final int COURT_HEIGHT = 600;
	public static final int PACMAN_VELOCITY = 4;
	public static final int INTERVAL = 35;
	public boolean playing = false; // whether the game is running
	private JLabel status;
	
	public Court(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.black);
		
		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); 
		
		setFocusable(true);
		
		
		
	addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				pacman.v_x = -PACMAN_VELOCITY;
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				pacman.v_x = PACMAN_VELOCITY;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				pacman.v_y = PACMAN_VELOCITY;
			else if (e.getKeyCode() == KeyEvent.VK_UP)
				pacman.v_y = -PACMAN_VELOCITY;
		}

		public void keyReleased(KeyEvent e) {
			pacman.v_x = 0;
			pacman.v_y = 0;
		}
	});
	this.status = status;
	}

	void tick() {
		if (playing) {
			// advance the square and snitch in their
			// current direction.
			pacman.move();

			// make the snitch bounce off walls...
			//pacman.bounce(pacman.hitWall());
			// ...and the mushroom
			
			//pacman.bounce(pacman.hitObj(poison));

			/*
			// check for the game end conditions
			if (pacman.intersects(ghost)) {
				playing = false;
				status.setText("You lose!");

			//} else if (pacman.intersects(ghost)) {
				//playing = false;
				//status.setText("You win!");
			}
			
			// update the display
			repaint();
		}
	}
	
	/*
	
	public void reset() {

		pacman = new Pacman(COURT_WIDTH, COURT_HEIGHT);
		ghost = new Ghost(COURT_WIDTH, COURT_HEIGHT);
		try {
			maze = new Maze();
		} catch (IOException e) {
			e.printStackTrace();
		}

		playing = true;
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		pacman.draw(g);
		ghost.draw(g);
		maze.draw(g);
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
	
}
*/
