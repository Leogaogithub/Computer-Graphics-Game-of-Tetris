package leo;

import java.awt.Point;
import java.util.LinkedList;

public class GameCore {
	Point curPosition ;
	int[][] board = null;
	int[][] colors = null;
	int ticks = 0;
	int maxX = 9;
	int maxY = 19;
	
	LinkedList<TouchBottomListener> listeners = new LinkedList<>();
	
	GameCore(int[][] board, int[][] colors){
		curPosition = new Point(4,0);	
		this.board = board;
		this.colors = colors;
		maxX = board[0].length - 1;
		maxY = board.length - 1;
	}
	
	void addTouchBottomListener(TouchBottomListener listener){
		listeners.add(listener);
	}
	
	void clockwiseRotate(){
		int shapeId = ResourceManager.getSingleton().clockwiseRotateCurShape();
		Shape shape = ResourceManager.getSingleton().getShape(shapeId);
		if(isValidate(curPosition.x, curPosition.y, shape)){
			ResourceManager.getSingleton().curShapeId = shapeId;
		}		
	}
	
	void counterClockwiseRotate(){
		int shapeId = ResourceManager.getSingleton().counterClockwiseRotateCurShape();
		Shape shape = ResourceManager.getSingleton().getShape(shapeId);
		if(isValidate(curPosition.x, curPosition.y, shape)){
			ResourceManager.getSingleton().curShapeId = shapeId;
		}
	}
	
	void goLeft(){
		Shape shape = ResourceManager.getSingleton().getCurShape();
		if(isValidate(curPosition.x-1, curPosition.y, shape)){
			curPosition.x--;
		}
	}
	
	void goRight(){
		Shape shape = ResourceManager.getSingleton().getCurShape();
		if(isValidate(curPosition.x+1, curPosition.y, shape)){
			curPosition.x++;
		}
	}
	
	void goDown(){
		Shape shape = ResourceManager.getSingleton().getCurShape();
		int  colorId = ResourceManager.getSingleton().curColorId;
		if(isValidate(curPosition.x, curPosition.y+1, shape)){
			curPosition.y++;
		}else{			
			for(Point point: shape.points){
				int x = point.x+curPosition.x;
				int y = point.y+curPosition.y;
				board[y][x] = 1;
				colors[y][x] = colorId;
			}
			curPosition.x = 4;
			curPosition.y = 0;
			ResourceManager.getSingleton().generateNext();
			for(TouchBottomListener listener: listeners){
				listener.touchBottom();
			}
		}
	}
	
	boolean isValidate(int x, int y, Shape shape){
		for(Point point: shape.points){
			int newX = point.x+x;
			int newY = point.y+y;
			if(newX < 0 || newX > maxX || newY < 0 || newY > maxY) return false;
			if(board[newY][newX]==1 ) return false;			
		}
		return true;
	}	
}
