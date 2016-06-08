
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   This class is responsible for the home button actor. 
 *         This button will take the user back to a main menu of 
 *         their choosing based on a selection to another window 
 *         that the application prompts the user with.
 */

package edu.miamioh.worldEditor.ToolBar.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.ChangeListeners.HomeChangeListener;

public class HomeActor extends AbstractToolBarActor {

	// The home actor button that this actor represents.
	//private TextButton button;
	
	/**
	 * The constructor call creates the text button. 
	 */
	public HomeActor() {
		
		buttonText = "HOME";
		color = new Color(Color.GREEN);
		
		create();
	}
	
	/**
	 * This method creates the actor that represents the home button. 
	 * All of the styling details are also defined in this method.
	 */
	public void create() {
		
		// A skin stores resources for UI widgets to use (fonts, 
		// colors, etc). Resource are named and can be looked 
		// up by name and type.
		Skin skin = new Skin();
		
		// A Pixmap represents an image in memory. 
		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		//pixmap.setColor(Color.GREEN);
		pixmap.setColor(color);
		pixmap.fill();
			
		skin.add("white", new Texture(pixmap));
		
		// Renders bitmap fonts. The dispose() method must 
		// be called when the texture is free otherwise memory 
		// leaks can occur.
		BitmapFont bfont = new BitmapFont();
		skin.add("default", bfont);
		
		// The style for a text button. However I do not know what 
		// any of these variables for Drawable mean but if they 
		// are not present then no background color shows up.
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("white", Color.WHITE);
		buttonStyle.down = skin.newDrawable("white", Color.WHITE);
		buttonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		buttonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

		buttonStyle.font = skin.getFont("default");
		
		skin.add("default", buttonStyle);
	
		button = new TextButton(buttonText, buttonStyle);

		button.addListener(new HomeChangeListener());
		
	}

	/**
	 * This method returns the button that represents the home actor.
	 * @return The button that controls the home actor operations.
	 */
	@Override
	public TextButton getButtonActor() {

		return button;
	}
}
