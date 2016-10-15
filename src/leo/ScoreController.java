package leo;

import java.util.LinkedList;

public class ScoreController implements CleanLinesListener{
	static ScoreController singleton = new ScoreController();
	static ScoreController getSingleton(){
		//if(singleton==null) return new ScoreController();
		return singleton;
	}

	private ScoreController(){
		
	}
	
	void init(){
		iScore = 0;
		iLevel = 1;
		 iLines = 0;
		 M = 1;
		 N = 20;
		 S = 1.0;
		 curLines = 0;
	}
	int iScore = 0;
	int iLevel = 1;
	int iLines = 0;
	int M = 1;

	int N = 20;
	double S = 1.0;
	int curLines = 0;
	double FS = 1;
	LinkedList<CleanLinesListener> cleanLinesListeners = new LinkedList<>();
	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public double getS() {
		return S;
	}

	public void setS(double s) {
		S = s;
	}
	@Override
	public void lineClean(int lines) {
		iScore = iScore + lines*iLevel*M;
		iLines+=lines;
		curLines += lines;
		if(curLines >= N){
			iLevel++;
			curLines=0;
			FS = FS*(1+iLevel*S);
		}
		System.out.println("ScoreController lineClean");
		for(CleanLinesListener listener: cleanLinesListeners){
			listener.lineClean(lines);
		}
	}
	
	
	void addCleanLinesListener(CleanLinesListener listener){
		cleanLinesListeners.add(listener);
	}
	
}
