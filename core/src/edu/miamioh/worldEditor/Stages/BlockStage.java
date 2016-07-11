package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.GameObjects.blocks.BlankBlock;
import edu.miamioh.GameObjects.blocks.ControllerBlock;
import edu.miamioh.GameObjects.blocks.LedBlock;
import edu.miamioh.GameObjects.blocks.ScooterBlock;
import edu.miamioh.GameObjects.blocks.WallBlock;
import edu.miamioh.worldEditor.ChangeListeners.BlankBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ControllerBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.LedBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ScooterBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.WallBlockChangeListener;

public class BlockStage {

	private Stage stage;
	private Table table;
	
	private int actorHeight = 50;
	private int actorWidth = 100;
	
	public BlockStage() {
		
		stage = new Stage();
		table = new Table();
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
				
		table.setSize(actorWidth, windowHeight);
		table.setPosition(50, 0);
		table.right().top();
		
		addButton(BlankBlock.COLOR, "BLANK", new BlankBlockChangeListener());
		addButton(WallBlock.COLOR, "WALL", new WallBlockChangeListener());
		addButton(ControllerBlock.COLOR, "CONTROLLER", new ControllerBlockChangeListener());
		addButton(ScooterBlock.COLOR, "SCOOTER", new ScooterBlockChangeListener());
		addButton(LedBlock.COLOR, "LED", new LedBlockChangeListener());
		
		stage.addActor(table);
	}
	
	private void addButton(Color color, String type, ChangeListener listener) {
		
		Actor actor = new TextButtonActor().createTextButton(color, type + "\nBLOCK");
		
		actor.addListener(listener);
		
		table.row();
		table.add(actor).width(actorWidth).height(actorHeight);
	}
	
	public Stage getStage() {
		return stage;
	}
}
