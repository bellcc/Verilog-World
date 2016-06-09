
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;

public class Block extends AbstractGameObject{

	private Color color;
	
	public Block() {
		color = new Color(Color.BLACK);
	}
	
	public Block(Color color) {
		this.color = color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
}
