package leo;

import java.util.LinkedList;

public class TickThread implements Runnable {
	int ticks = 0;
	static TickThread single = new TickThread();
	LinkedList<TickListener> listeners = null;
	private TickThread() {	
		listeners = new LinkedList<>();
	}
	
	static TickThread getSingleton(){
		return single;
	}
	
	void addListener(TickListener listener){
		listeners.add(listener);
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(600);
				ticks++;
				for(TickListener listener: listeners){
					listener.updateAction();
				}
				//System.out.println(ticks);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}		
	}	
}
