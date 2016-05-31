
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   
 */

package edu.miamioh.worldEditor.types;

public class Point {

	private int xPoint;
	private int yPoint;
	
	public Point() {
		xPoint = 0;
		yPoint = 0;
	}
	
	public Point(int x, int y) {
		xPoint = x;
		yPoint = y;
	}
	
	public void setX(int x) {
		xPoint = x;
	}
	
	public int getX() {
		return this.xPoint;
	}
	
	public void setY(int y) {
		yPoint = y;
	}
	
	public int getY() {
		return yPoint;
	}

	@Override
	public String toString() {
		return "Point [xPoint=" + xPoint + ", yPoint=" + yPoint + "]";
	}
	
}
