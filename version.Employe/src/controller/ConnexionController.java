package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import DAO.ConnectionDatabase;


public class ConnexionController implements Initializable{
	@FXML private TextField textEmail;
	@FXML private PasswordField textMotdepasse;
	
	
	public void seConnecter(ActionEvent event) throws SQLException {
		Connection connection=ConnectionDatabase.connect();
		PreparedStatement preparedStatement;
		Stage dialogStage;
		Scene scene ;
		String email=textEmail.getText().toString();
		String motdepasse=textMotdepasse.getText().toString();
		String sql = "SELECT * FROM compteemploye WHERE role='employe' and email = ? and motDePasse = ?";
	
       
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motdepasse);
             ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                InscriptionController.infoBox("Please enter correct Login and Password", null, "Failed");
            }else{
                 String sql2="insert into userempl(login,passworde)  values(?,?)";
                		 preparedStatement=connection.prepareStatement(sql2);
                 preparedStatement.setString(1, email);
                 preparedStatement.setString(2, motdepasse);
                int st=preparedStatement.executeUpdate();
      		   connection.close();
                 
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/BienvenueInterface3.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
		
	}
	
     @FXML public void inscrivezVous(MouseEvent event) throws IOException {
    	 Stage dialogStage;
    	 Scene scene;
    	 Node node = (Node)event.getSource();
         dialogStage = (Stage) node.getScene().getWindow();
         dialogStage.close();
         scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/InscriptionInterface2.fxml")));
         dialogStage.setScene(scene);
         dialogStage.show();
     }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
