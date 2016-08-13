import java.awt.Graphics;
import java.io.IOException;

public class Person {

	/** Current position of the object (in terms of graphics coordinates)
	 *  
	 * Coordinates are given by the upper-left hand corner of the object.
	 * This position should always be within bounds.
	 *  0 <= pos_x <= max_x 
	 *  0 <= pos_y <= max_y 
	 */
	public int pos_x; 
	public int pos_y;

	/** Size of object, in pixels */
	public int width;
	public int height;
	
	/** Velocity: number of pixels to move every time move() is called */
	public int v_x;
	public int v_y;

	/** Upper bounds of the area in which the object can be positioned.  
	 *    Maximum permissible x, y positions for the upper-left 
	 *    hand corner of the object
	 */
	public int max_x;
	public int max_y;
	
	public int[][] bounds;

	/**
	 * Constructor
	 */
	public Person(int v_x, int v_y, int pos_x, int pos_y, 
		int width, int height, int court_width, int court_height, int[][] bounds){
		this.v_x = v_x;
		this.v_y = v_y;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.width = width;
		this.height = height;
		
		// take the width and height into account when setting the 
		// bounds for the upper left corner of the object.
		this.max_x = court_width - width;
		this.max_y = court_height - height;
		this.bounds = bounds;

	}


	/**
	 * Moves the object by its velocity.  Ensures that the object does
	 * not go outside its bounds by clipping.
	 * @throws IOException 
	 */
	public void move() throws IOException{
		Direction direction = hitWall();
		if (direction == null) {
		    pos_x += v_x;
		    pos_y += v_y;
		}
	    else if(hitWall() == Direction.LEFT) {
			bounce(Direction.LEFT);
		}
		else if(hitWall() == Direction.RIGHT) {
			 bounce(Direction.RIGHT);
		}
		else if(hitWall() == Direction.UP) {
			bounce(Direction.UP);
		}
		else {
			bounce(Direction.DOWN);
		}
		clip();
	}

	/**
	 * Prevents the object from going outside of the bounds of the area 
	 * designated for the object. (i.e. Object cannot go outside of the 
	 * active area the user defines for it).
	 * @throws IOException 
	 */ 
	public void clip() throws IOException{
		if (pos_x < 0) pos_x = 0;
		else if (pos_x > max_x) pos_x = max_x;
		
		if (pos_y < 0) pos_y = 0;
		else if (pos_y > max_y) pos_y = max_y;
	}
	
	public boolean checkWalls() {
		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				if (bounds[pos_x + i][pos_y + j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Determine whether this game object is currently intersecting
	 * another object.
	 * 
	 * Intersection is determined by comparing bounding boxes. If the 
	 * bounding boxes overlap, then an intersection is considered to occur.
	 * 
	 * @param obj : other object
	 * @return whether this object intersects the other object.
	 */
	public boolean intersects(Person person){
		return (pos_x + width >= person.pos_x
				&& pos_y + height >= person.pos_y
				&& person.pos_x + person.width >= pos_x 
				&& person.pos_y + person.height >= pos_y);
	}

	
	/**
	 * Determine whether this game object will intersect another in the
	 * next time step, assuming that both objects continue with their 
	 * current velocity.
	 * 
	 * Intersection is determined by comparing bounding boxes. If the 
	 * bounding boxes (for the next time step) overlap, then an 
	 * intersection is considered to occur.
	 * 
	 * @param obj : other object
	 * @return whether an intersection will occur.
	 */
	public boolean willIntersect(Person person){
		int next_x = pos_x + v_x;
		int next_y = pos_y + v_y;
		int next_obj_x = person.pos_x + person.v_x;
		int next_obj_y = person.pos_y + person.v_y;
		return (next_x + width >= next_obj_x
				&& next_y + height >= next_obj_y
				&& next_obj_x + person.width >= next_x 
				&& next_obj_y + person.height >= next_y);
	}

	
	/** Update the velocity of the object in response to hitting
	 *  an obstacle in the given direction. If the direction is
	 *  null, this method has no effect on the object. */
	public void bounce(Direction d) {
		if (d == null) return;
		switch (d) {
		case UP:    v_y = Math.abs(v_y); break;  
		case DOWN:  v_y = -Math.abs(v_y); break;
		case LEFT:  v_x = Math.abs(v_x); break;
		case RIGHT: v_x = -Math.abs(v_x); break;
		}
	}
	
	/** Determine whether the game object will hit a 
	 *  wall in the next time step. If so, return the direction
	 *  of the wall in relation to this game object.
	 *  
	 * @return direction of impending wall, null if all clear.
	 */
	public Direction hitWall() {
		if (pos_x + v_x < 0) 
			return Direction.LEFT; 
		else if (pos_x + v_x > max_x)
			return Direction.RIGHT;
		if (pos_y + v_y < 0)
			return Direction.UP;
		else if (pos_y + v_y > max_y)
			return Direction.DOWN;
		
		for (int i = 0; i < height; i++) { 
			for (int j = pos_x + v_x; j < pos_x; j++) {
	            if (bounds[j][pos_y + i] == 1 && v_x < 0) return Direction.LEFT;
			}
		}
		for (int i = 0; i < height; i++) { 
			for (int j = pos_x + width; j < pos_x + v_x + width; j++) {
	            if (bounds[j][pos_y + i] == 1 && v_x > 0) return Direction.RIGHT;
			}
		}
		for (int i = 0; i < width; i++) { 
			for (int j = pos_y + v_y; j < pos_y; j++) {
	            if (bounds[pos_x + i][j] == 1 && v_y < 0) return Direction.UP;
			}
		}
		for (int i = 0; i < width; i++) { 
			for (int j = pos_y + height; j < pos_y + height + v_y; j++) {
	            if (bounds[pos_x + i][j] == 1 && v_y > 0) return Direction.DOWN;
			}
		}
		return null;
	}

	/** Determine whether the game object will hit another 
	 *  object in the next time step. If so, return the direction
	 *  of the other object in relation to this game object.
	 *  
	 * @return direction of impending object, null if all clear.
	 */
	public Direction hitObj(Person person) {

		if (this.willIntersect(person)) {
			double dx = person.pos_x + person.width /2 - (pos_x + width /2);
			double dy = person.pos_y + person.height/2 - (pos_y + height/2);

			double theta = Math.acos(dx / (Math.sqrt(dx * dx + dy *dy)));
			double diagTheta = Math.atan2(height / 2, width / 2);

   if (theta <= diagTheta ) {
     return Direction.RIGHT;
   } else if ( theta > diagTheta && theta <= Math.PI - diagTheta ) {
     if ( dy > 0 ) {
       // Coordinate system for GUIs is switched
       return Direction.DOWN;
     } else {
       return Direction.UP;
     }
   } else {
     return Direction.LEFT;
   }
		} else {
			return null;
		}

	}
	
	/**
	 * Default draw method that provides how the object should be drawn 
	 * in the GUI. This method does not draw anything. Subclass should 
	 * override this method based on how their object should appear.
	 * 
	 * @param g 
	 *	The <code>Graphics</code> context used for drawing the object.
	 * 	Remember graphics contexts that we used in OCaml, it gives the 
	 *  context in which the object should be drawn (a canvas, a frame, 
	 *  etc.)
	 */
	public void draw(Graphics g) {
	}
	
}
