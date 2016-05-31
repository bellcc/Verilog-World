
/**
 * @author Chris Bell
 * @date   05-31-2016
 * @info   The generic block object.
 */

package edu.miamioh.worldEditor.blockActors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BlankBlockActor {

	private Label label;

	public BlankBlockActor() {
		
		create();
	}
	
	public void create() {

		Skin skin = new Skin();
					
		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		pixmap.setColor(Color.PURPLE);
		pixmap.fill();
						
		skin.add("white", new Texture(pixmap));
					
		BitmapFont bfont=new BitmapFont();
		skin.add("default", bfont);
					
		skin.add("default", skin);
				
		label = new Label("Blank", skin);
					
	}
	
	public Label getLabel() {
		return label;
	}

}
