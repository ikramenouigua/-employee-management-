package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.CompteEmployeDAO;
import DAO.CongeAnnuelDAO;
import DAO.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.CongeAnnuel;

public class CongeAnnuelController implements Initializable {
       @FXML private TextField textMatricule;
       @FXML private TextField textContrat;
       @FXML  private TextField textNom;
       @FXML  private TextField textPrenom;
       @FXML  private ComboBox<String> textDepartement =new ComboBox<String>();
       @FXML private TextField textPoste;
       @FXML private DatePicker textDebut;
       @FXML private DatePicker textFin;
       
       @FXML  private ComboBox<String> choix =new ComboBox<String>();
       public void congeAnnuel() throws SQLException {
    	   String sql="select employe.matricule,contrat.idcontrat,employe.nom,employe.prenom,contrat.departement,contrat.poste from employe,contrat,compteemploye,userempl where  employe.matricule=contrat.idemploye and employe.matricule=compteemploye.matricule and compteemploye.email=userempl.login" ;
    	   Connection con=ConnectionDatabase.connect();
         	PreparedStatement stat;
         	stat=con.prepareStatement(sql);
         	ResultSet result=stat.executeQuery();
         	 if(result.next()) {
         		 textMatricule.setText(result.getString(1));
         		 textContrat.setText(result.getString(2));
         		 textNom.setText(result.getString(3));
         		 textPrenom.setText(result.getString(4));
         		 textDepartement.setValue(result.getString(5));
         		 textPoste.setText(result.getString(6));
         		
         	 }
       }
     
       public void envoiCongeAnnuel(ActionEvent event) throws SQLException {
    	   
    	String matricule=textMatricule.getText().toString();
    	String idContrat=textContrat.getText().toString();
    	String nom=textNom.getText().toString();
    	String prenom=textPrenom.getText().toString();
    	String departement=textDepartement.getValue();
    	String poste=textPoste.getText().toString();
    	String dateDebut=textDebut.getValue().toString();
    	String dateFin=textFin.getValue().toString();
    	  
    	CongeAnnuel C=new CongeAnnuel();
 		 C.setIdEmploye(matricule);
 		 C.setIdContrat(idContrat);
 		 C.setNom(nom);
 		 C.setPrenom(prenom);
 		 C.setDepartement(departement);
 		 C.setPoste(poste);
 		 C.setDateDebut(dateDebut);
 		 C.setDateFin(dateFin);
 		 C.setEtatTraitement("Non traite");
 		 C.setResultat(" ");
 		int status=CongeAnnuelDAO.enregistrer(C);
  		if(status>0) {
  		InscriptionController.infoBox("Le demande de conge est envoye",null,"Successful");
  			 
  		}
  	 else {
  		InscriptionController.infoBox("Verifiez vos infos ",null,"Failed");
  	 }
    	
    	  
       }
       
       public void choixUtilisateur(ActionEvent event) throws IOException, SQLException{
       	Stage dialogStage;
       	Scene scene;
       	if(choix.getValue().toString().equalsIgnoreCase("Retour")) {
       		Node node = (Node)event.getSource();
           dialogStage = (Stage) node.getScene().getWindow();
           dialogStage.close();
           scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/BienvenueInterface3.fxml")));
           dialogStage.setScene(scene);
           dialogStage.show();
           
       }
   	 
       	if(choix.getValue().toString().equalsIgnoreCase("Deconnexion")) {
       		
       		Node node = (Node)event.getSource();
           dialogStage = (Stage) node.getScene().getWindow();
           dialogStage.close();
           scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/Connexion.fxml")));
           dialogStage.setScene(scene);
           dialogStage.show();
           Connection con=ConnectionDatabase.connect();
           PreparedStatement stat;
           String sql="delete from userempl";
           stat=con.prepareStatement(sql);
           int resultSet = stat.executeUpdate();
           
       } 
   	}
       
       
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		textDepartement.getItems().addAll("direction et administration générale ","achat","finance et comptabilité","logistique","marketing et commerciale","production"," recherche et développement","ressources humaines");
		choix.getItems().addAll("Retour","Deconnexion");
		try {
			congeAnnuel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
