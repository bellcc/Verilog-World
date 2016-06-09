
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Block extends AbstractGameObject{

	private Color color;
	private Button block;
	
	public Block() {
		color = new Color(Color.BLACK);
		createButton();
	}
	
	private void createButton() {
		
		Skin skin = new Skin();
		BitmapFont bfont = new BitmapFont();
		TextButtonStyle style = new TextButtonStyle();		
		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		
		pixmap.setColor(color);
		pixmap.fill();
			
		skin.add("white", new Texture(pixmap));
		skin.add("default", bfont);

		style.up = skin.newDrawable("white", Color.WHITE);
		style.down = skin.newDrawable("white", Color.WHITE);
		style.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		style.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		style.font = skin.getFont("default");
					
		skin.add("default", style);
		
		block = new Button(style);
		
		block.addListener(new BlockChangeListener());
		
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
	
	public Button getBlock() {
		return this.block;
	}
}
