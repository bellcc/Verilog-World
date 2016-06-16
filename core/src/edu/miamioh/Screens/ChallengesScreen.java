/**
 * @author Zachary McQuigg
 * @date   06-16-2016
 * @info   
 */


package edu.miamioh.Screens;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.miamioh.verilogWorld.VerilogWorldMain;
 
public class ChallengesScreen implements Screen {
	private SpriteBatch batch;
	private BitmapFont font;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    
    private TextButtonStyle buttonStyleL1;
    private TextButtonStyle buttonStyleL2;
    private TextButtonStyle buttonStyleL3;
    private TextButtonStyle buttonStyleL4;
    private TextButtonStyle buttonStyleL5;
    private TextButtonStyle buttonStyleL6;
    private TextButtonStyle buttonStyleL7;
    private TextButtonStyle buttonStyleL8;
    private TextButtonStyle buttonStyleL9;
    private TextButtonStyle buttonStyleL10;
    private TextButtonStyle buttonStyleB;


    protected Skin skinL1;
    protected Skin skinL2;
    protected Skin skinL3;
    protected Skin skinL4;
    protected Skin skinL5;
    protected Skin skinL6;
    protected Skin skinL7;
    protected Skin skinL8;
    protected Skin skinL9;
    protected Skin skinL10;
    protected Skin skinB;

    private Table mainTable;

    private Game g;
    
    public ChallengesScreen(Game g)
    {
    	this.g = g;
    	font = new BitmapFont();
		
    	skinL1 = new Skin();
    	skinL2 = new Skin();
    	skinL3 = new Skin();
    	skinL4 = new Skin();
    	skinL5 = new Skin();
    	skinL6 = new Skin();
    	skinL7 = new Skin();
    	skinL8 = new Skin();
    	skinL9 = new Skin();
    	skinL10 = new Skin();
    	skinB = new Skin();

    	
    	buttonSetUp();
    	
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
	    }


	    @Override
	    public void show() {
	       
	    	//This sets up a table to add the buttons to
	    	mainTable = new Table();
	        mainTable.setFillParent(true);
	        mainTable.left();

	        //Creates buttons
	        TextButton l1Button = new TextButton("", skinL1);
	        TextButton l2Button = new TextButton("", skinL2);
	        TextButton l3Button = new TextButton("", skinL3);
	        TextButton l4Button = new TextButton("", skinL4);
	        TextButton l5Button = new TextButton("", skinL5);
	        TextButton l6Button = new TextButton("", skinL6);
	        TextButton l7Button = new TextButton("", skinL7);
	        TextButton l8Button = new TextButton("", skinL8);
	        TextButton l9Button = new TextButton("", skinL9);
	        TextButton l10Button = new TextButton("", skinL10);
	        TextButton backButton = new TextButton("", skinB);


	        //Click listeners for each of the buttons
	        l1Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {	            	
	            	System.out.println("level 1 Click Listener");
	            }
	        });
	        
	        l2Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {	 
	            	System.out.println("level 2 Click Listener");
	            }
	        });
	        
	        l3Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 3 Click Listener");
	            }
	        });
	        
	        l4Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 4 Click Listener");
	            }
	        });
	        
	        l5Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 5 Click Listener");
	            }
	        });
	        
	        l6Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 6 Click Listener");
	            }
	        });
	        
	        l7Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 7 Click Listener");
	            }
	        });
	        
	        l8Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 8 Click Listener");
	            }
	        });
	        
	        l9Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 9 Click Listener");
	            }
	        });
	        
	        l10Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	System.out.println("level 10 Click Listener");
	            }
	        });
	        
	        backButton.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	g.setScreen(new PlayScreen(g));
	            }
	        });

	        //Add buttons to table
	        mainTable.add(l1Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l2Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l3Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l4Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l5Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l6Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l7Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l8Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l9Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(l10Button).height(40).width(100);
	        mainTable.row();
	        mainTable.add(backButton).height(40).width(100);
	        
	        stage.addActor(mainTable);
	    }

	    @Override
	    public void render(float delta) {
	        Gdx.gl.glClearColor((float)0/255, (float)0/255, (float)0/255, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	        stage.act();	        
	        stage.draw();
	    }

	    @Override
	    public void resize(int width, int height) {
	    	//stage.setViewport(new StretchViewport(width, height));
	        mainTable.left();
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
	        skinL1.dispose();
	        skinL2.dispose();
	        skinL3.dispose();
	        skinL4.dispose();
	        skinL5.dispose();
	        skinL6.dispose();
	        skinL7.dispose();
	        skinL8.dispose();
	        skinL9.dispose();
	        skinL10.dispose();
	        skinB.dispose();
	        stage.dispose();
	    }
	    
	    public void buttonSetUp(){
	    	buttonStyle(skinL1, buttonStyleL1, "level 1.png");
	    	buttonStyle(skinL2, buttonStyleL2, "level 2.png");
	    	buttonStyle(skinL3, buttonStyleL3, "level 3.png");
	    	buttonStyle(skinL4, buttonStyleL4, "level 4.png");
	    	buttonStyle(skinL5, buttonStyleL5, "level 5.png");
	    	buttonStyle(skinL6, buttonStyleL6, "level 6.png");
	    	buttonStyle(skinL7, buttonStyleL7, "level 7.png");
	    	buttonStyle(skinL8, buttonStyleL8, "level 8.png");
	    	buttonStyle(skinL9, buttonStyleL9, "level 9.png");
	    	buttonStyle(skinL10, buttonStyleL10, "level 10.png");
	    	buttonStyle(skinB, buttonStyleB, "back.png");


	    }
	    
	    public void buttonStyle(Skin skin, TextButtonStyle buttonStyle, String imgName){
	    	
	    	skin.add("default", font);
		
	    	//adds an image texture to the skin of each button
			skin.add("textColor", new Texture(Gdx.files.internal(imgName)));
	    	
	    	//This sets up a style for each button
			buttonStyle = new TextButtonStyle();
			buttonStyle.up = skin.newDrawable("textColor", Color.WHITE);
			buttonStyle.down = skin.newDrawable("textColor", Color.DARK_GRAY);
			buttonStyle.checked = skin.newDrawable("textColor", Color.WHITE);
			buttonStyle.over = skin.newDrawable("textColor", Color.LIGHT_GRAY);
			buttonStyle.font = skin.getFont("default");
			skin.add("default", buttonStyle);
			
	    }
	    
}

