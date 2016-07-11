
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

/**
 * @author Clark Bell
 * @date   7-11-2016
 * @info   This is the basic framework for a label actor that
 *         is added to a stage using the libgdx game development
 *         library.
 */

package edu.miamioh.Buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class LabelActor {

	/**
	 * This method will create and return a label actor that can
	 * be added to a stage using libgdx.
	 * 
	 * @param  text The default text that will be in the label.
	 * @param  backgroundColor The background color of the label.
	 * @param  fontColor The color of the text (font color) in the
	 *         label.
	 * 
	 * @return The label actor created based on the specifications
	 *         of the parameters.
	 */
	public Label createLabel(String text, Color backgroundColor, Color fontColor) {
		
		Skin skin = new Skin();

		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		pixmap.setColor(backgroundColor);
		pixmap.fill();
		
		skin.add("font-color", new Texture(pixmap));
		
		BitmapFont bfont = new BitmapFont();
		skin.add("default-font", bfont);
					
		LabelStyle labelStyle = new LabelStyle(bfont, fontColor);
		labelStyle.background = skin.newDrawable("font-color", backgroundColor);
		
		labelStyle.font = skin.getFont("default-font");
		
		skin.add("default-font", labelStyle);
		
		Label label = new Label(text, labelStyle);
		return label;
	}
	
}