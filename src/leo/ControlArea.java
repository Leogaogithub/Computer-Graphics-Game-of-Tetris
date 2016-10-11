package leo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class ControlArea extends Bounds{
	static int edgeSize=20;
	NextShapeArea na = null;
	
	InforBoard inforBoard = null;
	MyButton startButton = null;
	MyButton configButton = null;
	MyButton quitButton = null;
	
	ControlArea(){			
		localCoordSys.rHeight = 20;
		localCoordSys.rWidth = 10;		
		na = new NextShapeArea();		
		quitButton = new MyButton("QUIT", Color.red);
		quitButton.setVisible(true);
		startButton = new MyButton("START", Color.red);
		startButton.setVisible(true);
		configButton = new MyButton("CONFIGURE", Color.red);
		configButton.setVisible(true);
		inforBoard = new InforBoard();
	}	
	
	public void paint(Graphics g){
		this.updateLocalCoordSys();
		g.setColor(Color.black);		
		Rectangle rectangle = getBounds();		
		//g.drawRect(rectangle.x,rectangle.y,rectangle.width-1,rectangle.height-1);		
		
		int size = localCoordSys.iLength(1);		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(1f), size*4, size*4);		
		na.setBounds(rectangle);		
		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(6), size*6, size*7);
		inforBoard.setBounds(rectangle);
		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(13), size*6, size*3/2);		
		configButton.setBounds(rectangle);
		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(15), size*6, size*3/2);		
		startButton.setBounds(rectangle);
		
		rectangle.setBounds(localCoordSys.iX(2), localCoordSys.iY(17), size*6, size*3/2);		
		quitButton.setBounds(rectangle);
		
		na.paint(g);		
		quitButton.paint(g);
		startButton.paint(g);
		configButton.paint(g);
		inforBoard.paint(g);
				
	}	
}