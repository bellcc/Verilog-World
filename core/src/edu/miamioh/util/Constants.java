
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   
 */

package edu.miamioh.util;

public final class Constants { 
	
	//The world width/height is measured in cells.
	public static final int WORLD_WIDTH = 10;
	public static final int WORLD_HEIGHT = 10;
	
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	
	public static final int BUFFER_WIDTH = 35;
	public static final int BUFFER_HEIGHT = 35;
	
	public static final int GRID_WIDTH = 25;
	public static final int GRID_HEIGHT = 25;

	public static final int STEP_WIDTH = 5;
	public static final int STEP_HEIGHT = 5;
	
	public static final int GENERAL_OBJECT_SIZE = 1;
	
	public static final int GENERAL_OBJECT_RED = 255;
	public static final int GENERAL_OBJECT_GREEN = 255;
	public static final int GENERAL_OBJECT_BLUE = 255;
	
	//General Object constants
	public int generalObjectSize = 1;
	
	public int generalObjectRed = 255;
	
	public int generalObjectBlue = 255;
	
	public int generalObjectGreen = 255;
	
	public boolean moveable = false;

	//SchematicRenderer constants
	public final int gateSize = 2;
	public final int leftEdge = WINDOW_WIDTH / 20;
	public final int rightEdge = WINDOW_WIDTH - leftEdge;
	public final int bottomEdge = leftEdge;
	public final int topEdge = WINDOW_HEIGHT - bottomEdge;
	public static boolean frame = false;
	public static int scaleFactor = 40;

}
