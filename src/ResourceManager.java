import java.awt.Color;
import java.util.Random;

public class ResourceManager{
	static ResourceManager singleton = new ResourceManager();
	int nextShapeId = 21;
	int nextColorId = 21/4;
	Random random = new Random();
	static ResourceManager getSingleton(){
		return singleton;
	}
	Shape[] shapes = new Shape[28];
	Color[] colors = new Color[7];
	
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
		
		nextShapeId = random.nextInt(27);
		nextColorId = nextShapeId/4;
	}
	
	private ResourceManager(){		
		colors[4] = new Color(139, 25, 155);//purple			
		colors[2] = Color.orange;
		colors[3] = Color.blue;
		colors[0] = Color.cyan;		
		colors[5] = Color.red;
		colors[1] = Color.green;		
		colors[6] = Color.yellow;
		
		shapes[0] = new Shape(new int[][]{
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}});
		shapes[1] = new Shape(new int[][]{
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0}});
		shapes[2] = shapes[0];
		shapes[3] = shapes[1];
		shapes[4] = new Shape(new int[][]{					
				{0,1,1,0},
				{0,1,1,0},
				{0,0,0,0},
				{0,0,0,0}});
		shapes[5] = shapes[4];
		shapes[6] = shapes[4];
		shapes[7] = shapes[4];
		shapes[8] = new Shape(new int[][]{
				{0,0,0,0},
				{1,0,0,0},
				{1,1,0,0},
				{1,0,0,0}});
		shapes[9] = new Shape(new int[][]{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,1,0},
				{0,1,0,0}});
		shapes[10] = new Shape(new int[][]{
				{0,0,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{0,1,0,0}});
		shapes[11] = new Shape(new int[][]{
				{0,0,0,0},
				{0,1,0,0},
				{1,1,1,0},
				{0,0,0,0}});
		shapes[12] = new Shape(new int[][]{
				{0,0,0,0},
				{1,1,0,0},
				{1,0,0,0},
				{1,0,0,0}});
		shapes[13] = new Shape(new int[][]{
				{0,0,0,0},
				{1,1,1,0},
				{0,0,1,0},
				{0,0,0,0}});
		shapes[14] = new Shape(new int[][]{
				{0,1,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{0,0,0,0}});
		shapes[15] = new Shape(new int[][]{
				{0,0,0,0},
				{1,0,0,0},
				{1,1,1,0},
				{0,0,0,0}});
		shapes[16] = new Shape(new int[][]{
				{0,0,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{1,0,0,0}});
		shapes[17] = new Shape(new int[][]{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,0,0},
				{0,1,1,0}});
		shapes[18] = new Shape(new int[][]{
				{0,0,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{1,0,0,0}});
		shapes[19] = new Shape(new int[][]{
				{0,0,0,0},
				{0,0,0,0},
				{1,1,0,0},
				{0,1,1,0}});
		shapes[20] = new Shape(new int[][]{
				{0,0,0,0},
				{1,1,0,0},
				{0,1,0,0},
				{0,1,0,0}});
		shapes[21] = new Shape(new int[][]{
				{0,0,0,0},
				{0,0,1,0},
				{1,1,1,0},
				{0,0,0,0}});
		shapes[22] = new Shape(new int[][]{
				{0,1,0,0},
				{0,1,0,0},
				{0,1,1,0},
				{0,0,0,0}});
		shapes[23] = new Shape(new int[][]{
				{0,0,0,0},
				{1,1,1,0},
				{1,0,0,0},
				{0,0,0,0}});
		shapes[24] = new Shape(new int[][]{
				{0,0,0,0},
				{0,1,0,0},
				{0,1,1,0},
				{0,0,1,0}});
		shapes[25] = new Shape(new int[][]{
				{0,0,0,0},
				{0,0,0,0},
				{0,1,1,0},
				{1,1,0,0}});
		shapes[26] = new Shape(new int[][]{
				{0,0,0,0},
				{0,1,0,0},
				{0,1,1,0},
				{0,0,1,0}});
		shapes[27] = new Shape(new int[][]{
				{0,0,0,0},
				{0,0,0,0},
				{0,0,1,1},
				{0,1,1,0}});
	}
}