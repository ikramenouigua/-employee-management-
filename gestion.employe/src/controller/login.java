package controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
 
public class login extends Application {
	public void start(Stage stage)  {
    	try {
    	 
        Parent root = FXMLLoader.load(getClass().getResource("/vue/LoginInterface2.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    	}catch(Exception e) {
    		
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
