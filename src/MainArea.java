import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class MainArea extends Bounds{	
	DeviceCoordVsLeftTopLogic localCoordSys = new DeviceCoordVsLeftTopLogic();
	MyButton pauseButton;
	int boardHeight = 20;
	int boardWidth = 10;
	int[][] board = null;
	int[][] colors = null;
	MainArea(){		
		pauseButton = new MyButton("PAUSE", Color.blue);			
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
		
		board[2][5] = 1;
		board[2][4] = 1;
		board[3][5] = 1;
		board[3][4] = 1;
		
		colors[2][5] = 1;
		colors[2][4] = 1;
		colors[3][5] = 1;
		colors[3][4] = 1;
		
		board[17][9] = 1;
		board[18][9] = 1;
		board[19][9] = 1;
		board[19][8] = 1;
		
		colors[17][9] = 3;
		colors[18][9] = 3;
		colors[19][9] = 3;
		colors[19][8] = 3;
		
		board[19][6] = 1;
		board[19][7] = 1;
		board[18][7] = 1;
		board[18][8] = 1;
		
		colors[19][6] = 6;
		colors[19][7] = 6;
		colors[18][7] = 6;
		colors[18][8] = 6;	
		
	}
	
	public void paint(Graphics g){						
		g.setColor(Color.black);		
		Rectangle rectangle = getBounds();
		g.drawRect(rectangle.x,rectangle.y,rectangle.width-1,rectangle.height-1);				
		localCoordSys.update(rectangle);			
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
}