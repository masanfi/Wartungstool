package wtool;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
* Class for copy commands
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/

public class CopyClass {
	private static int copyFileAndDir = 0;
	public static String ausgabe = "";
	public static boolean check = false;
		
	/**
     * method copyFile
     *
     * Take a file or directory and copy it to a target
     *
     * @param Path source, Path target
     */
	public static void copyFile(Path source, Path target){

		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			//Count copied Files and Directories
			copyFileAndDir++;
		} catch (NoSuchFileException n) {
	        System.err.format("%s: Datei nicht gefunden %n", source);
	    } catch (DirectoryNotEmptyException d) {
	        System.err.format("%s nicht leer%n", source);
	    } catch (IOException e) {
	    	System.err.format(e + " Fehler");}
		
		//Define feedback after checking the copy process
		if(copyFileAndDir > 0){
			ausgabe = String.format(copyFileAndDir + " kopierte Dateien und Verzeichnisse.");
			check = true;
		}else{
			ausgabe = String.format("Fehler - es wurde nichts kopiert.");
		}
	}
}
