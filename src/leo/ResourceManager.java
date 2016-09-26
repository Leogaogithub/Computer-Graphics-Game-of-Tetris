package leo;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class ResourceManager{
	static ResourceManager singleton = new ResourceManager();
	int curShapeId = 1;
	int curColorId = 1;
	int nextShapeId = 21;
	int nextColorId = 21/4;
	Random random = new Random();	
	
	static ResourceManager getSingleton(){
		return singleton;
	}
	Shape[] shapes = new Shape[28];
	Color[] colors = new Color[7];
	
	Shape getCurShape(){
		return shapes[curShapeId];
	}
	
	int clockwiseRotateCurShape(){
		int typeId = curShapeId/4;
		int rotateId = curShapeId%4;
		rotateId = (rotateId+1)%4;				
		return typeId*4+rotateId;
	}
	
	int counterClockwiseRotateCurShape(){
		int typeId = curShapeId/4;
		int rotateId = curShapeId%4;
		rotateId = (rotateId+3)%4;				
		return typeId*4+rotateId;
	}
	
	Color getCurColor(){
		return colors[curColorId];
	}
	
	Shape getShape(int shapeIdx){
		return shapes[shapeIdx];
	}
	
	Color getColor(int colorId){
		return colors[colorId];
	}
	
	
	Shape getNextShape(){		
		return shapes[nextShapeId];
	}
	
	Color getNextColor(){		
		return colors[nextColorId];
	}
	
	
	void generateNext(){
		curShapeId = nextShapeId;
		curColorId = nextShapeId/4;
		nextShapeId = random.nextInt(28);
		nextColorId = nextShapeId/4;
	}
	private Shape parseShape(String str){
		String pointsStr[] = str.split(";");
		Point point4[] = new Point[4];
		for(int i = 0; i < 4; i++){
			String xx[] = pointsStr[i].split(",");
			point4[i] = new Point(Integer.parseInt(xx[0]), Integer.parseInt(xx[1]));
		}
		Shape shape = new Shape(point4);
		return shape;
	}
	
	private ResourceManager(){		
		curShapeId = random.nextInt(28);
		curColorId = curShapeId/4;
		nextShapeId = random.nextInt(28);
		nextColorId = nextShapeId/4;
		colors[4] = new Color(139, 25, 155);//purple			
		colors[2] = Color.orange;
		colors[3] = Color.blue;
		colors[0] = Color.cyan;		
		colors[5] = Color.red;
		colors[1] = Color.green;		
		colors[6] = Color.yellow;
		String allshapes = 	 
				 "0,0;1,0;2,0;3,0E0,0;0,1;0,2;0,3E0,0;1,0;2,0;3,0E0,0;0,1;0,2;0,3E"
				+"0,0;1,0;0,1;1,1E0,0;1,0;0,1;1,1E0,0;1,0;0,1;1,1E0,0;1,0;0,1;1,1E"
				+"0,0;0,1;0,2;1,1E0,0;1,0;2,0;1,1E0,1;1,0;1,1;1,2E1,0;0,1;1,1;2,1E"
				+"0,0;0,1;0,2;1,0E0,0;1,0;2,0;2,1E0,2;1,0;1,1;1,2E0,0;0,1;1,1;2,1E"
				+"0,2;1,0;0,1;1,1E0,0;1,0;2,1;1,1E0,2;1,0;0,1;1,1E0,0;1,0;2,1;1,1E"
				+"0,0;1,0;1,2;1,1E0,1;1,1;2,1;2,0E0,0;0,1;0,2;1,2E0,0;1,0;2,0;0,1E"
				+"0,0;1,2;0,1;1,1E2,0;1,0;0,1;1,1E0,0;1,2;0,1;1,1E2,0;1,0;0,1;1,1E";
		String shapeStr[] = allshapes.split("E");
		for(int i = 0; i < 28; i++){
			shapes[i] = parseShape(shapeStr[i]);
		}		
	}
}