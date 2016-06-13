package edu.miamioh.PictureButton;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public abstract class AbstractPictureButtonActor {
	public TextButton button;
	public String buttonText;
	public Color color;

	public abstract void create();
	public abstract TextButton getButtonActor();
	public abstract void init();
	public abstract void createStage();
	public abstract Stage getStage();
}
