package edu.miamioh.GameObjects;

public enum NormalBlockType {
	
	Blank("Blank Block"),
	Wall("Wall Block"),
	Controller("Controller Block"),
	Scooter("Scooter Block"),
	Led("LED Block");
	
	public String name;
	
	private NormalBlockType(String name) {
		this.name = name;
	}
}
