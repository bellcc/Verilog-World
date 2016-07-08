package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ChangeListeners.ConnectChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.RemoveChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.SchematicChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.VerilogEditorChangeListener;

public class BlockSelectedStage {

	private Stage stage;
	
	private Actor verilogEditorActor;
	private Actor schematicActor;
	private Actor connectActor;
	private Actor removeActor;
	
	public BlockSelectedStage() {
				
		stage = new Stage();
	
		verilogEditorActor = new TextButtonActor().createTextButton(Color.RED, "VERILOG\nEDITOR");
		schematicActor = new TextButtonActor().createTextButton(Color.ORANGE, "SCHEMATIC");
		connectActor = new TextButtonActor().createTextButton(Color.GREEN, "CONNECT\nBLOCKS");
		removeActor = new TextButtonActor().createTextButton(Color.PINK, "REMOVE");
		
		verilogEditorActor.addListener(new VerilogEditorChangeListener());
		schematicActor.addListener(new SchematicChangeListener());
		connectActor.addListener(new ConnectChangeListener());
		removeActor.addListener(new RemoveChangeListener());
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
		
		int actorHeight = 50;
		int actorWidth = 100;
	
		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.setPosition(50, 0);
		table.right().top();
		
		table.add(verilogEditorActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(schematicActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(connectActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(removeActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
				
	}
	
	public Stage getStage() {
		return stage;
	}
}
