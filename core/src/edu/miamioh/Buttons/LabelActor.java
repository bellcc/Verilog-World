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