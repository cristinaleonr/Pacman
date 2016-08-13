import java.awt.BorderLayout;
import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Before;

public class MyTests {

	// Main playing area
	private Game game;
	private GameCourt court;
	private Pacman pacman;
	private Maze maze;
	private Ghost clyde;

    @Before
    public void setUp() {
        game = new Game();
        court = game.court;
        pacman = new Pacman(400, 600, court.bounds);
        try {
			maze = new Maze();
		} catch (IOException e) {
			e.printStackTrace();
		}
        clyde = new Clyde(400, 600, court.bounds);
    }

    // pacman eats all the food
    @Test
    public void noMoreFood() {
    	int counter = maze.numFood;
    	while (counter > 0) {
    		pacman.eat(maze);
    	}
    	assertEquals("game ends when pacman eats everything", court.playing, false);
    }
    
    @Test
    public void endsWhenFoodEmptyisZero() {
    	maze.numFood = 0;
    	assertEquals("status not playing when food is 0", court.playing, false);
    }
    
    // game state is playing at the beginning
    @Test
    public void playingAtBeginning() {
    	court.reset();
    	assertEquals("playing true", court.playing, true);
    }
    
    // top collision
    @Test
    public void tryOutOfBounds() {
    	court.reset();
    	pacman.v_y = -10;
    	pacman.pos_x = 0;
    	pacman.pos_y = 0;
    	assertEquals("hit wall up", pacman.hitWall(), Direction.UP);
    }
    
 // down collision
    @Test
    public void tryOutOfBoundsDown() {
    	court.reset();
    	pacman.v_y = 10;
    	pacman.pos_x = 10;
    	pacman.pos_y = 600;
    	assertEquals("hit wall down", pacman.hitWall(), Direction.DOWN);
    }
    
 // right collision
    @Test
    public void tryOutOfBoundsRight() {
    	court.reset();
    	pacman.v_x = 10;
    	pacman.pos_x = 400;
    	pacman.pos_y = 10;
    	assertEquals("hit wall down", pacman.hitWall(), Direction.RIGHT);
    }
    
    // left collision
    @Test
    public void tryOutOfBoundsLEft() {
    	court.reset();
    	pacman.v_x = -10;
    	pacman.pos_x = 0;
    	pacman.pos_y = 10;
    	assertEquals("hit wall down", pacman.hitWall(), Direction.LEFT);
    }
	
    
	
}