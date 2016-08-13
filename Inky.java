import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Inky extends Ghost{

	public static final String inky_img = "pacman-ghosts4.png";
	public static final int INIT_X = 170 + 45;
	public static final int INIT_Y = 226 + 24;

	public Inky(int courtWidth, int courtHeight, int[][] bounds) {
		super(courtWidth, courtHeight, bounds, INIT_X, INIT_Y, inky_img);
	}
	
	@Override
	public void draw(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File(inky_img)), pos_x, pos_y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
