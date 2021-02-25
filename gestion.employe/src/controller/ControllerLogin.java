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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import DAO.ConnectionDatabase;

public class ControllerLogin implements Initializable{
	    @FXML
	    private TextField textLogin;
	    
	    @FXML  private PasswordField textPassword;
	    
	    Stage dialogStage = new Stage();
	    Scene scene;
	    
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	 
	    public ControllerLogin() throws Exception {
	        connection = ConnectionDatabase.connect();
	    }
	    
	    
	    
	    public void loginAction(ActionEvent event){
	        String login = textLogin.getText().toString();
	        String password = textPassword.getText().toString();
	    
	        String sql = "SELECT email,motdepasse FROM compteemploye WHERE role='RH' and email = ? and motdepasse = ?";
	        
	        try{
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, login);
	            preparedStatement.setString(2, password);
	            resultSet = preparedStatement.executeQuery();
	            if(!resultSet.next()){
	                infoBox("Please enter correct Login and Password", null, "Failed");
	            }else{
	                
	                Node node = (Node)event.getSource();
	                dialogStage = (Stage) node.getScene().getWindow();
	                dialogStage.close();
	                scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/BienvenueInterface2.fxml")));
	                dialogStage.setScene(scene);
	                dialogStage.show();
	            }
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        
	    }
	    public void inscrivezVous(MouseEvent event) throws IOException {
	    	Node node = (Node)event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/InscriptionInterface2.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
	    }
	    
	    public static void infoBox(String infoMessage, String headerText, String title){
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setContentText(infoMessage);
	        alert.setTitle(title);
	        alert.setHeaderText(headerText);
	        alert.showAndWait();
	    }
	    
	    
	    
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        
	        
	    }    
	    

}
