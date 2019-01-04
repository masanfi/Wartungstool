package wtool;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
* VJ-Wartung 1.0
* Maintenance tool for MacBooks
* Copyright (c) 2019. All Rights Reserved.
* @author Martin Sanfilippo
* @version 1.0
*
*/
public class Viewer extends Application {
	private Scene scene;	
	
	/**
     * method start
     *
     * Initializes the Grid with Label and Buttons
     *
     * @param primaryStage
     */
	 @Override
	 public void start(Stage primaryStage) {
	      try {
	        primaryStage.setTitle("VJ-Wartung 1.0");
	        primaryStage.setResizable(false);
	        	
	        GridPane root = new GridPane();
	        root.setGridLinesVisible(false);
	        root.setAlignment(Pos.TOP_LEFT);
		    root.setHgap(10);
		    root.setVgap(10);  
		    root.getColumnConstraints().add(new ColumnConstraints(200));
		    root.getColumnConstraints().add(new ColumnConstraints(400));
		    root.setPadding(new Insets(10, 10, 10, 10));
		        
		    //Eight Text-Label
		    Label headline = new Label("rbb Wartungstool");
			root.add(headline, 0,0,3,1);
			headline.setTextFill(Color.DARKRED);
		    headline.setPrefHeight(25);
		    headline.setStyle("-fx-font: 24 futura;");
		    headline.setAlignment(Pos.CENTER);
		    headline.setPrefWidth(450);
			    
		    Label copyright = new Label("Copyright \u00a9 2019 Martin Sanfilippo");
		    root.add(copyright, 0,11,1,1);
		    copyright.setTextFill(Color.BLACK);
		    copyright.setPrefHeight(5);
		    copyright.setStyle("-fx-font: 7 futura;");
		    copyright.setAlignment(Pos.BASELINE_LEFT);
		    copyright.setPrefWidth(450);
			    
		    Label fragen = new Label("Repository and requests: https://github.com/masanfi");
		    root.add(fragen, 0,11,4,1);
		    fragen.setTextFill(Color.BLACK);
		    fragen.setPrefHeight(5);
		    fragen.setStyle("-fx-font: 7 futura;");
		    fragen.setAlignment(Pos.BASELINE_RIGHT);
		    fragen.setPrefWidth(450);
		        
		    Label hinweis = new Label("User-Daten entfernen und Laptop auf Standard zurücksetzen.");
		    root.add(hinweis, 0,2,3,1);
		    hinweis.setTextFill(Color.DARKRED);
		    hinweis.setStyle("-fx-font: 10 futura;");
		    hinweis.setAlignment(Pos.CENTER);
		    hinweis.setPrefWidth(450);
			    
		    Label startLabel = new Label("ACHTUNG!");
		    root.add(startLabel, 0,5,3,1);
		    startLabel.setPrefWidth(450);
		    startLabel.setPrefHeight(20);
	        startLabel.setAlignment(Pos.CENTER);
	        startLabel.setTextFill(Color.DARKRED);
	        startLabel.setStyle("-fx-font: 12 futura;");
		        
	        Label feedback = new Label("");
	        root.add(feedback, 0,6,3,1);
	        feedback.setPrefWidth(450);
	        feedback.setPrefHeight(55);
	        feedback.setAlignment(Pos.CENTER);
	        feedback.setTextFill(Color.BLACK);
	        feedback.setStyle("-fx-font: 12 futura;");
	        String infoText = String.format("Mit dem Starten der Wartung werden Verzeichnisse gelöscht und Dateien%n" +
	        		"kopiert. Eine Wiederherstellung ist nicht möglich! Dies funktioniert nur%n" +
	        		"Fehlerfrei, wenn dies ein VJ-Laptop mit Standard Konfiguration ist.");
	        feedback.setText(infoText);
		        
	        Label fazit = new Label("");
	        root.add(fazit, 0,7,4,3);
	        fazit.setPrefWidth(450);
	        fazit.setPrefHeight(60);
	        fazit.setAlignment(Pos.CENTER);
	        fazit.setTextFill(Color.DARKGREEN);
	        fazit.setStyle("-fx-font: 12 futura;");
		        
	        Label fehler = new Label("");
	        root.add(fehler, 0,7,4,3);
	        fehler.setPrefWidth(450);
	        fehler.setPrefHeight(60);
	        fehler.setAlignment(Pos.CENTER);
	        fehler.setTextFill(Color.DARKRED);
	        fehler.setStyle("-fx-font: 12 futura;");
		        
	        //Two Buttons 
	        Button startButton = new Button("Wartung starten");
	        root.add(startButton, 0,3,3,1);
	        startButton.setPrefWidth(450);
	        startButton.setAlignment(Pos.CENTER);
	        startButton.setStyle("-fx-base: DARKGREEN");
	        startButton.defaultButtonProperty().bind(startButton.focusedProperty());
		         
	        Button exitButton = new Button("Programm beenden");
	        root.add(exitButton, 0,10,3,1);
	        exitButton.setPrefWidth(450);
	        exitButton.setAlignment(Pos.CENTER);
	        exitButton.setStyle("-fx-base: DARKRED");
		        
	        //Button for starting the method of the CommandClass
	        //Display Feedback of the CommandClass
	        try {
	        startButton.setOnAction(new EventHandler<ActionEvent>() {
		        	 
	            @Override
	           public void handle(ActionEvent event) {
					startLabel.setText("Wartungsbericht:");
					try {
						CommandClass.processStart();
					} catch (InterruptedException e) {
						System.err.format(e + " Fehler");
					}
					
					feedback.setText(CommandClass.ausgabe);
					fazit.setText(CommandClass.ausgabeErfolg);
					fehler.setText(CommandClass.ausgabeFehler);
						
					startButton.setDisable(true);
					exitButton.defaultButtonProperty().bind(exitButton.focusedProperty());
	            }});
			} catch (Exception e) {
				System.err.format(e + "Fehler beim Starten der Wartung.");
			}
	        
	        //Button to close the program
	        try {
		        exitButton.setOnAction(new EventHandler<ActionEvent>() {
			        	 
		            @Override
		            public void handle(ActionEvent event) {
		            	System.exit(0);
		            }});
				} catch (Exception e) {
					System.err.format(e + "Fehler beim Beenden des Programm.");
				}        
	            
	        scene = new Scene(root,470,350);
	        primaryStage.setScene(scene);
	        primaryStage.show();
        } catch(Exception e) {
        	System.err.format(e + "Fehler");
        }
   }
    
	 /**
	 * main method
     *
     * launch the program
     *
     * @param args
     */	 
    public static void main(String[] args) {
        launch(args);
    }   
}