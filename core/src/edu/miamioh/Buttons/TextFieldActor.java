
/**
 * @author Clark Bell
 * @date   7-11-2016
 * @info   The basic framework for a text field actor which
 *         can be added to a stage using the libgdx library.
 */

package edu.miamioh.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TextFieldActor {

	/**
	 * This method will create a text field based on the skin 
	 * description in the uiskin.json file located in assets.
	 * 
	 * @return The text field actor based on the description in 
	 *         the uiskin.json file.
	 */
	public TextField createTextField() {

		Skin skin = new Skin(Gdx.files.internal ("uiskin.json"));
		
		TextField textField = new TextField("", skin);
		return textField;
		
	}
	
}