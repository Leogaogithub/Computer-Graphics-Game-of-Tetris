package leo;
import java.awt.Rectangle;
public class DeviceCoordVsLeftTopLogic {
	float pixelSize = 0;
	float rWidth = 5;
	float rHeight = 5;	
	int originX = 0;
	int originY = 0;
	int width = 0;
	int height =  0; 	
	
	DeviceCoordVsLeftTopLogic(){
		rWidth = 5;
		rHeight = 5;
		originX = 0;
		originY = 0;		
		width = 0;
		height =  0;
	}
	
	void update(Rectangle bound){		
		width = bound.width - 1; 
		height = bound.height - 1;
		originX = bound.x;
		originY = bound.y;		
		pixelSize = Math.max(rWidth/width, rHeight/height);			
	}	
	
	 int iLength(float len){
		return Math.round(len/pixelSize);
	}
	
	 int iX(float x){
		return originX + Math.round(x/pixelSize);
	}
	
	 int iY(float y){
		return originY + Math.round(y/pixelSize);
	}
	
	 float fx(int X){
		return (X-originX)*pixelSize;
	}
	
	 float fy(int Y){
		return (Y-originY)*pixelSize;
	}
}
