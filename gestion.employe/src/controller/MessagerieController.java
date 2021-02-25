package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MessagerieController implements Initializable {
	@FXML private TextField contrat;
	@FXML private TextField congeAnnuel;
	@FXML private TextField congeMaladie;
	Stage dialogStage = new Stage();
    Scene scene;
	public void messagerie() {
		int x=ContratController.nbContratEpuisee();
		contrat.setText("Vous avez "+x+" contrat expire cliquez pour les traiter");
		int y=CongeAnnuelController.nbCongeAnnuel();
		congeAnnuel.setText("Vous avez "+y+" conge annuel cliquez ici pour les traiter");
		int w=CongeMaladieController.nbCongeMaladie();
		congeMaladie.setText("Vous avez "+w+" conge maladie cliquez ici pour les traiter");
		
	}
	public void contratExpiree(MouseEvent event) throws IOException {
		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/ContratInterface2.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
	}
	public void demandeCongeAnnuel(MouseEvent event)throws IOException{
		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/CongeAnnuelInterface.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
	}
	public void demandeCongeMaladie(MouseEvent event)throws IOException{
		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/CongeMaladieInterface2.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		messagerie();
	}
	

}
