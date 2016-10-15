package leo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ConfigureFrame extends JFrame {
	//M scoring factor (range:	1-10)
	JSlider mSlider;
	//N number of rows required for	each Level of difficulty (range:	20-50)
	JSlider nSlider;
	//S speed factor (range:	0.1-1.0).
	JSlider sSlider;
	public ConfigureFrame() {
		Font font = new Font("Serif", Font.ITALIC, 15);
        this.setTitle("Configuration");        
        this.pack();
        this.setVisible(false);
        setLayout(new BorderLayout());
        JPanel jp = new JPanel(new GridLayout(6,1));
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
        this.setBounds(0, 0, 400, 400);               
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
}
