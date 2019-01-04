package wtool;

import java.io.File;

/**
* Class for delete commands
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/

public class DeletionClass {
	private static int deletedFileAndDir = 0;
	public static String ausgabe = "";
	public static boolean check = false;	
	
	/**
     * method deleteDir
     *
     * Recursive method to delete all files and directories
     *
     * @param File structure
     */
	public static void deleteDir(File structure) {
		try {
			for (File file : structure.listFiles()) {
				if(file.isDirectory()){
					deleteDir(file);
					}
				if(file.delete()){
				//Count deleted Files and Directories
				deletedFileAndDir++;}
			}		
		} catch (NullPointerException n) {
			System.err.format("%s: Datei oder Verzeichnis existiert nicht und konnte nicht gelöscht werden%n", structure);
		}
		
		//Define feedback after checking the copy process
		if(deletedFileAndDir > 0){
			ausgabe = String.format(deletedFileAndDir + " gelöschte Dateien und Verzeichnisse.");
			check = true;
		}else{
			ausgabe = String.format("Fehler - es wurde nichts gelöscht.");
		}
	 }
}
