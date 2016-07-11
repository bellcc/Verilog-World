
/**
 * @author Clark Bell
 * @date   7-11-2016
 * @info   The configuration screen that defines all 
 *         of the world parameter variables.
 */

package edu.miamioh.worldConfiguration;

import edu.miamioh.Buttons.LabelActor;
import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.Buttons.TextFieldActor;
import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Level.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ConfigurationScreen implements Screen {
	
	private static ConfigurationScreen currentScreen;

	private Stage stage;
	
	private Label worldWidthLabel;
	private Label worldHeightLabel;

	private Label gridWidthLabel;
	private Label gridHeightLabel;
	
	private Label bufferWidthLabel;
	private Label bufferHeightLabel;
	
	private Label stepWidthLabel;
	private Label stepHeightLabel;
	
	private TextField worldWidthTextField;
	private TextField worldHeightTextField;

	private TextField gridWidthTextField;
	private TextField gridHeightTextField;
	
	private TextField stepWidthTextField;
	private TextField stepHeightTextField;
	
	private TextField bufferWidthTextField;
	private TextField bufferHeightTextField;
	
	private TextButton backButton;
	private TextButton nextButton;
	
	private int worldWidth;
	private int worldHeight;

	private int gridWidth;
	private int gridHeight;
	
	private int bufferWidth;
	private int bufferHeight;
	
	private int stepWidth;
	private int stepHeight;
	
	private Level newLevel;
	
	public ConfigurationScreen(Level level) {
		
		updateWorld(level.getConfig());
		this.newLevel = level;
	}
	
	@Override
	public void show() {
		
		currentScreen = this;
		constructStage();
	}
	
	@Override
	public void render(float arg0) {
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.stage.act(Gdx.graphics.getDeltaTime());
		this.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		constructStage();
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	private void constructStage() {
		
		this.stage = new Stage();

		worldWidthLabel = new LabelActor().createLabel("World Width", Color.LIGHT_GRAY, Color.BLACK);
		worldHeightLabel = new LabelActor().createLabel("World Height", Color.LIGHT_GRAY, Color.BLACK);
		
		gridWidthLabel = new LabelActor().createLabel("Grid Width", Color.LIGHT_GRAY, Color.BLACK);
		gridHeightLabel = new LabelActor().createLabel("Grid Height", Color.LIGHT_GRAY, Color.BLACK);

		bufferWidthLabel = new LabelActor().createLabel("Buffer Width", Color.LIGHT_GRAY, Color.BLACK);
		bufferHeightLabel = new LabelActor().createLabel("Buffer Height", Color.LIGHT_GRAY, Color.BLACK);

		stepWidthLabel = new LabelActor().createLabel("Step Width", Color.LIGHT_GRAY, Color.BLACK);
		stepHeightLabel = new LabelActor().createLabel("Step Height", Color.LIGHT_GRAY, Color.BLACK);

		worldWidthLabel.setAlignment(0);
		worldHeightLabel.setAlignment(0);
		
		gridWidthLabel.setAlignment(0);
		gridHeightLabel.setAlignment(0);
		
		bufferWidthLabel.setAlignment(0);
		bufferHeightLabel.setAlignment(0);
		
		stepWidthLabel.setAlignment(0);
		stepHeightLabel.setAlignment(0);

		worldWidthTextField = new TextFieldActor().createTextField();
		worldHeightTextField = new TextFieldActor().createTextField();

		gridWidthTextField = new TextFieldActor().createTextField();
		gridHeightTextField = new TextFieldActor().createTextField();
		
		stepWidthTextField = new TextFieldActor().createTextField();
		stepHeightTextField = new TextFieldActor().createTextField();
		
		bufferWidthTextField = new TextFieldActor().createTextField();
		bufferHeightTextField = new TextFieldActor().createTextField();

		worldWidthTextField.setText(String.valueOf(this.worldWidth));
		worldHeightTextField.setText(String.valueOf(this.worldHeight));
		
		gridWidthTextField.setText(String.valueOf(this.gridWidth));
		gridHeightTextField.setText(String.valueOf(this.gridHeight));
		
		bufferWidthTextField.setText(String.valueOf(this.bufferWidth));
		bufferHeightTextField.setText(String.valueOf(this.bufferHeight));
		
		stepWidthTextField.setText(String.valueOf(this.stepWidth));
		stepHeightTextField.setText(String.valueOf(this.stepHeight));
		
		backButton = new TextButtonActor().createTextButton(Color.RED, "BACK");
		nextButton = new TextButtonActor().createTextButton(Color.BLUE, "NEXT");
		
		backButton.addListener(new BackChangeListener());
		nextButton.addListener(new NextChangeListener());
		
		Table table = new Table();
		table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/circuit_2.png")))));
		table.center();

		table.add(worldWidthLabel).width(150).height(50);
		table.add(worldWidthTextField).width(150).height(40);
		table.row();
		table.add(worldHeightLabel).width(150).height(50);
		table.add(worldHeightTextField).width(150).height(40);
		table.row();
		
		table.add(gridWidthLabel).width(150).height(50);
		table.add(gridWidthTextField).width(150).height(40);
		table.row();
		table.add(gridHeightLabel).width(150).height(50);
		table.add(gridHeightTextField).width(150).height(40);
		table.row();
		
		table.add(bufferWidthLabel).width(150).height(50);
		table.add(bufferWidthTextField).width(150).height(40);
		table.row();
		table.add(bufferHeightLabel).width(150).height(50);
		table.add(bufferHeightTextField).width(150).height(40);
		table.row();
		
		table.add(stepWidthLabel).width(150).height(50);
		table.add(stepWidthTextField).width(150).height(40);
		table.row();
		table.add(stepHeightLabel).width(150).height(50);
		table.add(stepHeightTextField).width(150).height(40);
		table.row();
		
		table.add(backButton).width(150).height(50);
		table.add(nextButton).width(150).height(50);
		table.row();
		
		this.stage.addActor(table);	

		Gdx.input.setInputProcessor(stage);
	}
	
	public void updateWorld(Configuration config) {

		this.worldWidth = config.getWorldWidth();
		this.worldHeight = config.getWorldHeight();

		this.gridWidth = config.getGridWidth();
		this.gridHeight = config.getGridHeight();
		
		this.bufferWidth = config.getBufferWidth();
		this.bufferHeight = config.getBufferHeight();
		
		this.stepWidth = config.getStepWidth();
		this.stepHeight = config.getStepHeight();
		
	}
	
	private Configuration getConfiguration() {

		Configuration config = new Configuration();

		config.setWorldWidth(Integer.parseInt(worldWidthTextField.getText()));
		config.setWorldHeight(Integer.parseInt(worldHeightTextField.getText()));
		
		config.setGridWidth(Integer.parseInt(gridWidthTextField.getText()));
		config.setGridHeight(Integer.parseInt(gridHeightTextField.getText()));
		
		config.setBufferWidth(Integer.parseInt(bufferWidthTextField.getText()));
		config.setBufferHeight(Integer.parseInt(bufferHeightTextField.getText()));
		
		config.setStepWidth(Integer.parseInt(stepWidthTextField.getText()));
		config.setStepHeight(Integer.parseInt(stepHeightTextField.getText()));
		
		return config;
	}
	
	public Level getLevel() {
		
		Configuration config = getConfiguration();
		newLevel.setConfig(config);
		
		return this.newLevel;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public static ConfigurationScreen getCurrentScreen() {
		return currentScreen;
	}
	
}
