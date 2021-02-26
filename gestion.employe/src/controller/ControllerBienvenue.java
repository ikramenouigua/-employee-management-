package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import controller.GestionController;
import controller.ContratController;
public class ControllerBienvenue implements Initializable{
	Stage dialogStage = new Stage();
    Scene scene;
    @FXML  private ComboBox<String> employe =new ComboBox<String>();
   
    @FXML private TextField message;
    
    int x=ContratController.nbContratEpuisee();
    public void messagenouveau() {
    	int y=CongeAnnuelController.nbCongeAnnuel();
    	int w=CongeMaladieController.nbCongeMaladie();
    	int z=x+y+w;
    	if(z>0) {
    		message.setText("nouveau");
    	}else {
    		message.setText("0");
    	}
    }
    
    public void afficherMessage(MouseEvent event) throws IOException {
    	Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/MessagerieInterface.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
	}
    
   
    
    
    public void choixEmploye(ActionEvent event) throws IOException{
    	
    	
	 
    	if(employe.getValue().toString().equalsIgnoreCase("Afficher Employe")) {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/AfficherInterface3.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    	if(employe.getValue().toString().equalsIgnoreCase("Afficher conge annuel")) {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/AfficherCongeAnnuel.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    }
    
            
    	
    
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		employe.getItems().addAll("Afficher Employe","Afficher conge annuel");
		
		messagenouveau();
		
	
	}
	 
}
