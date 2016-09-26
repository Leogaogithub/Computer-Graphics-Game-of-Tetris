package leo;
import java.awt.Rectangle;

public class Bounds {
	DeviceCoordVsLeftTopLogic localCoordSys = new DeviceCoordVsLeftTopLogic();
	Rectangle rectangle;
	boolean visible = true;
	Bounds(){
		rectangle = new Rectangle();
		visible = true;
	}
	void setBounds(Rectangle rectangle){
		this.rectangle = (Rectangle) rectangle.clone();
	}
	
	void setBounds(int x, int y, int width, int height){
		rectangle.x = x;
		rectangle.y = y;
		rectangle.width = width;
		rectangle.height = height;
	}
	Rectangle  getBounds(){
		return (Rectangle) rectangle.clone();
	}
	
	boolean isVisible(){
		return visible;				
	}
	
	void setVisible(boolean visible){
		this.visible = visible;
	}
	
	void updateLocalCoordSys(){
		localCoordSys.update(rectangle);
	}	
}
