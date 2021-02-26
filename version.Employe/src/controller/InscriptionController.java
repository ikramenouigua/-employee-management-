package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.CompteEmployeDAO;
import DAO.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modele.CompteEmploye;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InscriptionController implements Initializable {
	@FXML private TextField textMatricule;
	@FXML private TextField textPrenom;
	@FXML private TextField textNom;
	@FXML private TextField textEmail;
	@FXML private PasswordField textmotdepasse;
	@FXML private PasswordField textmotdepasse2;
	
	 public static void infoBox(String infoMessage, String headerText, String title){
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setContentText(infoMessage);
	        alert.setTitle(title);
	        alert.setHeaderText(headerText);
	        alert.showAndWait();
	    }
	
	public void enregistrer(ActionEvent event) throws IOException, SQLException {
	   String 	matricule1=textMatricule.getText().toString();
	   String prenom=textPrenom.getText().toString();
	   String nom=textNom.getText().toString();
	   String email=textEmail.getText().toString();
	   String motDePasse=textmotdepasse.getText().toString();
	   String motDePasse2=textmotdepasse2.getText().toString();
	   if(motDePasse.compareTo(motDePasse2)==0) {
		   String sql="SELECT * from employe where  matricule = ? and prenom = ? and nom = ? and  email = ?";
		   
			   Connection con=ConnectionDatabase.connect();
			   PreparedStatement stat=con.prepareStatement(sql);
			   stat.setString(1, matricule1);
			   stat.setString(2, prenom);
			   stat.setString(3, nom);
			   stat.setString(4, email);
			   ResultSet result=stat.executeQuery();
			  
			   
		   if(result.next() ) {
		   CompteEmploye c=new CompteEmploye();
		   c.setMatricule(matricule1);
		   c.setPrenom(prenom);
		   c.setNom(nom);
		   c.setEmail(email);
		   c.setMotDePasse(motDePasse);
		   c.setRole("employe");
		   int status=CompteEmployeDAO.enregistrer(c);
		   Stage dialogStage;
		   Scene scene;
	    	infoBox("les infos sont bien enregisterer cliquez ici pour continuer", null, "Successful");
	    	Node node = (Node)event.getSource();
	         dialogStage = (Stage) node.getScene().getWindow();
	         dialogStage.close();
	         scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/connexion.fxml")));
	         dialogStage.setScene(scene);
	         dialogStage.show();
	    	
	   
		}
		else {
			infoBox("S'il vous plait remplis tout les champs", null, "Failed");
		}
		   
	   }else {
		   infoBox("les champs de mot de passe ne sont pas identique",null,"Failed");
	   }
		
	}
	
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
