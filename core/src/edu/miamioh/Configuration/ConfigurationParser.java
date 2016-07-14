
/**
 * @author Clark Bell
 * @date 6-3-2016
 * @info This class provides the functionality to parse the default Verilog
 *       World settings as well as other defined settings that are not
 *       necessarily the default that are defined within an xml file of the
 *       correct syntax.
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

import com.badlogic.gdx.Gdx;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.blocks.BlankBlock;
import edu.miamioh.GameObjects.blocks.ControllerBlock;
import edu.miamioh.GameObjects.blocks.LedBlock;
import edu.miamioh.GameObjects.blocks.ScooterBlock;
import edu.miamioh.GameObjects.blocks.WallBlock;
import edu.miamioh.Level.Level;
import edu.miamioh.worldSimulator.ModulePort;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ConfigurationParser {

	private String defaultConfigurationPath = "default_world.xml";
	
	/**
	 * This method is used to parse the default "world.xml" file that contains
	 * the default by value settings for the Verilog World program during
	 * execution.
	 * 
	 * @return The default configuration settings as defined by the default
	 *         program settings file.
	 */
	public Configuration getDefaultConfiguration() {
		
		//File file = Gdx.files.internal("core/assets/" + defaultConfigurationPath).file();
		File file = new File(defaultConfigurationPath);
		Configuration config = getConfiguration(file).getConfig();
		
		return config;
	}
	
	/**
	 * This method is used to parse a non-default configuration file that is
	 * provided as a parameter to this method. Instances that you may consider
	 * using this are in the instance that a specific lab/challenge needs
	 * different values for whatever reason.
	 * 
	 * @param file
	 *            The file that is used to define the new or different
	 *            configuration details.
	 * 
	 * @return The configuration details as defined by the parameter provided
	 *         file.
	 */
	public Level getConfiguration(File file) {
		
		Level level = new Level();
		Configuration config = new Configuration();
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList worldList = doc.getElementsByTagName("world");
			Node worldNode = worldList.item(0);
			
			NodeList blockList = doc.getElementsByTagName("block");
			
			for (int i = 0; i < blockList.getLength(); i++) {
				
				Element blockElement = (Element) blockList.item(i);
				
				int row = Integer.parseInt(blockElement
						.getElementsByTagName("row").item(0).getTextContent());
				int column = Integer
						.parseInt(blockElement.getElementsByTagName("column")
								.item(0).getTextContent());
				int id = Integer.parseInt(blockElement
						.getElementsByTagName("id").item(0).getTextContent());
				String type = blockElement.getElementsByTagName("type").item(0)
						.getTextContent();
				
				NormalBlock block = null;
				switch (type) {
					
					case "Blank":
						block = new BlankBlock(row, column, id);
						level.addBlock(block);
						block.setID(id);
						block.compile();
						break;
					
					case "Wall":
						block = new WallBlock(row, column, id);
						level.addBlock(block);
						block.setID(id);
						block.compile();
						break;
					
					case "Controller":
						block = new ControllerBlock(row, column, id);
						level.addBlock(block);
						block.setID(id);
						block.compile();
						break;
					
					case "Scooter":
						block = new ScooterBlock(row, column, id);
						level.addBlock(block);
						block.setID(id);
						block.compile();
						break;
					
					case "Led":
						block = new LedBlock(row, column, id);
						level.addBlock(block);
						block.setID(id);
						block.compile();
						break;
					
				}
				
			}
			
			NodeList portList = doc.getElementsByTagName("port");
			
			for (int i = 0; i < portList.getLength(); ++i) {
				
				Element portElement = (Element) portList.item(i);
				
				// Gather all the necessary information to construct a module
				// port
				// primary block
				int row = Integer
						.parseInt(portElement.getElementsByTagName("loc-row")
								.item(0).getTextContent());
				int col = Integer
						.parseInt(portElement.getElementsByTagName("loc-column")
								.item(0).getTextContent());
				String portName = portElement.getElementsByTagName("port-name")
						.item(0).getTextContent();
				boolean isInput = Boolean.parseBoolean(
						portElement.getElementsByTagName("is-input").item(0)
								.getTextContent());
				String wireName = portElement.getElementsByTagName("wire-name")
						.item(0).getTextContent();
				
				// target block
				int targetRow = Integer.parseInt(
						portElement.getElementsByTagName("target-loc-row")
								.item(0).getTextContent());
				int targetCol = Integer.parseInt(
						portElement.getElementsByTagName("target-loc-column")
								.item(0).getTextContent());
				String targetPortName = portElement
						.getElementsByTagName("target-port-name").item(0)
						.getTextContent();
				boolean targetIsInput = Boolean.parseBoolean(
						portElement.getElementsByTagName("target-is-input")
								.item(0).getTextContent());
				String targetWireName = portElement
						.getElementsByTagName("target-wire-name").item(0)
						.getTextContent();
				
				// Get the necessary blocks we are working with
				NormalBlock block = (NormalBlock) level.getBlock(row, col);
				NormalBlock targetBlock = (NormalBlock) level
						.getBlock(targetRow, targetCol);
				
				// Because there will port entry pairs (i.e. block 'A' has port
				// 'X' whose target is 'Y' and block 'B'
				// has port 'Y' whose target is 'X') we will only construct a
				// new module port if it hasn't already been made
				// by a previous run through this loop.
				if (!block.getModuleWrapper().getPortsHash()
						.containsKey(portName)
						|| !targetBlock.getModuleWrapper().getPortsHash()
								.containsKey(targetPortName)) {
					
					// Make the ports and add them to the blocks
					ModulePort port = new ModulePort(block, portName, isInput,
							wireName, targetBlock, targetPortName,
							targetIsInput, targetWireName);
					ModulePort targetPort = port.getTargetPort();
					
					block.getModuleWrapper().addPort(port);
					targetBlock.getModuleWrapper().addPort(targetPort);
				}
			}
			
			if (worldNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element worldElement = (Element) worldNode;
				
				String tempWorldWidth = worldElement
						.getElementsByTagName("world-width").item(0)
						.getTextContent();
				String tempWorldHeight = worldElement
						.getElementsByTagName("world-height").item(0)
						.getTextContent();
				
				String tempBufferWidth = worldElement
						.getElementsByTagName("buffer-width").item(0)
						.getTextContent();
				String tempBufferHeight = worldElement
						.getElementsByTagName("buffer-height").item(0)
						.getTextContent();
				
				String tempGridWidth = worldElement
						.getElementsByTagName("grid-width").item(0)
						.getTextContent();
				String tempGridHeight = worldElement
						.getElementsByTagName("grid-height").item(0)
						.getTextContent();
				
				String tempStepWidth = worldElement
						.getElementsByTagName("step-width").item(0)
						.getTextContent();
				String tempStepHeight = worldElement
						.getElementsByTagName("step-height").item(0)
						.getTextContent();
				
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
	 * This method will create a world.xml file at the specified location based
	 * on the level object. This includes blocks, their locations, the module,
	 * the type of block, and the connections made between that block and the
	 * other blocks.
	 * 
	 * @param level
	 *            The level object that will be used to create the xml file.
	 * @param file
	 *            The file which contains the location where the file will be
	 *            created.
	 */
	public void createWorld(Level level, File file) {
		
		Configuration config = level.getConfig();
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("world");
			doc.appendChild(rootElement);
			
			rootElement.appendChild(addElement(doc, "world-width",
					String.valueOf(config.getWorldWidth())));
			rootElement.appendChild(addElement(doc, "world-height",
					String.valueOf(config.getWorldHeight())));
			
			rootElement.appendChild(addElement(doc, "grid-width",
					String.valueOf(config.getGridWidth())));
			rootElement.appendChild(addElement(doc, "grid-height",
					String.valueOf(config.getGridHeight())));
			
			rootElement.appendChild(addElement(doc, "step-width",
					String.valueOf(config.getStepWidth())));
			rootElement.appendChild(addElement(doc, "step-height",
					String.valueOf(config.getStepHeight())));
			
			rootElement.appendChild(addElement(doc, "buffer-width",
					String.valueOf(config.getBufferWidth())));
			rootElement.appendChild(addElement(doc, "buffer-height",
					String.valueOf(config.getBufferHeight())));
			
			ArrayList<Block> blockList = level.getBlockList();
			
			for (Block block : blockList) {
				
				// Create the block
				Element element = doc.createElement("block");
				rootElement.appendChild(element);
				
				Element id = doc.createElement("id");
				id.setTextContent(String.valueOf(block.getID()));
				element.appendChild(id);
				
				Element row = doc.createElement("row");
				row.setTextContent(String.valueOf(block.getRow()));
				element.appendChild(row);
				
				Element column = doc.createElement("column");
				column.setTextContent(String.valueOf(block.getColumn()));
				element.appendChild(column);
				
				NormalBlock nblock = (NormalBlock) block;
				Element type = doc.createElement("type");
				type.setTextContent(nblock.getType().toString());
				element.appendChild(type);
				
			}
			
			for (Block block : blockList) {
				// Get the list of ports of the block
				ArrayList<ModulePort> portList = ((NormalBlock) block)
						.getModuleWrapper().getPortsList();
				// Create the ports
				for (ModulePort port : portList) {
					
					// Create the primary port
					Element portElement = doc.createElement("port");
					rootElement.appendChild(portElement);
					
					Element portName = doc.createElement("port-name");
					portName.setTextContent(port.getName());
					portElement.appendChild(portName);
					
					Element isInput = doc.createElement("is-input");
					isInput.setTextContent(String.valueOf(port.getIsInput()));
					portElement.appendChild(isInput);
					
					Element row = doc.createElement("loc-row");
					row.setTextContent(String.valueOf(block.getRow()));
					portElement.appendChild(row);
					
					Element col = doc.createElement("loc-col");
					col.setTextContent(String.valueOf(block.getColumn()));
					portElement.appendChild(col);
					
					Element wireName = doc.createElement("wire-name");
					wireName.setTextContent(port.getWire().getName());
					portElement.appendChild(wireName);
					
					// Create the target port
					Element targetName = doc.createElement("target-port-name");
					targetName.setTextContent(port.getTargetPort().getName());
					portElement.appendChild(targetName);
					
					Element targetIsInput = doc
							.createElement("target-is-input");
					targetIsInput.setTextContent(
							String.valueOf(port.getTargetPort().getIsInput()));
					portElement.appendChild(targetIsInput);
					
					// Get the targetBlock
					NormalBlock targetBlock = port.getTargetPort().getBlock();
					
					Element targetRow = doc.createElement("target-loc-row");
					targetRow.setTextContent(
							String.valueOf(targetBlock.getRow()));
					portElement.appendChild(targetRow);
					
					Element targetCol = doc.createElement("target-loc-col");
					targetCol.setTextContent(
							String.valueOf(targetBlock.getColumn()));
					portElement.appendChild(targetCol);
					
					Element targetWireName = doc
							.createElement("target-wire-name");
					targetWireName.setTextContent(
							port.getTargetPort().getWire().getName());
					portElement.appendChild(targetWireName);
					
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
			
			System.out.println("File written.");
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
	}
	
	/**
	 * This method will add and element to the document based on the title of
	 * the element and the content text of that title.
	 * 
	 * @param doc
	 *            The document to add the element to.
	 * @param title
	 *            The title of the xml node.
	 * @param text
	 *            The text to be contained within that xml node.
	 * 
	 * @return The element to be added to the xml document.
	 */
	private Element addElement(Document doc, String title, String text) {
		
		Element element = doc.createElement(title);
		element.appendChild(doc.createTextNode(text));
		
		return element;
		
	}
	
}
