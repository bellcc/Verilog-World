package edu.miamioh.GameObjects;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.RootModuleInstance;
import edu.miamioh.util.FileTools;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.ModulePort;
import edu.miamioh.worldSimulator.ModuleWrapper;

public abstract class NormalBlock extends Block {
	
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
	
	public void addDefaultPorts(ModulePort clock, ModulePort reset) {
		
		ArrayList<String> ports = module.getModule().getPorts_list();
		
		// Connect module clock and reset inputs to global clock and reset ports
		if(ports.contains("clk")) {
			module.addPort(new ModulePort("clk", clock, true));
		}
		
		if(ports.contains("rst")) {
			module.addPort(new ModulePort("rst", reset, true));
		}
	}
	
	public void updatePortValues() {
		
		for(ModulePort port : module.getPorts()) {
			
			// Get the module wire corresponding to the port
			ParseRegWire wire = module.getModule().getHash_vars().get(port.getName());
			
			wire.setValue(0, port.getValue(), false);
			wire.setValue(1, port.getValue(), false);
		}
	}
	
	public abstract void updateProperties();
	
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
