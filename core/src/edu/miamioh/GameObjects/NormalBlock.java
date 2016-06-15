package edu.miamioh.GameObjects;

import java.io.File;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.simulator.RootModuleInstance;
import edu.miamioh.util.FileTools;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;

public class NormalBlock extends Block {
	
	private String sourceFile;
	private RootModuleInstance module;
	private NormalBlockType type;
	
	public NormalBlock(NormalBlockType type, int row, int column) {

		setRow(row);
		setColumn(column);
		
		this.type = type;
		this.sourceFile = null;
		
		// Set color according to type
		switch(type) {
		case Wall:
			setColor(Color.GRAY);
			break;
		case Scooter:
			setColor(Color.BLUE);
			break;
		default:
			setColor(Color.BLACK);
		}
	}
	
	public void compile() {
		System.out.println(sourceFile);
	}
	
	public void makeUniqueFile() {
		
		String modulePath = VerilogWorldController.getRootPath() + "core/assets/modules/";
		
		String template = type.toString() + ".v";
		String pathToTemplate = modulePath + "templates/" + template;
		File templateFile = new File(pathToTemplate);
		
		String uniqueName = "module_" + WorldEditorController.getCurrentWorldController().getUniqueBlockID() + ".v";
		String pathToUnique = modulePath + uniqueName;
		File uniqueFile = new File(pathToUnique);
		
		FileTools.copyFile(templateFile, uniqueFile);
		
		this.sourceFile = uniqueName;
	}
	
	public void setType(NormalBlockType type) {
		this.type = type;
	}
	
	public String getSourceFile() {
		return this.sourceFile;
	}
}
