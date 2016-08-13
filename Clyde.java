
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Clyde extends Ghost{
	public static final String clyde_img = "pacman-ghosts.png";
	public static final int INIT_X = 170;
	public static final int INIT_Y = 226;

	public Clyde(int courtWidth, int courtHeight, int[][] bounds) {
		super(courtWidth, courtHeight, bounds, INIT_X, INIT_Y, clyde_img);
	}
	
	@Override
	public void draw(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File(clyde_img)), pos_x, pos_y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
