/**
 * @author Zachary McQuigg
 * @date   06-15-2016
 * @info   
 */

package edu.miamioh.Screens;

import com.badlogic.gdx.Game;

public class StartScreen extends Game{
	private MainMenuScreen mainMenuScreen;

	
	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen(this);	
		this.setScreen(mainMenuScreen);
	}
	
	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		mainMenuScreen.dispose();
	}

}
