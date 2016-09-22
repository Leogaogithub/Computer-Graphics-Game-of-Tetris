import java.awt.Color;
import java.awt.Graphics;


public class Square {
	public static void draw(Graphics g, int x, int y, int width, int height, Color color){		
		g.setColor(Color.black);
		g.drawRect(x,y,width, height);
		g.setColor(color);
		g.fillRect(x+1,y+1,width-1, height-1);			
	}
}
