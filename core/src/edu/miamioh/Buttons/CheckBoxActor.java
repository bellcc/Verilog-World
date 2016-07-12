package edu.miamioh.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CheckBoxActor {
		
	public CheckBox createCheckBox(String text) {
		
		Skin skin = new Skin(Gdx.files.internal ("uiskin.json"));
		CheckBox actor = new CheckBox(text, skin);

		return actor;
	}

}
