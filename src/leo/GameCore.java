package leo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class GameCore {
	Point curPosition ;
	int[][] board = null;
	int[][] colors = null;
	int ticks = 0;
	int maxX = 9;
	int maxY = 19;
	HashSet<Integer> cleanLineSet = null;
	
	LinkedList<TouchBottomListener> touchBottomListener = new LinkedList<>();
	
	LinkedList<CleanLinesListener> cleanLinesListeners = new LinkedList<>();
	
	void addCleanLinesListener(CleanLinesListener listener){
		cleanLinesListeners.add(listener);
	}
	
	GameCore(int[][] board, int[][] colors){
		curPosition = new Point(4,0);	
		this.board = board;
		this.colors = colors;
		maxX = board[0].length - 1;
		maxY = board.length - 1;
		cleanLineSet = new HashSet<>();
	}
	
	GameCore(){
		
	}
	
	void init(int[][] board, int[][] colors){
		curPosition = new Point(4,0);	
		this.board = board;
		this.colors = colors;
		maxX = board[0].length - 1;
		maxY = board.length - 1;
		cleanLineSet = new HashSet<>();
	}
	void addTouchBottomListener(TouchBottomListener listener){
		touchBottomListener.add(listener);
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
			cleanLineSet.clear();
			for(Point point: shape.points){
				int x = point.x+curPosition.x;
				int y = point.y+curPosition.y;
				board[y][x] = 1;
				colors[y][x] = colorId;
				if(CheckLineWithOutHole(y)){
					cleanLineSet.add(y);
				}				
			}
			curPosition.x = 4;
			curPosition.y = 0;	
			if(!cleanLineSet.isEmpty()){				
				for(CleanLinesListener listener: cleanLinesListeners){
					listener.lineClean(cleanLineSet.size());
				}
				cleanLines();
			}
			
			ResourceManager.getSingleton().generateNext();
			for(TouchBottomListener listener: touchBottomListener){
				listener.touchBottom();
			}
		}
	}
	
	void cleanLines(){
		Integer lines[] = new Integer[cleanLineSet.size()];
		lines = cleanLineSet.toArray(lines);
		Arrays.sort(lines);
		for(Integer line : lines){
			for(int y = line; y>0; y--){
				for(int x = 0; x <= maxX; x++){
					board[y][x] = board[y-1][x] ;
					colors[y][x] = colors[y-1][x];
				}
			}
		}
	}
	
	boolean CheckLineWithOutHole(int y){
		for(int x=0; x <= maxX; x++){
			if(board[y][x]==0) return false;			
		}
		return true;
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
