package edu.miamioh.GameObjects;

import java.io.File;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleInstance;
import edu.miamioh.util.FileTools;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.ModuleWrapper;

public class NormalBlock extends Block {
	
	private static Parse compiler;
	
	private String sourceFile;
	private ModuleWrapper module;
	private NormalBlockType type;
	
	public NormalBlock(NormalBlockType type, int row, int column) {
		
		if(compiler == null) {
			compiler = VerilogWorldController.getController().getSim().getCompiler();
		}

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
		
		makeUniqueFile();
	}
	

	
	public ModuleWrapper compile() {
		
		RootModuleInstance newModule = null;
		
		try {
			newModule = compiler.compileFileForGame(sourceFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.module = new ModuleWrapper(newModule);
		
		return this.module;
	}
	
	public void makeUniqueFile() {
		
		String modulePath = VerilogWorldController.getController().getRootPath() + "core/assets/modules/";
//		String modulePath = "/home/pheonix/GitHub/Verilog-World/core/assets/modules/";
		
		String template = type.toString() + ".v";
		String pathToTemplate = modulePath + "templates/" + template;
		File templateFile = new File(pathToTemplate);
		
		String uniqueName = "module_" + WorldEditorController.getCurrentController().getUniqueBlockID() + ".v";
		String pathToUnique = modulePath + uniqueName;
		File uniqueFile = new File(pathToUnique);
		
		FileTools.copyFile(templateFile, uniqueFile);
		
		this.sourceFile = uniqueName;
	}
	
	public void setType(NormalBlockType type) 	{this.type = type;}
	public String getSourceFile() 				{return this.sourceFile;}
	public ModuleWrapper getModuleWrapper() 	{return this.module;}
}
