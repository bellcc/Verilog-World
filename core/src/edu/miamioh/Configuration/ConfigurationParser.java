
/**
 * @author Chris Bell
 * @date   6-3-2016
 * @info   This class provides the functionality to 
 *         parse the default Verilog World settings as 
 *         well as other defined settings that are not 
 *         necessarily the default that are defined within 
 *         an xml file of the correct syntax.
 */

package edu.miamioh.Configuration;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ConfigurationParser {

	private String defaultConfigurationPath = "world.xml";
	
	/**
	 * This method is used to parse the default "world.xml" 
	 * file that contains the default by value settings for 
	 * the Verilog World program during execution.
	 * 
	 * @return The default configuration settings as defined 
	 * by the default program settings file.
	 */
	public Configuration getDefaultConfiguration() {
		
		File file = new File(defaultConfigurationPath);
		Configuration config = getConfiguration(file);
	
		return config;
	}
	
	/**
	 * This method is used to parse a non-default configuration 
	 * file that is provided as a parameter to this method. 
	 * Instances that you may consider using this are in the 
	 * instance that a specific lab/challenge needs different 
	 * values for whatever reason. 
	 * 
	 * @param file The file that is used to define the new or 
	 * different configuration details.
	 * 
	 * @return The configuration details as defined by the 
	 * parameter provided file.
	 */
	public Configuration getConfiguration(File file) {
		
		Configuration config = new Configuration();
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList worldList = doc.getElementsByTagName("world");
			Node worldNode = worldList.item(0);
			
			if(worldNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element worldElement = (Element) worldNode;
				
				String tempWorldWidth = worldElement.getElementsByTagName("world-width").item(0).getTextContent();
				String tempWorldHeight = worldElement.getElementsByTagName("world-height").item(0).getTextContent();
				
				String tempWindowWidth = worldElement.getElementsByTagName("window-width").item(0).getTextContent();
				String tempWindowHeight = worldElement.getElementsByTagName("window-height").item(0).getTextContent();
				
				String tempBufferWidth = worldElement.getElementsByTagName("buffer-width").item(0).getTextContent();
				String tempBufferHeight = worldElement.getElementsByTagName("buffer-height").item(0).getTextContent();
				
				String tempGridWidth = worldElement.getElementsByTagName("grid-width").item(0).getTextContent();
				String tempGridHeight = worldElement.getElementsByTagName("grid-height").item(0).getTextContent();
				
				String tempStepWidth = worldElement.getElementsByTagName("step-width").item(0).getTextContent();
				String tempStepHeight = worldElement.getElementsByTagName("step-height").item(0).getTextContent();
				
				int worldWidth = Integer.parseInt(tempWorldWidth);
				int worldHeight = Integer.parseInt(tempWorldHeight);
				
				int windowWidth = Integer.parseInt(tempWindowWidth);
				int windowHeight = Integer.parseInt(tempWindowHeight);
				
				int bufferWidth = Integer.parseInt(tempBufferWidth);
				int bufferHeight = Integer.parseInt(tempBufferHeight);
				
				int gridWidth = Integer.parseInt(tempGridWidth);
				int gridHeight = Integer.parseInt(tempGridHeight);
				
				int stepWidth = Integer.parseInt(tempStepWidth);
				int stepHeight = Integer.parseInt(tempStepHeight);
				
				config.setWorldWidth(worldWidth);
				config.setWorldHeight(worldHeight);
				
				config.setWindowWidth(windowWidth);
				config.setWindowHeight(windowHeight);
				
				config.setBufferWidth(bufferWidth);
				config.setBufferHeight(bufferHeight);
				
				config.setGridWidth(gridWidth);
				config.setGridHeight(gridHeight);
				
				config.setStepWidth(stepWidth);
				config.setStepHeight(stepHeight);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return config;
	}
}
