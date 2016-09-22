import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

class ControlArea extends Bounds{
	static int edgeSize=20;
	NextShapeArea na = null;
	MyButton quitButton = null;
	InforBoard inforBoard = null;
	ControlArea(){			
		localCoordSys.rHeight = 20;
		localCoordSys.rWidth = 10;		
		na = new NextShapeArea();		
		quitButton = new MyButton("QUIT", Color.black);
		quitButton.setVisible(true);
		inforBoard = new InforBoard();
	}	
	
	public void paint(Graphics g){
		this.updateLocalCoordSys();
		g.setColor(Color.black);		
		Rectangle rectangle = getBounds();		
		//g.drawRect(rectangle.x,rectangle.y,rectangle.width-1,rectangle.height-1);		
		
		int size = localCoordSys.iLength(1);		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(1f), size*5, size*5);
		//System.out.println("ControlArea NextShapeArea size" + size);
		na.setBounds(rectangle);		
		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(8), size*6, size*7);
		inforBoard.setBounds(rectangle);
		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(16), size*6, size*2);		
		quitButton.setBounds(rectangle);
		
		na.paint(g);		
		quitButton.paint(g);
		inforBoard.paint(g);
				
	}	
}