package leo;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.text.Position;

public class Tetris extends Frame  implements MouseMotionListener, MouseListener,MouseWheelListener, ComponentListener{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static MainArea ma = null;
	ControlArea ca = null;	
	DeviceCoordVsCenterLogic gLogic = new DeviceCoordVsCenterLogic(); 
	static ConfigureFrame configureFrame = new ConfigureFrame();
	boolean bMouseInMainArea = false;
	boolean bShapeHasChanged = false;
	Thread tickThread = null;
	Tetris(){
		super("Tetris");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		bMouseInMainArea = false;
		ma = new MainArea();
		ca = new ControlArea();
		ma.setParent(this);
		setLayout(null);		
		setSize(410,410);		
		setVisible(true);	
		
		addComponentListener(this); 
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		Thread tickThread = new Thread(TickThread.getSingleton());
		ScoreController.getSingleton();
		tickThread.run();
	}	
	
	@Override
	public void paint(Graphics g) {			
		ca.paint(g);
		ma.paint(g);
		super.paint(g);
	}
	
	public static void main(String[] args) {
			new Tetris();			
	}
	
	private boolean checkPointInRectangular(Point point, Rectangle rectangle) {
		
		if(point.getX() < rectangle.getMaxX() 
		&& point.getX() > rectangle.getMinX()
		&& point.getY() < rectangle.getMaxY()
		&& point.getY() > rectangle.getMinY()) 
			return true;
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		Rectangle quitRec = ca.quitButton.getBounds();
		boolean inQuitButton = checkPointInRectangular(point, quitRec);
		if(inQuitButton){
			System.out.println("quit");
			System.exit(0);
		}
		
		Rectangle confRec = ca.configButton.getBounds();
		boolean inConfigureButton = checkPointInRectangular(point, confRec);
		if(inConfigureButton){
			System.out.println("configure");
			configureFrame.setVisible(true);			
			return;
		}		

		Rectangle startRec = ca.startButton.getBounds();
		boolean inStartButton = checkPointInRectangular(point, startRec);
		if(inStartButton){
			System.out.println("Start");
			int w = ScoreController.getSingleton().getW();
			int h = ScoreController.getSingleton().getH();
			ScoreController.getSingleton().initScore();
			ma.initBoardSize(w, h);
			TickThread.getSingleton().setSendAction(true);
			return;
		}		
		
		Rectangle mainAreaRec = ma.getBounds();
		boolean inMainArea = checkPointInRectangular(point, mainAreaRec);
		if(!inMainArea){
			if(e.getButton()==MouseEvent.BUTTON1){
				ma.gameCore.goLeft();
				repaint();
			}
			if(e.getButton()==MouseEvent.BUTTON3){
				ma.gameCore.goRight();
				repaint();
			}			
		}
	}

	public void componentResized(ComponentEvent arg0) {
		Dimension d = getSize();
    	gLogic.update(d); 
		int x = gLogic.leftUpPoint.x;
		int y = gLogic.leftUpPoint.y;
		int width = gLogic.iWidth/2;
		int height = gLogic.iHeight;		
		ma.setBounds(x, y, width, height);
		ca.setBounds(x+width, y, width, height);		
		repaint();		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		Point point = e.getPoint();	
		Rectangle mainAreaRec = ma.getBounds();
		boolean inMainArea = checkPointInRectangular(point, mainAreaRec);
		if(!bMouseInMainArea && inMainArea){
			System.out.println("in");
			bMouseInMainArea = true;
			ma.showPause();				
			repaint();
		}else if((!inMainArea)&&(bMouseInMainArea)){
			System.out.println("out");
			bMouseInMainArea = false;
			ma.hidePause();
			repaint();
		}
		
		if(inMainArea&& (!bShapeHasChanged) && ma.checkPointInCurShape(point)){
			System.out.println("in cur shape");			
			ma.showPause();	
			bShapeHasChanged = true;
			ResourceManager.getSingleton().changeCurShapeByRandom();
			ScoreController.getSingleton().substractScores();
			repaint();
		}else{
			bShapeHasChanged = false;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Point point = e.getPoint();
		Rectangle mainAreaRec = ma.getBounds();
		int notches = e.getWheelRotation();
		boolean inMainArea = checkPointInRectangular(point, mainAreaRec);
		if(!inMainArea){
			if(notches>0) ma.gameCore.clockwiseRotate();
			else ma.gameCore.counterClockwiseRotate();
			repaint();
		}		
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {	}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void componentHidden(ComponentEvent arg0) {}
	@Override
	public void componentMoved(ComponentEvent arg0) {}
	@Override
	public void componentShown(ComponentEvent arg0) {}
	@Override
	public void mouseDragged(MouseEvent arg0) {}	
}






	







