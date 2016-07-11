package edu.miamioh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileTools {
    
    public static void copyFile(File sourceFile, File destFile) {
	
	try {
	    if (!destFile.exists()) {
		destFile.createNewFile();
	    }
	    
	    FileInputStream source = new FileInputStream(sourceFile);
	    FileOutputStream dest = new FileOutputStream(destFile);
	    
	    while (source.available() != 0) {
		dest.write(source.read());
	    }
	    
	    source.close();
	    dest.close();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
