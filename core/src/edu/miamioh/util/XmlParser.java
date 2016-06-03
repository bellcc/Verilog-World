/*
 * @author Zach McQuigg
 * @date 6-3-2016
 */

package edu.miamioh.util;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class XmlParser {
	private Document doc;

	
	public XmlParser(String xmlFileName){
	    try {

			File fXmlFile = new File("./" + xmlFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public void getNodes() {

	    try {

		System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
				
		NodeList nList = doc.getElementsByTagName("Block");
				
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element : " + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				/* 
				 * 
				System.out.println("Location : " + "row = " +((Element) (eElement.getElementsByTagName("Location").item(0))).getAttribute("row") +
						", column = " + ((Element) (eElement.getElementsByTagName("Location").item(0))).getAttribute("column"));
				*/
				
				//Print out xml for testing purposes
				System.out.println("Row : " + eElement.getElementsByTagName("Row").item(0).getTextContent());
				System.out.println("Column : " + eElement.getElementsByTagName("Column").item(0).getTextContent());
				System.out.println("VerilogFileName : " + eElement.getElementsByTagName("VerilogFileName").item(0).getTextContent());
				System.out.println("Color : " + eElement.getElementsByTagName("Color").item(0).getTextContent());
			}
		}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
