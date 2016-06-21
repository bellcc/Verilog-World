package edu.miamioh.PictureButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class PictureButton extends AbstractPictureButtonActor {
	private Stage stage;
	private String imgName;
	private int x;
	private int y;
	private int width;
	private int height;
	public TextButton button;
	public String buttonText;


	/* Constructor Creates a Text Button with image "imgName" 
	 * at position x & y
	 * with dimensions width and height 
	 */
	public PictureButton(String imgName, int x, int y, int width, int height){
		this.imgName = imgName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		stage = new Stage();
		buttonText = "";
		
		create();
	
	}
	
	/* Calls the method to create the stage for the Button */	
	public void init() {
		
		createStage();
	
	}
	
	/* Creates The Picture Button Actor */
	public void create () {

		Skin skin = new Skin();
			
		skin.add("white", new Texture(Gdx.files.internal(imgName)));

		BitmapFont bfont = new BitmapFont();
		skin.add("default", bfont);
		
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("white", Color.WHITE);
		buttonStyle.down = skin.newDrawable("white", Color.WHITE);
		buttonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
		buttonStyle.over = skin.newDrawable("white", Color.BLACK);

		buttonStyle.font = skin.getFont("default");
		
		skin.add("default", buttonStyle);
	
		button = new TextButton(buttonText, buttonStyle);

		button.addListener(new PictureButtonChangeListener());
		

					
	}


	public TextButton getButtonActor() {
		
		return button;
	
	}
	
	/* Creates the stage for the button and set its position and dimentions */
	public void createStage() {
		
		Actor buttonActor = getButtonActor();
		buttonActor.setPosition(x, y);
		buttonActor.setHeight(height);
		buttonActor.setWidth(width);
		
		stage.addActor(buttonActor);
	
	}
	
	public Stage getStage() {
		
		return stage;
	}
	
	
}
