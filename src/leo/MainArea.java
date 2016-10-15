package leo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

class MainArea extends Bounds implements TickListener, TouchBottomListener{	
	DeviceCoordVsLeftTopLogic localCoordSys = new DeviceCoordVsLeftTopLogic();
	GameCore gameCore = null;
	MyButton pauseButton;
	int boardHeight = 20;
	int boardWidth = 10;
	int[][] board = null;
	int[][] colors = null;
	Graphics graphics = null;
	Tetris parent = null;
	Boolean updateBoard = false;
	void setParent(Tetris parent ){
		this.parent = parent;
	}
	MainArea(){		
		pauseButton = new MyButton("PAUSE", Color.MAGENTA);			
		pauseButton.setVisible(false);			
		localCoordSys.rHeight = boardHeight;
		localCoordSys.rWidth = boardWidth;
		board = new int[boardHeight][];
		colors = new int[boardHeight][];		
		for(int j = 0; j < boardHeight; j++){
			board[j] = new int[boardWidth];
			colors[j] = new int[boardWidth];
			for(int i = 0; i < boardWidth; i++){
				board[j][i] = 0;
				colors[j][i] = 0;
			}
		}
		gameCore = new GameCore(board, colors);
		TickThread.getSingleton().addListener(this);	
		gameCore.addTouchBottomListener(this);
		gameCore.addCleanLinesListener(ScoreController.getSingleton());
	}
	
	public void paint(Graphics g){	
		graphics = g;
		g.setColor(Color.black);		
		Rectangle rectangle = getBounds();	
		g.drawRect(rectangle.x,rectangle.y,rectangle.width-1,rectangle.height-1);	
		localCoordSys.update(rectangle);
		
		// paint cur shape
		Point curPosition = gameCore.curPosition;
		Shape curShape = ResourceManager.getSingleton().getCurShape();
		Color curColor = ResourceManager.getSingleton().getCurColor();
		for(Point point: curShape.points){
			int newX = point.x+curPosition.x;
			int newY = point.y+curPosition.y;
			int x = localCoordSys.iX(newX);
			int y = localCoordSys.iY(newY);
			int w = localCoordSys.iX(newX+1)-x;
			int h = localCoordSys.iY(newY+1)-y;
			Square.draw(g, x, y, w, h, curColor);
		}
		// paint board		
			
		for (int j = 0; j < boardHeight; j++) {
			for(int i = 0; i < boardWidth; i++){				
				if(board[j][i] == 1){
					int x = localCoordSys.iX(i);
					int y = localCoordSys.iY(j);
					int w = localCoordSys.iX(i+1)-localCoordSys.iX(i);
					int h = localCoordSys.iY(j+1)-localCoordSys.iY(j);
					Square.draw(g, x, y, w, h, ResourceManager.getSingleton().getColor(colors[j][i]));
				}
			}
		}
		
		int size = localCoordSys.iLength(1);
		rectangle = new Rectangle(localCoordSys.iX(2), localCoordSys.iY(9), size*6, size*2);		
		pauseButton.setBounds(rectangle);
		pauseButton.paint(g);		
	}
	
	public void showPause(){
		pauseButton.setVisible(true);				
	}
	
	public void hidePause(){	
		pauseButton.setVisible(false);
	}

	@Override
	public void updateAction() {
		if(!pauseButton.isVisible()){
			gameCore.goDown();	
			parent.repaint();
		}		
	}
	@Override
	public void touchBottom() {
		updateBoard = true;		
		//parent.repaint();
		updateBoard = false;
	}	
}