package leo;
import java.awt.Dimension;
import java.awt.Point;


public class DeviceCoordVsCenterLogic {
	float pixelSize = 0;
	float rWidth = 10;
	float rHeight = 10;
	float centerX = 0;
	float centerY = 0;
	Point leftUpPoint = new Point();
	int   iWidth = 0;
	int   iHeight = 0;
	void update(Dimension d){
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth/maxX, rHeight/maxY);		
		centerX = maxX/2;
		centerY = maxY/2;
		leftUpPoint.x = iX(-rWidth/2);
		leftUpPoint.y = iY(rHeight/2);
		iWidth = iLength(rWidth);
		iHeight = iLength(rHeight);		
	}
	
	int iLength(float len){
		return Math.round(len/pixelSize);
	}
	
	int iX(float x){
		return Math.round(centerX+x/pixelSize);
	}
	
	int iY(float y){
		return Math.round(centerY-y/pixelSize);
	}
	
	float fx(int X){
		return (X-centerX)*pixelSize;
	}
	
	float fy(int Y){
		return (centerY - Y)*pixelSize;
	}
}
