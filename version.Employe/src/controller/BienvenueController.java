package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.ConnectionDatabase;
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

public class BienvenueController implements Initializable{
    @FXML private ComboBox<String>  conge=new ComboBox<String>();
    @FXML private ComboBox<String> info=new ComboBox<String>();
    @FXML private TextField message;
    
    public int resultatCongeAnnuel() throws SQLException {
    	int x=0;
    	Connection con=ConnectionDatabase.connect();
    	PreparedStatement stat;
    	String sql="select reponse from compteemploye,reponseconge where compteemploye.matricule=reponseconge.matricule ";
    	stat=con.prepareStatement(sql);
    	ResultSet rs=stat.executeQuery();
    	while(rs.next()) {
    		x=1;
    		
    	}
    	return x;
    }
    
    public void messagerie() throws SQLException {
    	int x=resultatCongeAnnuel();
    	if(x>0) {
    		message.setText("1");
    	}
    	else {
    		message.setText("0");
    	}
    }
    public void afficherMessage(MouseEvent event) throws IOException, SQLException {
    	Connection con=ConnectionDatabase.connect();
    	PreparedStatement stat;
    	String resultat=" ";
    	String sql="select reponse from compteemploye,reponseconge,userempl where compteemploye.matricule=reponseconge.matricule and compteemploye.email=userempl.login";
    	stat=con.prepareStatement(sql);
    	ResultSet result=stat.executeQuery();
    	if(result.next()) {
    	    resultat=result.getString(1);
    	   InscriptionController.infoBox("Votre demande de conge est "+resultat+" " , null, "reponse");
    	   String sql2="delete from reponseconge";
    	   stat=con.prepareStatement(sql2);
           int resultSet = stat.executeUpdate();
    	}
    	
	}
    	
    public void choixConge(ActionEvent event) throws IOException{
    	Stage dialogStage;
    	Scene scene;
    	if(conge.getValue().toString()=="Conge Annuel") {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/CongeAnnuelInterface3.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
	 
    	if(conge.getValue().toString()=="Conge Maladie") {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/CongeMaladieInterface.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    } 
	}
    public void choixInfo(ActionEvent event) throws IOException{
    	Stage dialogStage;
    	Scene scene;
    	if(info.getValue().toString()=="Afficher infos") {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/AffichageInfoInterface.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		conge.getItems().addAll("Conge Annuel","Conge Maladie");
		info.getItems().addAll("Afficher infos");
		try {
			messagerie();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
