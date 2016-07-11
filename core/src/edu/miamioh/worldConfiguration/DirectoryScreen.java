package edu.miamioh.worldConfiguration;

import java.io.File;

import javax.swing.JFileChooser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import edu.miamioh.Buttons.LabelActor;
import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.Buttons.TextFieldActor;
import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class DirectoryScreen implements Screen {
	
	private static DirectoryScreen screen;
	
	private File directoryPath;
	private String projectName;

	private TextButton directoryButton;
	private TextButton backButton;
	private TextButton nextButton;
	
	private TextField directoryTextField;
	private TextField directoryNameTextField;
	
	private Label directoryLabel;
	private Label directoryNameLabel;
	
	private Stage stage;
	
	@Override
	public void show() {
		
		screen = this;
		
		stage = new Stage();
		
		directoryButton = new TextButtonActor().createTextButton(Color.LIGHT_GRAY, "SELECT");
		backButton = new TextButtonActor().createTextButton(Color.LIGHT_GRAY, "BACK");
		nextButton = new TextButtonActor().createTextButton(Color.LIGHT_GRAY, "NEXT");
		
		directoryTextField = new TextFieldActor().createTextField();
		directoryNameTextField = new TextFieldActor().createTextField();
		
		directoryLabel = new LabelActor().createLabel("Lab Directory: ", Color.LIGHT_GRAY, Color.BLACK);
		directoryLabel.setAlignment(0);
		directoryNameLabel = new LabelActor().createLabel("Project Name: " , Color.LIGHT_GRAY, Color.BLACK);
		directoryNameLabel.setAlignment(0);
		
		directoryButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {

				System.out.println("Directory Change Listener");
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select Directory Location");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				chooser.setAcceptAllFileFilterUsed(false);
				
				if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
					DirectoryScreen.getScreen().setDirectoryPath(chooser.getSelectedFile());
					DirectoryScreen.getScreen().setDirectoryTextField(chooser.getSelectedFile().getAbsolutePath());				
				}
				
			}
			
		});
		
		backButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {

				System.out.println("Back Change Listener");
				
				VerilogWorldMain.getVerilogWorldMain().setPlayScreen();
			}
			
		});
		
		nextButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {

				System.out.println("Next Change Listener");
				
				File file = DirectoryScreen.getScreen().getDirectoryPath();
				
				if(file == null || DirectoryScreen.getScreen().retrieveProjectName().equals(null)) {
					return;
				}
				
				String projectName = DirectoryScreen.getScreen().retrieveProjectName();
				DirectoryScreen.getScreen().setProjectName(projectName);
				
				File tempFile = new File(file.getPath() + "/" + projectName + "/modules/");
				tempFile.mkdirs();
				
				ConfigurationParser parser = new ConfigurationParser();
				parser.createWorld(new Level(), new File(file.getPath() + "/" + projectName + "/world.xml"));
				
				Level level = new Level();
				level.setProject(new File(file.getPath() + "/" + projectName));
				
            	//VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);
				VerilogWorldMain.getVerilogWorldMain().setConfigurationScreen(level);

			}
			
		});
		
		Table table = new Table();
		table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/circuit_2.png")))));
		table.center();

		table.add(directoryLabel).width(100).height(50);
		table.add(directoryTextField).width(300).height(50);
		table.add(directoryButton).width(75).height(50);	
		table.row();
		table.add(directoryNameLabel).width(100).height(50);
		table.add(directoryNameTextField).width(300).height(50);
		table.row();
		
		table.add(backButton).width(100).height(50).colspan(3);
		table.row();
		table.add(nextButton).width(100).height(50).colspan(3);
		
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
		
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
	
	public static DirectoryScreen getScreen() {
		return screen;
	}
	
	public void setDirectoryPath(File path) {
		this.directoryPath = path;
	}
	
	public File getDirectoryPath() {
		return this.directoryPath;
	}
	
	public void setDirectoryTextField(String text) {
		directoryTextField.setText(text);
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	
	private String retrieveProjectName() {
		return directoryNameTextField.getText();
	}

}
