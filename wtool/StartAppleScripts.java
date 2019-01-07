package wtool;

import java.io.IOException;

/**
* Class for starting Apple Script Orders
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/

public class StartAppleScripts{
	public static boolean check = false;
	public static String ausgabe = "";
 
	/**
     * method scripts
     *
     * Prepare Apple Scripts commands and start them 
     *
     * @param 
     */
	public static void scripts() throws InterruptedException{ 
		Runtime runtime = Runtime.getRuntime();
		String[] args = { "osascript", "-e", "tell application \"Finder\" to set desktop picture to POSIX file \"/Users/admin/Pictures/rbb-VJ.jpg\"", 
    		"-e", "tell application \"System Events\" to tell screen saver preferences to set delay interval to 0"};
    
		try{
			Process start = runtime.exec(args);
			start.waitFor();
			if(start.exitValue() == 0){
				ausgabe = String.format("Systemeinstellungen wurden korrigiert.");
				check = true;
    	}else{
    		ausgabe = String.format("Fehler - Systemeinstellungen konnten nicht angepasst werden.");
    		System.err.printf("Scripts konnten nicht ausgeführt werden.%n");
    		}
		}
		catch (IOException e){
			System.err.format(e + " Fehler beim Start der Runtime.");
		}
	}   
}