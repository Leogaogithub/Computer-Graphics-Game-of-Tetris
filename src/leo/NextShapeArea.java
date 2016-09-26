package leo;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class NextShapeArea extends Bounds{	
	NextShapeArea(){
		localCoordSys.rWidth = 4;
		localCoordSys.rHeight = 4;
	}
	
	public void paint(Graphics g){
		this.updateLocalCoordSys();				
		Rectangle rectangle = this.getBounds();
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);		
		Shape sp = ResourceManager.getSingleton().getNextShape();
		
		for(Point point : sp.points){
			int x = localCoordSys.iX(point.x);
			int y = localCoordSys.iY(point.y);
			int w = localCoordSys.iX(point.x+1)-x;
			int h = localCoordSys.iY(point.y+1)-y;
			Square.draw(g, x, y, w, h, ResourceManager.getSingleton().getNextColor());
		}			
	}		
}