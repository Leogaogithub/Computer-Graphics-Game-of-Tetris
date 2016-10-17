package leo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ConfigureFrame extends JFrame implements WindowListener{
	//M scoring factor (range:	1-10)
	JSlider mSlider;
	//N number of rows required for	each Level of difficulty (range:	20-50)
	JSlider nSlider;
	//S speed factor (range:	0.1-1.0).
	JSlider sSlider;
	// width
	JSlider wSlider;
	// height
	JSlider hSlider;
	public ConfigureFrame() {
		Font font = new Font("Serif", Font.ITALIC, 15);
        this.setTitle("Configuration");        
        this.pack();
        this.setVisible(false);
        setLayout(new BorderLayout());
        JPanel jp = new JPanel(new GridLayout(12,1));
        jp.setFont(font);
        JLabel jmlabel = new JLabel("M scoring factor");
        jp.add(jmlabel);
        mSlider = createSlider(1,10,1);
        jp.add(mSlider);  
        
        JLabel jnlabel = new JLabel("N number of rows required for	each Level of difficulty");
        jp.add(jnlabel);
        nSlider = createSlider(1,50,1);
        jp.add(nSlider);
        
        JLabel jslabel = new JLabel("S speed factor");
        jp.add(jslabel);
        sSlider = createSlider(1,10,10);
        jp.add(sSlider);
        
        JLabel jwlabel = new JLabel("number of squares along X");
        jp.add(jwlabel);
        wSlider = createSlider(10,30,10);
        jp.add(wSlider);
        
        JLabel jhlabel = new JLabel("number of squares along Y");
        jp.add(jhlabel);
        hSlider = createSlider(20,60,20);
        jp.add(hSlider);
        
        this.add(jp, BorderLayout.CENTER);        
        Hashtable<Integer, JLabel> stable = new Hashtable<Integer, JLabel>();
        JLabel jst1 =  new JLabel("O.1");        
        stable.put(1, jst1);
        JLabel jst6 =  new JLabel("1.0");        
        stable.put(10, jst6);       
        sSlider.setLabelTable(stable); 
        
        Hashtable<Integer, JLabel> mtable = new Hashtable<Integer, JLabel>();
        JLabel jmt1 =  new JLabel("1");       
        mtable.put(1, jmt1);
        JLabel jmt6 =  new JLabel("10");        
        mtable.put(10, jmt6);       
        mSlider.setLabelTable(mtable);
        this.setBounds(0, 0, 400, 600); 
        this.addWindowListener(this);
	}
	
	static private JSlider createSlider(int min, int max, int init){		
		JSlider slider = new JSlider(JSlider.HORIZONTAL,min, max, init);        		
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);             
		return slider;
	}
	
	int getM(){
		return mSlider.getValue();
	}
	
	int getN(){
		return nSlider.getValue();
	}
	
	double getS(){
		return sSlider.getValue()/10.0;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void windowClosed(WindowEvent arg0) {				
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		ScoreController.getSingleton().setM(getM());
		System.out.println("M is seted to " + ScoreController.getSingleton().getM());
		ScoreController.getSingleton().setN(getN());
		ScoreController.getSingleton().setS(getS());
		ScoreController.getSingleton().setH(hSlider.getValue());
		ScoreController.getSingleton().setW(wSlider.getValue());
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
