package wtool;

import java.util.ArrayList;
import java.util.stream.IntStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
* XML Reader Class
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/

public class XMLReader {
	private ArrayList<Item> items;
	private Document configFile;
	private NodeList objectList;
	
	public XMLReader() {
        this.items = new ArrayList<>();
        
        readObjects();
	}
	
	/**
     * method readObjects
     *
     * Parse the config.xml passes this to the getAllObjects()
     *
     * @param 
     */
	public void readObjects() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ClassLoader cl = this.getClass().getClassLoader();
            java.io.InputStream in = cl.getResourceAsStream("config.xml");
            configFile = builder.parse(in);
            configFile.getDocumentElement().normalize();
        } catch (Exception e) {
        	System.err.format(e + " Fehler beim Lesen der XML-Datei.");
        }

        getAllObjects();
    }
	
	/**
     * method getAllObjects
     *
     * Sort all Objects from config.xml to a Object List with all attributes 
     *
     * @param 
     */
	private void getAllObjects() {
        objectList = configFile.getElementsByTagName("element");

        IntStream.range(0, objectList.getLength())
                .forEach(idx ->{
                    org.w3c.dom.Node nNode = objectList.item(idx);

                    if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Item item = new Item();
                        item.setId(Integer.parseInt("0" + eElement.getElementsByTagName("id").item(0).getTextContent()));
                        item.setOrder(eElement.getElementsByTagName("order").item(0).getTextContent());
                        item.setSource(eElement.getElementsByTagName("source").item(0).getTextContent());
                        item.setTarget(eElement.getElementsByTagName("target").item(0).getTextContent());
                        items.add(item);
                    }
                }); 
    }
	
	/**
     * method getItems
     *
     * Method to access all objects 
     *
     * @param 
     */
	public ArrayList<Item> getItems() {
        return items;
    }
}
