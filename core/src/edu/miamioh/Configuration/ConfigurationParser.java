
/**
 * @author Clark Bell
 * @date   6-3-2016
 * @info   This class provides the functionality to 
 *         parse the default Verilog World settings as 
 *         well as other defined settings that are not 
 *         necessarily the default that are defined within 
 *         an xml file of the correct syntax.
 */

package edu.miamioh.Configuration;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.blocks.BlankBlock;
import edu.miamioh.GameObjects.blocks.ControllerBlock;
import edu.miamioh.GameObjects.blocks.LedBlock;
import edu.miamioh.GameObjects.blocks.ScooterBlock;
import edu.miamioh.GameObjects.blocks.WallBlock;
import edu.miamioh.Level.Level;

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
		Configuration config = getConfiguration(file).getConfig();
	
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
	public Level getConfiguration(File file) {
		
		Level level = new Level();
		Configuration config = new Configuration();
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList worldList = doc.getElementsByTagName("world");
			Node worldNode = worldList.item(0);
			
			NodeList blockList = worldNode.getChildNodes();

			for(int i=0;i<blockList.getLength();i++) {
								
				if(blockList.item(i).getNodeName().equals("block")) {
					
					Element blockElement = (Element) blockList.item(i);
					
					int row = Integer.parseInt(blockElement.getElementsByTagName("row").item(0).getTextContent());
					int column = Integer.parseInt(blockElement.getElementsByTagName("column").item(0).getTextContent());
					int id = Integer.parseInt(blockElement.getElementsByTagName("id").item(0).getTextContent());
					String type = blockElement.getElementsByTagName("type").item(0).getTextContent();
					
					switch (type) {
					
						case "Blank":
							level.addBlock(new BlankBlock(row, column, id));
							level.getBlock(row, column).setID(id);
							break;
							
						case "Wall":
							level.addBlock(new WallBlock(row, column, id));
							level.getBlock(row, column).setID(id);
							break;
							
						case "Controller":
							level.addBlock(new ControllerBlock(row, column, id));
							level.getBlock(row, column).setID(id);
							break;
							
						case "Scooter":
							level.addBlock(new ScooterBlock(row, column, id));
							level.getBlock(row, column).setID(id);
							break;
							
						case "Led":
							level.addBlock(new LedBlock(row, column, id));
							level.getBlock(row, column).setID(id);
							break;
							
					}
					
				}
				
			}
			
			if(worldNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element worldElement = (Element) worldNode;
				
				String tempWorldWidth = worldElement.getElementsByTagName("world-width").item(0).getTextContent();
				String tempWorldHeight = worldElement.getElementsByTagName("world-height").item(0).getTextContent();

				String tempBufferWidth = worldElement.getElementsByTagName("buffer-width").item(0).getTextContent();
				String tempBufferHeight = worldElement.getElementsByTagName("buffer-height").item(0).getTextContent();
				
				String tempGridWidth = worldElement.getElementsByTagName("grid-width").item(0).getTextContent();
				String tempGridHeight = worldElement.getElementsByTagName("grid-height").item(0).getTextContent();
				
				String tempStepWidth = worldElement.getElementsByTagName("step-width").item(0).getTextContent();
				String tempStepHeight = worldElement.getElementsByTagName("step-height").item(0).getTextContent();
				
				int worldWidth = Integer.parseInt(tempWorldWidth);
				int worldHeight = Integer.parseInt(tempWorldHeight);

				int bufferWidth = Integer.parseInt(tempBufferWidth);
				int bufferHeight = Integer.parseInt(tempBufferHeight);
				
				int gridWidth = Integer.parseInt(tempGridWidth);
				int gridHeight = Integer.parseInt(tempGridHeight);
				
				int stepWidth = Integer.parseInt(tempStepWidth);
				int stepHeight = Integer.parseInt(tempStepHeight);
				
				config.setWorldWidth(worldWidth);
				config.setWorldHeight(worldHeight);

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
		
		level.setConfig(config);
		
		return level;
	}
	
	/**
	 * This method will create a world.xml file at the specified 
	 * location based on the level object. This includes blocks, 
	 * their locations, the module, the type of block, and the 
	 * connections made between that block and the other blocks.
	 * 
	 * @param level The level object that will be used to create 
	 *              the xml file.
	 * @param file The file which contains the location where the 
	 *        file will be created.
	 */
	public void createWorld(Level level, File file) {
		
		Configuration config = level.getConfig();
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("world");
			doc.appendChild(rootElement);

			rootElement.appendChild(addElement(doc, "world-width", String.valueOf(config.getWorldWidth())));
			rootElement.appendChild(addElement(doc, "world-height", String.valueOf(config.getWorldHeight())));

			rootElement.appendChild(addElement(doc, "grid-width", String.valueOf(config.getGridWidth())));
			rootElement.appendChild(addElement(doc, "grid-height", String.valueOf(config.getGridHeight())));
			
			rootElement.appendChild(addElement(doc, "step-width", String.valueOf(config.getStepWidth())));
			rootElement.appendChild(addElement(doc, "step-height", String.valueOf(config.getStepHeight())));
			
			rootElement.appendChild(addElement(doc, "buffer-width", String.valueOf(config.getBufferWidth())));
			rootElement.appendChild(addElement(doc, "buffer-height", String.valueOf(config.getBufferHeight())));
			
			ArrayList<Block> blockList = level.getBlockList();
			
			for(int i=0;i<blockList.size();i++) {
				
				Element element = doc.createElement("block");
				rootElement.appendChild(element);
				
				Element id = doc.createElement("id");
				id.setTextContent(String.valueOf(blockList.get(i).getID()));
				element.appendChild(id);
				
				Element row = doc.createElement("row");
				row.setTextContent(String.valueOf(blockList.get(i).getRow()));
				element.appendChild(row);
				
				Element column = doc.createElement("column");
				column.setTextContent(String.valueOf(blockList.get(i).getColumn()));
				element.appendChild(column);
				
				NormalBlock block = (NormalBlock) blockList.get(i);				
				Element type = doc.createElement("type");
				type.setTextContent(block.getType().toString());
				element.appendChild(type);
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
			
			System.out.println("File written.");
			
		
		}catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	
	}

	/**
	 * This method will add and element to the document based on 
	 * the title of the element and the content text of that title.
	 * 
	 * @param  doc The document to add the element to.
	 * @param  title The title of the xml node.
	 * @param  text The text to be contained within that xml node.
	 * 
	 * @return The element to be added to the xml document.
	 */
	private Element addElement(Document doc, String title, String text) {
		
		Element element = doc.createElement(title);
		element.appendChild(doc.createTextNode(text));
		
		return element;
		
	}
	
}
