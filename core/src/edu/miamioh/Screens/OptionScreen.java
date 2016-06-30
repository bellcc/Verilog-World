/**
 * @author Zachary McQuigg
 * @date   06-30-2016
 * @info   
 */

package edu.miamioh.Screens;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.miamioh.verilogWorld.VerilogWorldMain;
 
public class OptionScreen implements Screen {
	
	private SpriteBatch batch;
	private SpriteBatch batch2;
	private Sprite sprite;
	private BitmapFont font;
	private int buttonWidth;
	private int buttonHeight;
	
    private TextArea textArea;
    private static int textAreaY;
    private static int textAreaX;

    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinCm;
    private Skin skinB;
    private Skin textSkin;
    
    private TextButton clickMeButton;
    private TextButton backButton;
    
    public OptionScreen(VerilogWorldMain vwm) {
    
    }

    @Override
    public void show() {
       
    	font = new BitmapFont();
    	
    	skinCm = new Skin();
    	skinB = new Skin();

    	buttonStyles();
		
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getHeight(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        
        //set up a background image for the menu
        batch2 = new SpriteBatch();
        Texture backTex = new Texture(Gdx.files.internal("images/circuit_1.png"));
        sprite = new Sprite(backTex);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
    	
    	//set up a table to add the buttons to
    	Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        //Create buttons
        clickMeButton = new TextButton("", skinCm);
        backButton = new TextButton("", skinB);

    	textSkin = new Skin(Gdx.files.internal ("uiskin.json"));
        textAreaY = Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/7)*2);
        textAreaX = 0;

        buttonHeight = Gdx.graphics.getHeight()/7;
        buttonWidth = viewport.getScreenWidth() - viewport.getScreenWidth()/4;
        
        //Add buttons to table
        mainTable.add(clickMeButton).height(buttonHeight).width(buttonWidth);
        mainTable.row();
        mainTable.add(backButton).height(buttonHeight).width(buttonWidth);
        
        clickedListeners();
        
        //Add the table to the stage
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)0/255, (float)0/255, (float)0/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        
        batch2.begin();
        sprite.draw(batch2);
        batch2.end();
        
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    	viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skinCm.dispose();
        skinB.dispose();
        stage.dispose();
    }
    
    public void clickedListeners() {
    	
        //Click listeners for each of the buttons
        clickMeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {	            	
            	String describe = "YOU CLICKED ON A BUTTON ONLY BECAUSE IT SAID \"CLICK ME!\". "
            			+ "YOU SHOULD NOT HAVE DONE THAT! YOUR PET POLAR BEAR IS NOW PROPERTY "
            			+ "OF THE UNITED STATES AIR FORCE. \n\nGOOD JOB!";
    			textArea = new TextArea(describe, textSkin); 
    			
            	textArea.setDisabled(true);
            	textArea.setWidth(viewport.getScreenWidth());
            	textArea.setHeight((Gdx.graphics.getHeight()/7)*2);
    	        textArea.setPosition(textAreaX, textAreaY);
        		stage.addActor(textArea);
            }
        });
        
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {	            	
            	VerilogWorldMain.getVerilogWorldMain().setMainMenuScreen();	            	
            }
        });
    }
    
    public void buttonStyles(){
    	skinCm.add("default", font);
    	skinB.add("default", font);
		
    	//adds an image texture to the skin of each button
		skinCm.add("textColor", new Texture(Gdx.files.internal("images/click me.png")));
		skinB.add("textColor", new Texture(Gdx.files.internal("images/back.png")));
		 		
		//This sets up a style for each button
		TextButtonStyle buttonStyleCm = new TextButtonStyle();
		buttonStyleCm.up = skinCm.newDrawable("textColor", Color.WHITE);
		buttonStyleCm.down = skinCm.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleCm.over = skinCm.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleCm.font = skinCm.getFont("default");
		skinCm.add("default", buttonStyleCm);
		
		TextButtonStyle buttonStyleB = new TextButtonStyle();
		buttonStyleB.up = skinB.newDrawable("textColor", Color.WHITE);
		buttonStyleB.down = skinB.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleB.over = skinB.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleB.font = skinB.getFont("default");
		skinB.add("default", buttonStyleB);
		
	}

}