/**
 * @author Zachary McQuigg
 * @date   06-15-2016
 * @info   
 */

package edu.miamioh.MainMenu;

 
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.miamioh.verilogWorld.VerilogWorldMain;
 
public class MainMenuScreen implements Screen {
	private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    protected Skin skin;
    private Game g;
    
    public MainMenuScreen(Game g)
    {
    	this.g = g;
    	BitmapFont font = new BitmapFont();
    	skin = new Skin();
    	skin.add("default", font);
    	
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
			
		skin.add("background", new Texture(pixmap));
		
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("background", Color.ORANGE);
		buttonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
		buttonStyle.checked = skin.newDrawable("background", Color.ORANGE);
		buttonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
		buttonStyle.font = skin.getFont("default");
		skin.add("default", buttonStyle);
		
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
	        //Stage should controll input:
        Gdx.input.setInputProcessor(stage);
	    }


	    @Override
	    public void show() {
	        //Create Table
	        Table mainTable = new Table();
	        //Set table to fill stage
	        mainTable.setFillParent(true);
	        //Set alignment of contents in the table.
	        mainTable.center();

	        //Create buttons
	        TextButton playButton = new TextButton("Play", skin);
	        TextButton optionsButton = new TextButton("Options", skin);
	        TextButton exitButton = new TextButton("Exit", skin);

	        //Add listeners to buttons
	        playButton.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {	            	
	            	g.setScreen(new VerilogWorldMain(g));
	            }
	        });
	        exitButton.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                Gdx.app.exit();
	            }
	        });

	        //Add buttons to table
	        mainTable.add(playButton);
	        mainTable.row();
	        mainTable.add(optionsButton);
	        mainTable.row();
	        mainTable.add(exitButton);
	        
	        stage.addActor(mainTable);
	    }

	    @Override
	    public void render(float delta) {
	        Gdx.gl.glClearColor(.40f, .40f, .40f, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	        stage.act();
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
	        skin.dispose();
	        stage.dispose();
	    }
}