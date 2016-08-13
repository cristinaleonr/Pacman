
import java.io.IOException;

public class Ghost extends Person {
	
	public static final int SIZE = 18;
	public static final int INIT_VEL_X = 3;
	public static final int INIT_VEL_Y = 3;
	public static int INIT_X;
	public static int INIT_Y;
	public static String img;
	public int[][] bounds;

	public Ghost(int courtWidth, int courtHeight, int[][] bounds, int INIT_X, 
			int INIT_Y, String img) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, courtWidth,
				courtHeight, bounds);
		Ghost.INIT_X = INIT_X;
		Ghost.INIT_Y = INIT_Y;
		Ghost.img = img;
		this.bounds  = bounds;
	}

	@Override
	public void move() throws IOException {
		Direction direction = hitWall();

		int random = 1 + (int) (Math.random() * 2);
		
		if (direction == null) {
		    pos_x += v_x;
		    pos_y += v_y;
		}
		
		if (hitWall() == Direction.RIGHT || hitWall() == Direction.LEFT) {
			if (random == 1) {
				v_y = -8;
				v_x = 0;
				
			}
			else {
				v_x = 0;
				v_y = 8;
				
			}
		}
		else if (hitWall() == Direction.UP || hitWall() == Direction.DOWN) {
			if (random == 1) {
				v_x = 8;
				v_y = 0;
			}
			else {
				v_x = -8;
				v_y = 0;
			}
		}
	clip();
    }
}



