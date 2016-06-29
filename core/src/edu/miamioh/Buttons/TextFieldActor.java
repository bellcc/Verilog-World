
package edu.miamioh.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TextFieldActor {
	
	public TextField createTextField() {

		Skin skin = new Skin(Gdx.files.internal ("uiskin.json"));
		
		TextField textField = new TextField("", skin);
		return textField;
		
	}
	
}