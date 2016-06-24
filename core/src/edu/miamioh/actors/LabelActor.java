package edu.miamioh.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class LabelActor {

	private Label label;
	
	public LabelActor() {
		
	}
	
	public Label createLabel(String text) {

		NinePatch patch = new NinePatch(new Texture(text), 10, 10, 10, 10);
		Skin skin = new Skin();
		skin.add("background", patch);
		
		LabelStyle style = new LabelStyle();
		style.background = skin.getDrawable("background");
		
		return label;
		
	}
}