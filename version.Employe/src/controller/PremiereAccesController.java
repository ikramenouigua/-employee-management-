package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PremiereAccesController extends Application {
	public void start(Stage stage)  {
    	try {
    	 
        Parent root = FXMLLoader.load(getClass().getResource("/vue/Connexion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
