package edu.miamioh.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    
    public void createTempFile(String filePath, String projectPath, String fileName) throws IOException {
    	
		Path dst = Paths.get(projectPath + fileName);

		InputStream in = getClass().getResourceAsStream(filePath);
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		BufferedWriter writer = Files.newBufferedWriter(dst, StandardCharsets.UTF_8);
		
		String text = "";
		
		while((text = input.readLine()) != null) {
			
			writer.write(text);
			writer.newLine();
		}
		
		input.close();
		writer.close();
    	
    }
    
}
