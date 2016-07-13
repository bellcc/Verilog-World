package edu.miamioh.worldEditor.Stages;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import edu.miamioh.Buttons.CheckBoxActor;
import edu.miamioh.Buttons.LabelActor;
import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;

public class ConnectionStage {
	
	private ArrayList<CheckBox> selectedGroup;
	private ArrayList<CheckBox> targetGroup;
	
	private int actorHeight = 30;
	private int actorWidth = 300;
	
	public Stage createConnectionStage(ArrayList<String> selectedList, ArrayList<String> targetList, NormalBlock selectedBlock, NormalBlock targetBlock) {

		Stage stage = new Stage();
		
		Pixmap pm1 = new Pixmap(1, 1, Format.RGB565);
		pm1.setColor(Color.LIGHT_GRAY);
		pm1.fill();
		
		Table table = new Table();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm1))));
		table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.center();
		
		TableElement selected = constructTable(selectedGroup, selectedList, "Selected Block");
		selectedGroup = selected.getGroup();
		
		table.add(selected.getTable());
		
		TableElement target = constructTable(targetGroup, targetList, "Target Block");
		targetGroup = target.getGroup();
		
		table.add(target.getTable());
		
		TextButton backButton = new TextButtonActor().createTextButton(Color.RED, "Back");
		TextButton nextButton = new TextButtonActor().createTextButton(Color.GREEN, "Next");
		
		
		backButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {

				System.out.println("Back Button Change Listener");
				
				WorldEditorScreen.getScreen().setConnectModeWire(false);
				WorldEditorScreen.getScreen().setConnectMode(false);
				WorldEditorController.getCurrentController().updateInputMultiplexer();
				
				Level level = VerilogWorldController.getController().getCurrentLevel();
				VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);
			}
			
		});
		
		nextButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {

				System.out.println("Next Change Listener");

				int selectedCount = 0;
				for(int i=0;i<selectedGroup.size();i++) {
					if(selectedGroup.get(i).isChecked()) {
						selectedCount ++;
					}
				}
				
				int targetCount = 0;
				for(int i=0;i<targetGroup.size();i++) {
					if(targetGroup.get(i).isChecked()) {
						targetCount ++;
					}
				}
								
				if(selectedCount != 1 || targetCount != 1) {
					System.out.println("I am here.");
					JOptionPane.showMessageDialog(new JFrame(),
						    "You must select an option but not more than one from each group (Selected and Target).",
						    null, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				String selectedWireName = "";
				String targetWireName = "";

				for(int i=0;i<selectedGroup.size();i++) {
					if(selectedGroup.get(i).isChecked()) {
						selectedWireName = selectedGroup.get(i).getName();
					}
				}
				
				for(int i=0;i<targetGroup.size();i++) {
					if(targetGroup.get(i).isChecked()) {
						targetWireName = targetGroup.get(i).getName();
					}
				}
				
				WorldEditorScreen.getScreen().setConnectModeWire(false);
				WorldEditorScreen.getScreen().setConnectMode(false);
				WorldEditorController.getCurrentController().updateInputMultiplexer();
				
				System.out.println("Selected Wire Name : " + selectedWireName);
				System.out.println("Target Wire Name   : " + targetWireName);
				System.out.println("Selected Block     : " + selectedBlock);
				System.out.println("Target Block       : " + targetBlock);
				
				try {
					WorldEditorController.getCurrentController().connectBlocks(selectedBlock, targetBlock, selectedWireName, targetWireName);
				}catch (StackOverflowError e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		table.row();
		table.add(backButton).width(actorWidth/2).height(actorHeight).colspan(2);
		table.row();
		table.add(nextButton).width(actorWidth/2).height(actorHeight).colspan(2);
		
		stage.addActor(table);
		
		return stage;
	}
	
	private TableElement constructTable(ArrayList<CheckBox> group, ArrayList<String> list, String title) {
		
		group = new ArrayList<CheckBox>();

		Table table = new Table();

		table.add(new LabelActor().createLabel(title, Color.CLEAR, Color.WHITE)).colspan(2);
		table.row();
		
		for(int i=0;i<list.size();i++) {
			CheckBox actor = new CheckBoxActor().createCheckBox("  " + list.get(i));
			actor.setName(list.get(i));
			group.add(actor);
		}
		
		for(int i=0;i<list.size();i++) {			
			table.add(group.get(i)).width(actorWidth).height(actorHeight);
			table.row();
		}
		
		TableElement tableElement = new TableElement(table, group);
		
		return tableElement;
		
	}
	
	private class TableElement {
		
		private Table table;
		private ArrayList<CheckBox> group;
		
		public TableElement(Table table, ArrayList<CheckBox> group) {
			this.setTable(table);
			this.setGroup(group);
		}

		public Table getTable() {
			return table;
		}

		public void setTable(Table table) {
			this.table = table;
		}

		public ArrayList<CheckBox> getGroup() {
			return group;
		}

		public void setGroup(ArrayList<CheckBox> group) {
			this.group = group;
		}
	}
	
}
