/*
 * @author Zach McQuigg
 * @date 6-3-2016
 */

package edu.miamioh.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlWriter {
	
	private Element mainRootElement;
	private String xml;
	private int row; //x coordinate for block
	private int column; //y coordinate for block 
	private Document doc;
	
	public XmlWriter(String xmlName, int levelNumber) {
       xml = xmlName + ".xml";
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       try {
           DocumentBuilder icBuilder = dbFactory.newDocumentBuilder();
           doc = icBuilder.newDocument();
           mainRootElement = doc.createElement("Level" + Integer.toString(levelNumber));
           doc.appendChild(mainRootElement);
       } catch (Exception e) {
           e.printStackTrace();
       }
	}
	
	public void writeXml(){

           try {
               Transformer tr = TransformerFactory.newInstance().newTransformer();
               tr.setOutputProperty(OutputKeys.INDENT, "yes");
               tr.setOutputProperty(OutputKeys.METHOD, "xml");
               tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
               //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "block.dtd");

               // send DOM to file
               tr.transform(new DOMSource(doc), 
                                    new StreamResult(new FileOutputStream(xml)));

           } catch (TransformerException te) {
               System.out.println(te.getMessage());
           } catch (IOException ioe) {
               System.out.println(ioe.getMessage());
           }

   }
	// Add a new Block node 
	public void newBlock(int row, int column, String color, String verilogFileName){
		this.row = row;
		this.column = column;
       
		mainRootElement.appendChild(addBlock(doc, color, verilogFileName));
           

	}

   private Node addBlock(Document doc, String color, String verilogFileName) {
       Element block = doc.createElement("Block");
       //block.appendChild(getBlockLocation(doc, block, "Location", Integer.toString(row), Integer.toString(column)));
       block.appendChild(getBlockElements(doc, block, "Row", Integer.toString(row)));
       block.appendChild(getBlockElements(doc, block, "Column", Integer.toString(column)));
       block.appendChild(getBlockElements(doc, block, "VerilogFileName", verilogFileName));
       block.appendChild(getBlockElements(doc, block, "Color", color)); //I can easily change color to RGB values if needed
       return block;
   }

   // create text node
   private Node getBlockElements(Document doc, Element element, String name, String value) {
       Element node = doc.createElement(name);
       node.appendChild(doc.createTextNode(value));
       return node;
   }
   /*
   private Node getBlockLocation(Document doc, Element element, String name, String row, String column){
   	Element location = doc.createElement(name);
   	location.setAttribute("row", row);
   	location.setAttribute("column", column);
   	return location;
   }
   */
}
