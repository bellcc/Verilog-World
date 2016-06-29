
package edu.miamioh.worldConfiguration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.Configuration.Configuration;

public class ConfigurationController {

	private static ConfigurationController controller;
	private Configuration defaultConfig;

	public ConfigurationController() {
		controller = this;
	}
	
	public ConfigurationController(Configuration defaultConfig) {
		this();
		this.defaultConfig = defaultConfig;
	}
	
	public void setInputProcess() {
		
		Stage stage = ConfigurationScreen.getCurrentScreen().getStage();
		Gdx.input.setInputProcessor(stage);
	}
	
	public static ConfigurationController getController() {
		return controller;
	}
	
	public Configuration getDefaultConfig(){
		return defaultConfig;
	}
	
}
