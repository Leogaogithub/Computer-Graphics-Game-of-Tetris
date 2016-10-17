package leo;

import java.util.LinkedList;

public class TickThread implements Runnable {
	
	int ticks = 0;
	static TickThread single = new TickThread();
	LinkedList<TickListener> listeners = null;
	boolean isSendAction = false;
	private TickThread() {	
		listeners = new LinkedList<>();
	}
	
	static TickThread getSingleton(){
		return single;
	}
	
	void setSendAction(boolean isSendAction){
		this.isSendAction = isSendAction;
	}
	
	void addListener(TickListener listener){
		listeners.add(listener);
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep((int)(600/ScoreController.getSingleton().FS));
				
				ticks++;
				if(isSendAction){
					for(TickListener listener: listeners){
						listener.updateAction();
					}
				}				
				//System.out.println(ticks);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}		
	}	
}
