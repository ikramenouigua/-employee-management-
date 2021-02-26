package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.ConnectionDatabase;
import Modele.Contrat;
import DAO.ContratDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ContratController implements Initializable{
	@FXML private TableView<Contrat> table;
	@FXML private TableColumn<Contrat,String> idContrat;
	@FXML private TableColumn<Contrat,String> idEmploye;
	@FXML private TableColumn<Contrat,String> textdateDeb;
	@FXML private TableColumn<Contrat,String> textdateFin;
	@FXML private TableColumn<Contrat,String> textDep;
	@FXML private TableColumn<Contrat,String> textposte;
	@FXML private TableColumn<Contrat,String> textdiplome;
	@FXML private TableColumn<Contrat,Double> textsalaire;
	
	public ObservableList<Contrat> data = FXCollections.observableArrayList();
	
	@FXML private TextField textContrat;
    @FXML private TextField textIdEmp;
    @FXML private DatePicker textDebut;
    @FXML private DatePicker textFin;
    @FXML  private ComboBox<String> textDepartement =new ComboBox<String>();
    @FXML private TextField textPoste;
    @FXML private TextField textDiplome;
    @FXML private TextField textSalaire;
    @FXML  private ComboBox<String> choix =new ComboBox<String>();
    public void contratEpuisee() {
	try {
		Instant instant=Instant.now();
		LocalDate date=LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
		String dateNow=date.toString();
		Connection con=ConnectionDatabase.connect();
		String sql="SELECT * FROM Contrat where datefin <= ? ";
		PreparedStatement stat=con.prepareStatement(sql);
	     stat.setString(1, dateNow);
		ResultSet rs=stat.executeQuery();
		
		while(rs.next()) {
			data.add(new Contrat(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(5),rs.getString(7),rs.getDouble(8)));
		}
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	idContrat.setCellValueFactory(new PropertyValueFactory<Contrat,String>("numeroContrat"));
	idEmploye.setCellValueFactory(new PropertyValueFactory<Contrat,String>("idEmploye"));
	textdateDeb.setCellValueFactory(new PropertyValueFactory<Contrat,String>("dateDebut"));
	textdateFin.setCellValueFactory(new PropertyValueFactory<Contrat,String>("dateFin"));
	textDep.setCellValueFactory(new PropertyValueFactory<Contrat,String>("departement"));
    textposte.setCellValueFactory(new PropertyValueFactory<Contrat,String>("poste"));
	textdiplome.setCellValueFactory(new PropertyValueFactory<Contrat,String>("diplome"));
	textsalaire.setCellValueFactory(new PropertyValueFactory<Contrat,Double>("salaire"));
	table.setItems(data);
	}
    public void Selected(MouseEvent event) {
		int index=table.getSelectionModel().getSelectedIndex();
		if(index <= -1){
			return;
	}
		textDebut.setValue(null);
		textFin.setValue(null);
		
		textContrat.setText(idContrat.getCellData(index).toString());
		textIdEmp.setText(idEmploye.getCellData(index).toString());
		textDebut.setPromptText(textdateDeb.getCellData(index).toString());
		textFin.setPromptText(textdateFin.getCellData(index).toString());
		textDepartement.setValue(textDep.getCellData(index).toString());
		textPoste.setText(textposte.getCellData(index).toString());
		textDiplome.setText(textdiplome.getCellData(index).toString());
		textSalaire.setText(textsalaire.getCellData(index).toString());
		//textsalaire.sett(Double.valueOf(x));
		
			
	
	}
    public static int nbContratEpuisee() {
    	int nb=0;
    	try {
    		
    		Instant instant=Instant.now();
    		LocalDate date=LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
    		String dateNow=date.toString();
    		Connection con=ConnectionDatabase.connect();
    		String sql="SELECT * FROM Contrat where datefin <= ? ";
    		PreparedStatement stat=con.prepareStatement(sql);
    	     stat.setString(1, dateNow);
    		ResultSet rs=stat.executeQuery();
    		
    		while(rs.next()) {
    	    nb++;
    		}
    		con.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return nb;
    	
    	
    }
    public void Actualiser() {
    	data.clear();
    	contratEpuisee();
    }
    public void initialiser() {
    	textContrat.setText(" ");
    	textIdEmp.setText(" ");
    	textPoste.setText(" ");
    	textDiplome.setText(" ");
    	textSalaire.setText(" ");
    	LocalDate dateD=textDebut.getValue(),dateF=textFin.getValue();
    	if(dateD==null) {
    		textDebut.setPromptText(null);
    	}else {
    		textDebut.setValue(null);
    	}
    	if(dateF==null) {
    		textFin.setPromptText(null);
    	}else {
    		textFin.setValue(null);
    	}
    }
    public void supprimerEmployeContrat() {
    		Connection connection = ConnectionDatabase.connect();
    	    PreparedStatement preparedStatement = null;
    	    int resultSet ;
    	    
    	    

    			String matsupprime=textIdEmp.getText().toString();
    			
    			 String sql="DELETE FROM employe where matricule = ? ";
    			 String sql2="DELETE FROM contrat where idemploye = ? ";
    			try{
    	            preparedStatement = connection.prepareStatement(sql);
    	            preparedStatement.setString(1, matsupprime);
    	            System.out.println(matsupprime);
    	            resultSet = preparedStatement.executeUpdate();
    	            
    	            preparedStatement = connection.prepareStatement(sql2);
    	            preparedStatement.setString(1, matsupprime);
    	            resultSet = preparedStatement.executeUpdate();
    	            if(resultSet >0){
    	            	ControllerLogin.infoBox("Les infos du contrat et employe sont  supprimer", null, "successful");
    	                
    	            }else {
    	            	ControllerLogin.infoBox("Cette matricule est introuvable Verifiez", null, "Failed");
    	            }
    	           
    	        }
    	        catch(Exception e){
    	            e.printStackTrace();
    	        }
    		Actualiser();	     
    	}
    public void supprimer(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("voulez vous supprimer l'employe effectivement de la bse de donnee?");
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	supprimerEmployeContrat();
        	initialiser();
        }
        }
   @FXML 
    public void renouvler(ActionEvent event) {
		try {
			 
			 
    String numeroContrat=textContrat.getText().toString();
	 String idEmploye1=textIdEmp.getText().toString();
	 String dateD;
	 String dateF;
	 String departement=textDepartement.getValue();
	 String diplome1=textDiplome.getText().toString();
	 String poste1=textPoste.getText().toString();
	 String salair=textSalaire.getText().toString();
	 double salaire=Double.valueOf(salair);
	 if(textDebut.getValue()==null) {
			dateD=textDebut.getPromptText();
		}
		else {
			dateD=textDebut.getValue().toString();
		}
	 
	 if(textFin.getValue()==null) {
			dateF=textFin.getPromptText();
		}
		else {
			dateF=textFin.getValue().toString();
		}
	 Connection con=ConnectionDatabase.connect();
	 String sql1="update contrat set idemploye='"+idEmploye1+"',idcontrat='"+numeroContrat+"',datedebut='"+dateD+"',datefin='"+dateF+"',departement='"+departement+"',poste='"+poste1+"',diplome='"+diplome1+"',salaire= '"+salaire+"'where idcontrat='"+numeroContrat+"'";
	   PreparedStatement stat=con.prepareStatement(sql1);
		int x=stat.executeUpdate();
		if(x>0) {
	 ControllerLogin.infoBox("les infos sont bien changer", null, "Successful");
		}
	
}catch(Exception e) {
	e.printStackTrace();
}
		Actualiser();
    }
    public void choixUtilisateur(ActionEvent event) throws IOException{
    	Stage dialogStage;
    	Scene scene;
    	if(choix.getValue().toString().equalsIgnoreCase("Retour")) {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/MessagerieInterface.fxml")));
        //GestionController.viewEmploye();
        dialogStage.setScene(scene);
        dialogStage.show();
    }if(choix.getValue().toString().equalsIgnoreCase("Bienvenue")) {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/BienvenueInterface2.fxml")));
        //GestionController.viewEmploye();
        dialogStage.setScene(scene);
        dialogStage.show();
    }
	 
    	if(choix.getValue().toString().equalsIgnoreCase("Deconnexion")) {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/LoginInterface2.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    } 
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		contratEpuisee();
		choix.getItems().addAll("Retour","Bienvenue","Deconnexion");
		textDepartement.getItems().addAll("direction et administration générale ","achat","finance et comptabilité","logistique","marketing et commerciale","production"," recherche et développement","ressources humaines");
	
		textDebut.setValue(LocalDate.now());
		  final Callback<DatePicker,DateCell> dayCellFactory= new Callback<DatePicker, DateCell>() {
				@Override
				public DateCell call(final DatePicker datePicker) {
					return new DateCell() {
						@Override
						public void updateItem(LocalDate item,boolean empty) {
							super.updateItem(item, empty);
							if(item.isBefore(textDebut.getValue().plusDays(1))) {
								setDisable(true);
								setStyle("-fx-background-color:#EEEEEE;");
							}
						}
					};
				}
			};
			textFin.setDayCellFactory(dayCellFactory);
			textFin.setValue(textDebut.getValue().plusDays(1));
	}

}
