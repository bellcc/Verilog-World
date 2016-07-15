/**
 * @author Zachary McQuigg
 * @date   06-15-2016
 * @info   
 */

package edu.miamioh.Screens;
 
import java.io.File;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.miamioh.verilogWorld.VerilogWorldMain;

public class MainMenuScreen implements Screen {
	
	private SpriteBatch batch;
	private SpriteBatch batch2;
	private Sprite sprite;
	private BitmapFont font;
	private int buttonWidth;
	private int buttonHeight;
	
	
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinPg;
    private Skin skinO;
    private Skin skinE;
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton exitButton;
    
    public MainMenuScreen(VerilogWorldMain vwm) {
    	
    }

    @Override
    public void show() {
       
    	font = new BitmapFont();
    	
    	skinPg = new Skin();
    	skinO = new Skin();
    	skinE = new Skin();

    	buttonStyles();
		
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getHeight(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        
        //sets up a background image for the menu
        batch2 = new SpriteBatch();
        Texture backTex = new Texture(getImagePath() + "/World.png");
        sprite = new Sprite(backTex);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
    	
    	//This sets up a table to add the buttons to
    	Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        //Creates buttons
        playButton = new TextButton("", skinPg);
        optionsButton = new TextButton("", skinO);
        exitButton = new TextButton("", skinE);

        clickedListeners();

        buttonHeight = Gdx.graphics.getHeight()/7;
        buttonWidth = viewport.getScreenWidth() - viewport.getScreenWidth()/4;
        
        //Add buttons to table
        mainTable.add(playButton).height(buttonHeight).width(buttonWidth);
        mainTable.row();
        mainTable.add(optionsButton).height(buttonHeight).width((2*buttonWidth)/3);
        mainTable.row();
        mainTable.add(exitButton).height(buttonHeight).width(buttonWidth/3);
        
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
        skinPg.dispose();
        skinO.dispose();
        skinE.dispose();
        stage.dispose();
    }
    
    public void clickedListeners() {
        //Click listeners for each of the buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {	            	
            	VerilogWorldMain.getVerilogWorldMain().setPlayScreen();	 
            }
        });
        
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {	 
            	VerilogWorldMain.getVerilogWorldMain().setOptionScreen();
            }
        });
        
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }
    
	public void buttonStyles(){
    	skinPg.add("default", font);
    	skinO.add("default", font);
    	skinE.add("default", font);
		
    	//adds an image texture to the skin of each button
		skinPg.add("textColor", new Texture(getImagePath() + "/play game.png"));
		skinO.add("textColor", new Texture(getImagePath() + "/options.png"));
		skinE.add("textColor", new Texture(getImagePath() + "/exit.png"));

		//This sets up a style for each button
		TextButtonStyle buttonStylePg = new TextButtonStyle();
		buttonStylePg.up = skinPg.newDrawable("textColor", Color.WHITE);
		buttonStylePg.down = skinPg.newDrawable("textColor", Color.DARK_GRAY);
		buttonStylePg.over = skinPg.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStylePg.font = skinPg.getFont("default");
		skinPg.add("default", buttonStylePg);
		
		TextButtonStyle buttonStyleO = new TextButtonStyle();
		buttonStyleO.up = skinO.newDrawable("textColor", Color.WHITE);
		buttonStyleO.down = skinO.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleO.over = skinO.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleO.font = skinO.getFont("default");
		skinO.add("default", buttonStyleO);
		
		TextButtonStyle buttonStyleE = new TextButtonStyle();
		buttonStyleE.up = skinE.newDrawable("textColor", Color.WHITE);
		buttonStyleE.down = skinE.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleE.over = skinE.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleE.font = skinE.getFont("default");
		skinE.add("default", buttonStyleE);
	}
	
	public String getImagePath() {
		
		String filePath = System.getProperty("user.dir") + "/assets/images";
    	return filePath;
	
	}

}