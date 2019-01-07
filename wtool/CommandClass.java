package wtool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* CommandClass for starting the process
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/
public class CommandClass {
	public static String ausgabe = "";
	public static String ausgabeErfolg = "";
	public static String ausgabeFehler = "";	
	
	/**
     * method processStart
     *
     * Sort the commands in the two ways, Delete and Copy.
     * After that, it launch the Apple Script Class and compose 
     * a feedback for the Viewer
     * @param 
	 * @throws InterruptedException, IOException 
     */
	public static void processStart() throws InterruptedException, IOException{
		XMLReader xmlObject = new XMLReader();
		int length = xmlObject.getItems().size();
		xmlObject.getItems().get(0);		
		
		if(xmlObject.getItems() != null) {
			for(int i = 0; i < length; i++){
				
				if(xmlObject.getItems().get(i).getOrder().equals("Delete")){
					File structure = new File(xmlObject.getItems().get(i).getSource());
					DeletionClass.deleteDir(structure);
					
				} else if(xmlObject.getItems().get(i).getOrder().equals("Copy")){
					Path source = Paths.get(xmlObject.getItems().get(i).getSource());	
					Path target = Paths.get(xmlObject.getItems().get(i).getTarget());
					Files.walkFileTree(source, new CopyClass(source, target));
				}
			}
		}else{
			System.err.format("Datei nicht gefunden.");
		}
		//Starting the Apple Script Class
		StartAppleScripts.scripts();
		
		//Check and compose the feedback
		if(DeletionClass.check == true && CopyClass.check == true && StartAppleScripts.check == true){
			ausgabeErfolg = String.format("Wartung wurde erfolgreich durchgeführt.");
		}else{
			ausgabeFehler = String.format("Wartung wurde mit Fehlern beendet.");
		}		
		ausgabe = String.format(DeletionClass.ausgabe + "%n" + CopyClass.ausgabe + "%n" +
			StartAppleScripts.ausgabe);		
	}
}