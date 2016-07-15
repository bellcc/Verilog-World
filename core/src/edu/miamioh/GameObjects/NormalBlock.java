package edu.miamioh.GameObjects;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import edu.miamioh.GameObjects.blocks.BlankBlock;
import edu.miamioh.GameObjects.blocks.ControllerBlock;
import edu.miamioh.GameObjects.blocks.LedBlock;
import edu.miamioh.GameObjects.blocks.ScooterBlock;
import edu.miamioh.GameObjects.blocks.WallBlock;
import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.RootModuleInstance;
import edu.miamioh.util.FileTools;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.ModulePort;
import edu.miamioh.worldSimulator.ModuleWrapper;

public abstract class NormalBlock extends Block {
	
	@Override
	public String toString() {
		return "NormalBlock [sourceFile=" + sourceFile + ", module=" + module + ", type=" + type + "]";
	}
	private static Parse compiler;
	
	private String sourceFile;
	private ModuleWrapper module;
	private NormalBlockType type;
	
	public NormalBlock(NormalBlockType type, int row, int column, int id) {
			
		this.setID(id);
		
		if(compiler == null) {
			compiler = VerilogWorldController.getController().getSim().getCompiler();
		}

		setRow(row);
		setColumn(column);
		
		this.type = type;
		this.sourceFile = null;
		
		// Set color according to type
		switch(type) {
		
			case Blank:
				setColor(BlankBlock.COLOR);
				break;
			case Wall:
				setColor(WallBlock.COLOR);
				break;
			case Controller:
				setColor(ControllerBlock.COLOR);
				break;
			case Scooter:
				setColor(ScooterBlock.COLOR);
				break;
			case Led:
				setColor(LedBlock.COLOR);
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
			ParseRegWire wire = module.getModule().getHash_vars().get("clk");
			module.addPort(new ModulePort(this, "clk", clock, wire, true));
		}
		
		if(ports.contains("rst")) {
			ParseRegWire wire = module.getModule().getHash_vars().get("rst");
			ModulePort resetPort = new ModulePort(this, "rst", reset, wire, true);
			resetPort.setValue(1); // Active-low
			module.addPort(resetPort);
		}
	}
	
	public static int count = 0;
	public void updateOutputs() {
		
		for(ModulePort port : module.getPortsList()) {
		
			if (!port.getIsInput()) { 
				// Get the module wire corresponding to the port
				//ParseRegWire wire = port.getWire();
				ParseRegWire wire = module.getModule().getHash_vars().get(port.getWire().getName());
				
				// If the output value is changed, notify the target block that it must recalculate itself
				boolean shouldSimTargetBlock = port.getValue() != wire.getValue(0) ? true : false;
				
				if(this.getType() == NormalBlockType.Controller) {
					System.out.println(" > " + wire.getValue(0));
				}
				
				// Do the actual setting
				port.setValue(wire.getValue(0));
				
				if(shouldSimTargetBlock) {
					//VerilogWorldController.getController().getSim().resimBlock(port.getTargetPort().getBlock());
				}
			}
		}
	}
	
	public void updateInputs() {
		
		for(ModulePort port : module.getPortsList()) {
			
			if (port.getIsInput()) { 
				// Get the module wire corresponding to the port
				ParseRegWire wire = module.getModule().getHash_vars().get(port.getName());
				
				wire.setValue(0, port.getValue(), false);
				wire.setValue(1, port.getValue(), false);
			}
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
		
		if (this.module == null) {
			this.module = new ModuleWrapper(newModule);
		}
		else {
			this.module.setRootModuleInstance(newModule);
		}
		
		return this.module;
	}
	
	public void makeUniqueFile() {
				
		String projectPath = WorldEditorController.getCurrentController().getCurrentLevel().getProject().getPath();
		
		String template = type.toString() + ".v";
		String pathToTemplate = new File("").getAbsolutePath() + "/assets/modules/templates/" + template;
		File templateFile = new File(pathToTemplate);
		
		String uniqueName = "module" + this.getID() + ".v";
		String pathToUnique = projectPath + "/modules/" + uniqueName;
		File uniqueFile = new File(pathToUnique);
		
		if(!uniqueFile.exists()) {
			FileTools.copyFile(templateFile, uniqueFile);
		}
		
		this.sourceFile = uniqueName;
	}
	
	public void setType(NormalBlockType type) 	{this.type = type;}
	public NormalBlockType getType()            {return type;}
	public String getSourceFile() 				{return this.sourceFile;}
	public ModuleWrapper getModuleWrapper() 	{return this.module;}
}
