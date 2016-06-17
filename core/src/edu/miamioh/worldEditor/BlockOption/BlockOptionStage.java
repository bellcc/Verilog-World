
/**
 * @author Chris Bell
 * @date   6-13-2016
 * @info   
 */

package edu.miamioh.worldEditor.BlockOption;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import edu.miamioh.verilogWorld.VerilogWorldController;

public class BlockOptionStage{

	private TextButton verilogEditorButton;
	private TextButton removeButton;
	
	public BlockOptionStage() {

		verilogEditorButton = createButton(Color.GREEN, "Verilog\nEditor");
		verilogEditorButton.addListener(new VerilogEditorChangeListener(VerilogWorldController.getController().getSim()));

		removeButton = createButton(Color.RED, "Remove");
		removeButton.addListener(new VerilogEditorChangeListener(VerilogWorldController.getController().getSim()));
		
	}
	
	private TextButton createButton(Color color, String text) {
		
		// A skin stores resources for UI widgets to use (fonts, 
		// colors, etc). Resource are named and can be looked 
		// up by name and type.
		Skin skin = new Skin();
		
		// A Pixmap represents an image in memory. 
		Pixmap pixmap = new Pixmap(80, 80, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
			
		skin.add("white", new Texture(pixmap));
		
		// Renders bitmap fonts. The dispose() method must 
		// be called when the texture is free otherwise memory 
		// leaks can occur.
		BitmapFont bfont=new BitmapFont();
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
	
		TextButton button = new TextButton(text, buttonStyle);
		
		return button;
		
	}
	
	public TextButton getVerilogEditorButton() {
		return verilogEditorButton;
	}
	
	public TextButton getRemoveButton() {
		return removeButton;
	}

}
