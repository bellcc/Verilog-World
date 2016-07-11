package edu.miamioh.util;

import java.util.ArrayList;
import java.util.Random;

import edu.miamioh.GameObjects.Block;

public class UniqueNumber {

	public int generateID(ArrayList<Block> blockList, int min, int max) {
		
		boolean flag = false;
		Random rand = new Random();
		
		while(true) {
			
			int num = (rand.nextInt(max - min) + 1) + min;
			
			if(blockList.size() == 0) {
				return num;
			}
			
			flag = false;
			
			for(int i=0;i<blockList.size();i++) {
				
				if(num == blockList.get(i).getID()) {
					flag = true;
				}
				
				if(i == blockList.size() - 1 && flag == false) {
					return num;
				}
			}
		}
		
	}
}
