
/**
 * @author Clark Bell
 * @date   7-11-2016
 * @info   This is the basic framework for a text button actor
 *         that will be added to a stage using the libgdx library.
 */

package edu.miamioh.Buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class TextButtonActor {

	/**
	 * This method will create a text button actor with a text and
	 * a specified background color. This can be easily modified
	 * to use an image as the background using a texture and pixmap.
	 * 
	 * @param color The background color of the text button actor.
	 * @param text The text in the button actor.
	 * 
	 * @return
	 */
	public TextButton createTextButton(Color color, String text) {

		Skin skin = new Skin();
		
		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
			
		skin.add("white", new Texture(pixmap));

		BitmapFont bfont = new BitmapFont();
		skin.add("default", bfont);

		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("white", Color.WHITE);
		buttonStyle.down = skin.newDrawable("white", Color.WHITE);
		buttonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		buttonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

		buttonStyle.font = skin.getFont("default");
		
		skin.add("default", buttonStyle);
	
		TextButton button = new TextButton(text, buttonStyle);
		
		return button;
		
	}

}
