import java.awt.Color;
import java.awt.Graphics;
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
		for(int j = 0; j < 4; j++)
			for(int i = 0; i < 4; i++){
				if(sp.shape[j][i]==1){												
					int x = localCoordSys.iX(i);
					int y = localCoordSys.iY(j);
					int w = localCoordSys.iX(i+1)-localCoordSys.iX(i);
					int h = localCoordSys.iY(j+1)-localCoordSys.iY(j);
					Square.draw(g, x, y, w, h, ResourceManager.getSingleton().getNextColor());
					//System.out.println("NextShapeArea paint" + w + "  h " + h);
				}											
		}		
	}		
}