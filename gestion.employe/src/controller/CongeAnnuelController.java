package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import DAO.ConnectionDatabase;
import Modele.CongeAnnuel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CongeAnnuelController implements Initializable{
	@FXML private TableView<CongeAnnuel> table;
	@FXML private TableColumn<CongeAnnuel,String> matricule;
	@FXML private TableColumn<CongeAnnuel,String> idContrat;
	@FXML private TableColumn<CongeAnnuel,String> nom;
	@FXML private TableColumn<CongeAnnuel,String> prenom;
	@FXML private TableColumn<CongeAnnuel,String> departement;
	@FXML private TableColumn<CongeAnnuel,String> poste;
	@FXML private TableColumn<CongeAnnuel,String> dateDebut;
	@FXML private TableColumn<CongeAnnuel,String> dateFin;
	@FXML private TableColumn<CongeAnnuel,String> etat;
	@FXML private TableColumn<CongeAnnuel,String> resultat;
    public ObservableList<CongeAnnuel> data = FXCollections.observableArrayList();
    
    @FXML private TextField textMatricule;
    @FXML private TextField textContrat;
    @FXML private TextField textNom;
    @FXML private TextField textPrenom;
    @FXML private TextField textDepartement;
    @FXML private TextField textPoste;
    @FXML private DatePicker textDebut;
    @FXML private DatePicker textFin;
    @FXML  private ComboBox<String> textEtat =new ComboBox<String>();
    @FXML  private ComboBox<String> textResultat =new ComboBox<String>();
    @FXML  private ComboBox<String> choix =new ComboBox<String>();
    
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
    @FXML
    public void congeAnnuel() {
	try {
		Connection con=ConnectionDatabase.connect();
		String sql="SELECT * FROM congeannuel where resultat =?";
		PreparedStatement stat=con.prepareStatement(sql);
		stat.setString(1," ");
		ResultSet rs=stat.executeQuery();
		
		while(rs.next()) {
			data.add(new CongeAnnuel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
		}
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	matricule.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("idEmploye"));
	idContrat.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("idContrat"));
	nom.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("nom"));
	prenom.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("prenom"));
	departement.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("departement"));
    poste.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("poste"));
	dateDebut.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("dateDebut"));
	dateFin.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("dateFin"));
	etat.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("etatTraitement"));
	resultat.setCellValueFactory(new PropertyValueFactory<CongeAnnuel,String>("resultat"));
	
    table.setItems(data);
   
    
}
    public static int nbCongeAnnuel() {
    	int x=0;
    	try {
    		Connection con=ConnectionDatabase.connect();
    		String sql="SELECT * FROM congeannuel where resultat =?";
    		PreparedStatement stat=con.prepareStatement(sql);
    		stat.setString(1," ");
    		ResultSet rs=stat.executeQuery();
    		
    		while(rs.next()) {
    			x++;
    		}
    		con.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return x;
    }
    public void selectionnee(MouseEvent event) {
		int index=table.getSelectionModel().getSelectedIndex();
		if(index <= -1){
			return;
	}
		
		textMatricule.setText(matricule.getCellData(index).toString());
		textContrat.setText(idContrat.getCellData(index).toString());
		textNom.setText(nom.getCellData(index).toString());
		textPrenom.setText(prenom.getCellData(index).toString());
		textDepartement.setText(departement.getCellData(index).toString());
		textPoste.setText(poste.getCellData(index).toString());
		textDebut.setPromptText(dateDebut.getCellData(index).toString());
		textFin.setPromptText(dateFin.getCellData(index).toString());
		textEtat.setValue(etat.getCellData(index).toString());
		textResultat.setValue(resultat.getCellData(index).toString());
		//textsalaire.sett(Double.valueOf(x));
		
			
	
	}
    public void initialiser() {
    	textMatricule.setText(" ");
    	textContrat.setText(" ");
    	textNom.setText(" ");
    	textPrenom.setText(" ");
    	textDepartement.setText(" ");
    	textPoste.setText(" ");
    	textDebut.setPromptText(null);
    	textFin.setPromptText(null);
    	textEtat.setValue(" ");
    	textResultat.setValue(" ");
    	
    }
    public void actualiser() {
    	data.clear();
    	congeAnnuel();
    }
	public void enregistrer(ActionEvent event) {
		
		try {
			 String matricule1=textMatricule.getText().toString();
			String resultat1=textResultat.getValue();
			String etat1="Traite";
			 Connection con=ConnectionDatabase.connect();
			 String sql="update congeannuel set resultat='"+resultat1+"',etat='"+etat1+"'where idemploye='"+matricule1+"'";
			 PreparedStatement stat=con.prepareStatement(sql);
				int x=stat.executeUpdate();
				if(x>0) {
					 ControllerLogin.infoBox("la demande est traite ", null, "Successful");
					 actualiser();
					 initialiser();
						
				String sql2="insert into reponseconge(matricule,reponse) values(?,?)";
				stat=con.prepareStatement(sql2);
				stat.setString(1, matricule1);
				stat.setString(2, resultat1);
				int st=stat.executeUpdate();
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		congeAnnuel();
		textEtat.getItems().addAll("Traite","Non Traite");
		textResultat.getItems().addAll("Accepte","Refuse");
		choix.getItems().addAll("Retour","Bienvenue","Deconnexion");
		
		
	}

}
