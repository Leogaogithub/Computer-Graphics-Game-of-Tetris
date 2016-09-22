import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class InforBoard extends Bounds{
	String score = "Score:    ";
	String level = "Level:    ";
	String lines = "Lines:    ";
	int iScore = 0;
	int iLevel = 1;
	int iLines = 0;
	InforBoard(){
		this.localCoordSys.rHeight = 6;
		this.localCoordSys.rWidth  = 1;
	}
	
	
	public void paint(Graphics g){
		this.updateLocalCoordSys();
		int x = localCoordSys.iX(0.5f);	
		Rectangle rectangle = this.getBounds();
		int fontSize = rectangle.height/9;			
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));		
		g.setColor(Color.black);			
		g.drawString(level+iLevel, x, localCoordSys.iY(1f));
		g.drawString(lines+iLines, x, localCoordSys.iY(3f));
		g.drawString(score+iScore, x, localCoordSys.iY(5f));
	}
}
