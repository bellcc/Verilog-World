
/**
 * @author Chris Bell
 * @date   05-31-2016
 * @info   The generic block object.
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockActors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners.BlankBlockChangeListener;

public class BlankBlockActor extends AbstractToolBarActor{

	public BlankBlockActor() {
		
		buttonText = "Blank\nBlock";
		color = new Color(Color.RED);
		
		create();
	}
	
	public void create() {

		Skin skin = new Skin();
		
		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
			
		skin.add("white", new Texture(pixmap));

		BitmapFont bfont=new BitmapFont();
		skin.add("default", bfont);

		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("white", Color.WHITE);
		buttonStyle.down = skin.newDrawable("white", Color.WHITE);
		buttonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		buttonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

		buttonStyle.font = skin.getFont("default");
					
		skin.add("default", buttonStyle);
				
		button = new TextButton(buttonText, buttonStyle);

		button.addListener(new BlankBlockChangeListener());
					
	}
	
	public TextButton getButtonActor() {
		
		return button;
	}

}