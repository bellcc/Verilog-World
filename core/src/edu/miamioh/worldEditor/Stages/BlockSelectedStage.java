package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.ChangeListeners.ConnectChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.RemoveChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.SchematicChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.VerilogEditorChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ViewConnectionsChangeListener;

public class BlockSelectedStage {

	private Stage stage;
	
	private Actor verilogEditorActor;
	private Actor schematicActor;
	private Actor connectActor;
	private Actor viewConnectionsActor;
	private Actor removeActor;
	
	public BlockSelectedStage() {
				
		stage = new Stage();
	
		verilogEditorActor = new TextButtonActor().createTextButton(Color.RED, "VERILOG\nEDITOR");
		schematicActor = new TextButtonActor().createTextButton(Color.ORANGE, "SCHEMATIC");
		connectActor = new TextButtonActor().createTextButton(Color.GREEN, "CONNECT\nBLOCKS");
		viewConnectionsActor = new TextButtonActor().createTextButton(Color.YELLOW, "VIEW\nCONNECTIONS");
		removeActor = new TextButtonActor().createTextButton(Color.PINK, "REMOVE");
		
		verilogEditorActor.addListener(new VerilogEditorChangeListener());
		schematicActor.addListener(new SchematicChangeListener());
		connectActor.addListener(new ConnectChangeListener());
		viewConnectionsActor.addListener(new ViewConnectionsChangeListener());
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
		table.add(viewConnectionsActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(removeActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
				
	}
	
	public Stage getStage() {
		return stage;
	}
}
