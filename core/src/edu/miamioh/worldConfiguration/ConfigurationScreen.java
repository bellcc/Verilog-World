package edu.miamioh.worldConfiguration;

import edu.miamioh.Buttons.LabelActor;
import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.Buttons.TextFieldActor;
import edu.miamioh.Configuration.Configuration;

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
	
	private Label windowWidthLabel;
	private Label windowHeightLabel;
	
	private Label gridWidthLabel;
	private Label gridHeightLabel;
	
	private Label bufferWidthLabel;
	private Label bufferHeightLabel;
	
	private Label stepWidthLabel;
	private Label stepHeightLabel;
	
	private TextField worldWidthTextField;
	private TextField worldHeightTextField;
	
	private TextField windowWidthTextField;
	private TextField windowHeightTextField;
	
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
	
	private int windowWidth;
	private int windowHeight;
	
	private int gridWidth;
	private int gridHeight;
	
	private int bufferWidth;
	private int bufferHeight;
	
	private int stepWidth;
	private int stepHeight;
	
	@Override
	public void show() {
		
		currentScreen = this;

		updateWorld(ConfigurationController.getController().getDefaultConfig());
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

		windowWidthLabel = new LabelActor().createLabel("Window Width", Color.LIGHT_GRAY, Color.BLACK);
		windowHeightLabel = new LabelActor().createLabel("Window Height", Color.LIGHT_GRAY, Color.BLACK);
		
		worldWidthLabel = new LabelActor().createLabel("World Width", Color.LIGHT_GRAY, Color.BLACK);
		worldHeightLabel = new LabelActor().createLabel("World Height", Color.LIGHT_GRAY, Color.BLACK);
		
		gridWidthLabel = new LabelActor().createLabel("Grid Width", Color.LIGHT_GRAY, Color.BLACK);
		gridHeightLabel = new LabelActor().createLabel("Grid Height", Color.LIGHT_GRAY, Color.BLACK);

		bufferWidthLabel = new LabelActor().createLabel("Buffer Width", Color.LIGHT_GRAY, Color.BLACK);
		bufferHeightLabel = new LabelActor().createLabel("Buffer Height", Color.LIGHT_GRAY, Color.BLACK);

		stepWidthLabel = new LabelActor().createLabel("Step Width", Color.LIGHT_GRAY, Color.BLACK);
		stepHeightLabel = new LabelActor().createLabel("Step Height", Color.LIGHT_GRAY, Color.BLACK);

		windowWidthLabel.setAlignment(0);
		windowHeightLabel.setAlignment(0);
		
		worldWidthLabel.setAlignment(0);
		worldHeightLabel.setAlignment(0);
		
		gridWidthLabel.setAlignment(0);
		gridHeightLabel.setAlignment(0);
		
		bufferWidthLabel.setAlignment(0);
		bufferHeightLabel.setAlignment(0);
		
		stepWidthLabel.setAlignment(0);
		stepHeightLabel.setAlignment(0);
		
		windowWidthTextField = new TextFieldActor().createTextField();
		windowHeightTextField = new TextFieldActor().createTextField();
		
		worldWidthTextField = new TextFieldActor().createTextField();
		worldHeightTextField = new TextFieldActor().createTextField();
		
		windowWidthTextField = new TextFieldActor().createTextField();
		windowHeightTextField = new TextFieldActor().createTextField();
		
		gridWidthTextField = new TextFieldActor().createTextField();
		gridHeightTextField = new TextFieldActor().createTextField();
		
		stepWidthTextField = new TextFieldActor().createTextField();
		stepHeightTextField = new TextFieldActor().createTextField();
		
		bufferWidthTextField = new TextFieldActor().createTextField();
		bufferHeightTextField = new TextFieldActor().createTextField();

		windowWidthTextField.setText(String.valueOf(this.windowWidth));
		windowHeightTextField.setText(String.valueOf(this.windowHeight));
		
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
		
		table.add(windowWidthLabel).width(150).height(50);
		table.add(windowWidthTextField).width(150).height(40);
		table.row();
		table.add(windowHeightLabel).width(150).height(50);
		table.add(windowHeightTextField).width(150).height(40);
		table.row();
		
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
		
		this.windowWidth = config.getWindowWidth();
		this.windowHeight = config.getWindowHeight();
		
		this.gridWidth = config.getGridWidth();
		this.gridHeight = config.getGridHeight();
		
		this.bufferWidth = config.getBufferWidth();
		this.bufferHeight = config.getBufferHeight();
		
		this.stepWidth = config.getStepWidth();
		this.stepHeight = config.getStepHeight();
		
	}
	
	public Configuration getConfiguration() {
		
		Configuration config = new Configuration();
		
		config.setWindowWidth(Integer.parseInt(windowWidthTextField.getText()));
		config.setWindowHeight(Integer.parseInt(windowHeightTextField.getText()));
		
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
	
	public Stage getStage() {
		return stage;
	}
	
	public static ConfigurationScreen getCurrentScreen() {
		return currentScreen;
	}
	
}
