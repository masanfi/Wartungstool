package wtool;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
* Class for copy commands
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/

public class CopyClass extends SimpleFileVisitor<Path> {
	private static int copyFileAndDir = 0;
	public static String ausgabe = "";
	public static boolean check = false;
	private Path source;
	private Path targetDir;

	public CopyClass(Path sourceDir, Path targetDir) {
		this.source = sourceDir;
		this.targetDir = targetDir;
		
		//Define feedback after checking the copy process
		if(copyFileAndDir > 0){
			ausgabe = String.format(copyFileAndDir + " kopierte Dateien und Verzeichnisse.");
			check = true;
		}else{
			ausgabe = String.format("Fehler - es wurde nichts kopiert.");
		}
	}

	/**
     * method visitFile
     *
     * Recursive method to copy all files.
     * 
     * @param Path file, BasicFileAttributes attributes
     */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
		try {
			Path target = targetDir.resolve(source.relativize(file));
			Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
			copyFileAndDir++;
		} catch (IOException e) {
			System.err.println(e + " Datei konnte nicht kopiert oder überschrieben werden.");
		}	
		return FileVisitResult.CONTINUE;
	}

	/**
     * method preVisitDirectory
     *
     * method to create new dir for the copy process.
     * 
     * @param Path dir, BasicFileAttributes attributes
     */
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
		try {
			Path newDir = targetDir.resolve(source.relativize(dir));
			Files.createDirectory(newDir);
			copyFileAndDir++;
		} catch(FileAlreadyExistsException e){
			System.err.println(e + " Ordner existiert bereits.");
		} catch (IOException e) {
			System.err.println(e + " Fehler.");
		}
		return FileVisitResult.CONTINUE;
	}		
}