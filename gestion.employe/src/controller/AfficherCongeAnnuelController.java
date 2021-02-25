package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;

import DAO.ConnectionDatabase;
import Modele.CongeAnnuel;
import Modele.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AfficherCongeAnnuelController implements Initializable{
	
	@FXML private TextField filtrerField;
	
	@FXML private TableView<CongeAnnuel> table;
	@FXML private TableColumn<CongeAnnuel,String> matricule;
	@FXML private TableColumn<CongeAnnuel,String> numeroContrat;
	@FXML private TableColumn<CongeAnnuel,String> nom;
	@FXML private TableColumn<CongeAnnuel,String> prenom;
	@FXML private TableColumn<CongeAnnuel,String> departement;
	@FXML private TableColumn<CongeAnnuel,String> poste;
	@FXML private TableColumn<CongeAnnuel,String> dateDebut;
	@FXML private TableColumn<CongeAnnuel,String> dateFin;
	@FXML private TableColumn etat;
    public ObservableList<CongeAnnuel> data = FXCollections.observableArrayList();
    @FXML  private ComboBox<String> choix =new ComboBox<String>();
    public void congeAnnuel() {
    	LocalDate date1;
    	String etat1;
    	try {
    		Connection con=ConnectionDatabase.connect();
    		String sql="SELECT * FROM congeannuel where resultat =?";
    		PreparedStatement stat=con.prepareStatement(sql);
    		stat.setString(1,"Accepte");
    		ResultSet rs=stat.executeQuery();
    		
    		while(rs.next()) {
    			date1=LocalDate.parse(rs.getString(8));
       		 Instant instant=Instant.now();
       			LocalDate date=LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
       			if(date.compareTo(date1)>0) {
       				etat1="fini";
       			}else {
       				etat1="en cours";
       			}
    			data.add(new CongeAnnuel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),etat1,rs.getString(10)));
    		 
    		}
    		con.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	matricule.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("idEmploye"));
    	numeroContrat.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("idContrat"));
    	nom.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("nom"));
    	prenom.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("prenom"));
    	departement.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("departement"));
        poste.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("poste"));
    	dateDebut.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("dateDebut"));
    	dateFin.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("dateFin"));
        etat.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("etatTraitement"));
    	
        table.setItems(data);
       
        
    }
    FilteredList<CongeAnnuel> filtredData=new FilteredList<>(data,b->true);
    
    @FXML
	public void search(KeyEvent event) {
		
		 filtrerField.textProperty().addListener((observable,oldValue,newValue)->{
			 filtredData.setPredicate(CongeAnnuel ->{
				 if(newValue== null||newValue.isEmpty()) {
					 return true;
				 }
				 String lowerCaseFilter = newValue.toLowerCase();
				 if(CongeAnnuel.getDateDebut().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					 return true;
				 }else if(CongeAnnuel.getDepartement().toLowerCase().indexOf(lowerCaseFilter)!=-1){
					 return true;
				 }
				 else {
					 return false;
				 }
			 });
			});
		 
		 SortedList<CongeAnnuel> sortedData = new SortedList<>(filtredData);			
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			table.setItems(sortedData);
	
		
	
	}
    public void choixUtilisateur(ActionEvent event) throws IOException{
    	Stage dialogStage;
    	Scene scene;
    	if(choix.getValue().toString().equalsIgnoreCase("Retour")) {
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		congeAnnuel();
		choix.getItems().addAll("Retour","Deconnexion");
	}

}
