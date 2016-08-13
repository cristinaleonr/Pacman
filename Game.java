/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	final GameCourt court;
	final JPanel status_panel;
	final JLabel status;
	
	public Game() {
		status_panel = new JPanel();
		status  = new JLabel("Running...");
		court = new GameCourt(status);
	}
	
	
	public void run() {
		// NOTE : recall that the 'final' keyword notes inmutability
		// even for local variables.

		// Top-level frame in which game components live
		final JFrame frame = new JFrame("PAC-MAN");
		frame.setLocation(300, 300);

		// show instructions
		JOptionPane.showMessageDialog(frame, "Introducing the One, the Only...Pac-Man!" + "\n"
				+ "Will he make it out alive and be able to live happily " + "\n"
				+ "ever after? (And consume a more balanced diet?)" + "\n"
				+ "The object of the game is to help Pac-Man gobble his way through" + 
				"\n" + "the maze level. He must eat all the dots to win. That can be pretty"
				+ "\n" + "tough, because at the same time he must dodge mean old ghosts!"
				+ "\n" + "Use the arrow keys to move around the maze (up to go up, down to "+ "\n"
				+ "go down, left to go left, and right to go right)" + "\n"
				+ "To start over click reset.");
		// Status panel
		
		frame.add(status_panel, BorderLayout.SOUTH);
		
		status_panel.add(status);

		// Main playing area
		
		frame.add(court, BorderLayout.CENTER);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(reset);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
