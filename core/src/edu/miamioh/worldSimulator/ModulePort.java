package edu.miamioh.worldSimulator;

public class ModulePort {
	
	private ModulePort target;
	private String name;
	private int value;
	private boolean isInput;
	
	public ModulePort() {
		this("");
	}
	
	public ModulePort(String name) {
		this(name, null, false);
	}
	
	public ModulePort(String name, ModulePort target, boolean isInput) {
		this.target = target;
		this.isInput = isInput;
		this.name = name;
		this.value = 0;
		
		if (target != null) target.setTargetPort(this);
	}
	
	public String getName()							{return this.name;}
	public ModulePort getTargetPort() 				{return this.target;}
	public void setTargetPort(ModulePort target) 	{this.target = target;}
	public void setIsInput(boolean value) 			{this.isInput = value;}
	public int getValue() 							{return this.value;}
	public void setValue(int value)					{this.value = value;}
}
