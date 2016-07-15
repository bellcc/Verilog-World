/**
 * @author Zachary McQuigg
 * @date   06-16-2016
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

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldMain;
 
public class PlayScreen implements Screen {
	
	private SpriteBatch batch;
	private SpriteBatch batch2;
	private Sprite sprite;
	private BitmapFont font;
	private int buttonHeight;
	private int buttonWidth;
	
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Skin skinT;
    private Skin skinC;
    private Skin skinS;
    private Skin skinB;
    
    public boolean challenges;
    public boolean tutorials;
    private ChallengesScreen challengeScreen;

    public PlayScreen(VerilogWorldMain vwm) {
    	challengeScreen = new ChallengesScreen();    	
    }


    @Override
    public void show() {
       
    	font = new BitmapFont();
    	
    	skinT = new Skin();
    	skinC = new Skin();
    	skinS = new Skin();
    	skinB = new Skin();
		
    	buttonStyles();
    	
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getHeight(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        
        //sets up a background image for the menu
        batch2 = new SpriteBatch();
        Texture backTex = new Texture(getImagePath() + "/circuit_0.png");
        sprite = new Sprite(backTex);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
        
    	//This sets up a table to add the buttons to
    	Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        //Creates buttons
        TextButton tutorialButton = new TextButton("", skinT);
        TextButton challengesButton = new TextButton("", skinC);
        TextButton sandboxButton = new TextButton("", skinS);
        TextButton backButton = new TextButton("", skinB);


        //Click listeners for each of the buttons
        tutorialButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {	            	
            	challengeScreen.setTutorials(true);
            	challengeScreen.setChallenges(false);
            	VerilogWorldMain.getVerilogWorldMain().setChallengesScreen();	            	
            }
        });
        
        challengesButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {	 
            	challengeScreen.setTutorials(false);
            	challengeScreen.setChallenges(true);
            	VerilogWorldMain.getVerilogWorldMain().setChallengesScreen();	            	
            }
        });
        
        sandboxButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(new Level());
            }
        });
        
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	VerilogWorldMain.getVerilogWorldMain().setMainMenuScreen();
            }
        });

        buttonHeight = Gdx.graphics.getHeight()/6;
        buttonWidth = viewport.getScreenWidth() - viewport.getScreenWidth()/4;
        
        //Add buttons to table
        mainTable.add(tutorialButton).height(buttonHeight).width((5*buttonWidth)/6);
        mainTable.row();
        mainTable.add(challengesButton).height(buttonHeight).width(buttonWidth);
        mainTable.row();
        mainTable.add(sandboxButton).height(buttonHeight).width((7*buttonWidth)/8);
        mainTable.row();
        mainTable.add(backButton).height(buttonHeight).width(buttonWidth/3);
        
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
    	//stage.setViewport(new FitViewport(width, height, camera));
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
        skinT.dispose();
        skinC.dispose();
        skinS.dispose();
        skinB.dispose();
        stage.dispose();
    }
    
    public void buttonStyles(){
    	skinT.add("default", font);
    	skinC.add("default", font);
    	skinS.add("default", font);
    	skinB.add("default", font);

		
    	//adds an image texture to the skin of each button
		skinT.add("textColor", new Texture(getImagePath() + "/tutorials.png"));
		skinC.add("textColor", new Texture(getImagePath() + "/challenges.png"));
		skinS.add("textColor", new Texture(getImagePath() + "/sandbox.png"));
		skinB.add("textColor", new Texture(getImagePath() + "/back.png"));

    	
    	//This sets up a style for each button
		TextButtonStyle buttonStyleT = new TextButtonStyle();
		buttonStyleT.up = skinT.newDrawable("textColor", Color.WHITE);
		buttonStyleT.down = skinT.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleT.checked = skinT.newDrawable("textColor", Color.WHITE);
		buttonStyleT.over = skinT.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleT.font = skinT.getFont("default");
		skinT.add("default", buttonStyleT);
		
		TextButtonStyle buttonStyleC = new TextButtonStyle();
		buttonStyleC.up = skinC.newDrawable("textColor", Color.WHITE);
		buttonStyleC.down = skinC.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleC.checked = skinC.newDrawable("textColor", Color.WHITE);
		buttonStyleC.over = skinC.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleC.font = skinC.getFont("default");
		skinC.add("default", buttonStyleC);
		
		TextButtonStyle buttonStyleS = new TextButtonStyle();
		buttonStyleS.up = skinS.newDrawable("textColor", Color.WHITE);
		buttonStyleS.down = skinS.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleS.checked = skinS.newDrawable("textColor", Color.WHITE);
		buttonStyleS.over = skinS.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleS.font = skinS.getFont("default");
		skinS.add("default", buttonStyleS);
		
		TextButtonStyle buttonStyleB = new TextButtonStyle();
		buttonStyleB.up = skinB.newDrawable("textColor", Color.WHITE);
		buttonStyleB.down = skinB.newDrawable("textColor", Color.DARK_GRAY);
		buttonStyleB.checked = skinB.newDrawable("textColor", Color.WHITE);
		buttonStyleB.over = skinB.newDrawable("textColor", Color.LIGHT_GRAY);
		buttonStyleB.font = skinB.getFont("default");
		skinB.add("default", buttonStyleB);
    }

	public String getImagePath() {
		String filePath = System.getProperty("user.dir") + "/assets/images";
    	return filePath;
	}
}
