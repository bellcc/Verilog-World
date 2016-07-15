package edu.miamioh.Buttons;

import java.io.File;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CheckBoxActor {
		
	public CheckBox createCheckBox(String text) {
		
		Skin skin = new Skin(new FileHandle(new File(System.getProperty("user.dir") + "/assets/uiskin.json")));
		CheckBox actor = new CheckBox(text, skin);

		return actor;
	}

}
