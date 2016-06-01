package edu.miamioh.SchematicRenderer;

/**
 * Created by shaffebd.
 */
public class Constants {

    edu.miamioh.verilogworld.Constants worldConstants = new edu.miamioh.verilogworld.Constants();
    //SchematicRenderer constants
    public final int DEFAULT_WIDTH = worldConstants.DEFAULT_WIDTH;
    public final int DEFAULT_HEIGHT = worldConstants.DEFAULT_HEIGHT;
    public final int gateSize = 2;
    public final int leftEdge = worldConstants.DEFAULT_WIDTH / 20;
    public final int rightEdge = worldConstants.DEFAULT_WIDTH - leftEdge;
    public final int bottomEdge = leftEdge;
    public final int topEdge = worldConstants.DEFAULT_HEIGHT - bottomEdge;
    public static boolean frame = false;
    public static int scaleFactor = 40;

}
