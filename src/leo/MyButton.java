package leo;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton extends Bounds{
	String label = "";
	Color color = null;		
	
	MyButton(String label, Color color){
		this.label = label;
		this.color = color;		
	}	
	
	public void paint(Graphics g){			
		if(this.isVisible()){	
			this.updateLocalCoordSys();
			Rectangle rectangle = this.getBounds();
			int fontSize = rectangle.height/2;			
			g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
			g.setColor(color);
			g.drawRect(rectangle.x,rectangle.y,rectangle.width, rectangle.height);
			//g.setColor(Color.yellow);
			//g.fillRect(rectangle.x,rectangle.y,rectangle.width, rectangle.height);			
			FontMetrics fm = g.getFontMetrics();
			int stringWidth = fm.stringWidth(label);
			int fontHeight = fm.getMaxAscent() + fm.getMaxDescent();
			g.drawString(label, rectangle.x+(rectangle.width - stringWidth)/2, rectangle.y+(rectangle.height+fontHeight)/2);
		}		
	}
}
