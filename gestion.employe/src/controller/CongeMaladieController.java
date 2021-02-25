package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.ConnectionDatabase;
import Modele.CongeAnnuel;
import Modele.CongeMaladie;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CongeMaladieController implements Initializable{
	@FXML private TableView<CongeMaladie> table;
	@FXML private TableColumn<CongeMaladie,String> matricule;
	@FXML private TableColumn<CongeMaladie,String> idContrat;
	@FXML private TableColumn<CongeMaladie,String> nom;
	@FXML private TableColumn<CongeMaladie,String> prenom;
	@FXML private TableColumn<CongeMaladie,String> departement;
	@FXML private TableColumn<CongeMaladie,String> poste;
	@FXML private TableColumn<CongeMaladie,String> dateDebut;
	@FXML private TableColumn<CongeMaladie,String> dateFin;
	@FXML private TableColumn<CongeMaladie,String> etat;
	@FXML private TableColumn<CongeMaladie,String> resultat;
    public ObservableList<CongeMaladie> data = FXCollections.observableArrayList();
    
    @FXML  private ComboBox<String> choix =new ComboBox<String>();
    
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
    @FXML private ImageView imageView;
    private Image image;
    private final Desktop  deskTop=Desktop.getDesktop();
    
    @FXML
    public void congeMaladie() {
	try {
		Connection con=ConnectionDatabase.connect();
		String sql="SELECT * FROM congemaladie where resultat =?";
		PreparedStatement stat=con.prepareStatement(sql);
		stat.setString(1," ");
		ResultSet rs=stat.executeQuery();
		
		while(rs.next()) {
			data.add(new CongeMaladie(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(10),rs.getString(11)));
		}
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	matricule.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("idEmploye"));
	idContrat.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("idContrat"));
	nom.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("nom"));
	prenom.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("prenom"));
	departement.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("departement"));
    poste.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("poste"));
	dateDebut.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("dateDebut"));
	dateFin.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("dateFin"));
	etat.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("etatTraitement"));
	resultat.setCellValueFactory(new PropertyValueFactory<CongeMaladie,String>("resultat"));
	
    table.setItems(data);
   
    
}
    public static int nbCongeMaladie() {
    	int x=0;
    	try {
    		Connection con=ConnectionDatabase.connect();
    		String sql="SELECT * FROM congemaladie where resultat =?";
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
    public void selectionnee(MouseEvent event) throws SQLException, IOException {
		int index=table.getSelectionModel().getSelectedIndex();
		if(index <= -1){
			return;
	}
		String mat=matricule.getCellData(index).toString();
		
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
		
		afficherCertificat(mat);	
	
	}
    public void afficherCertificat(String matricule) throws SQLException, IOException {
    	Connection con=ConnectionDatabase.connect();
    	PreparedStatement stat;
    	String sql="Select certificatmaladie from congemaladie where idemploye='"+matricule+"'";
    	stat=con.prepareStatement(sql);
    	ResultSet rs=stat.executeQuery();
    	if(rs.next()) {
    		InputStream is=rs.getBinaryStream(1);
    		
				OutputStream os= new FileOutputStream(new File("photos.png"));
			
    		byte[] contents =new byte[1024];
    		int size=0;
    		while((size=is.read(contents))!=-1) {
    			os.write(contents, 0, size);
    		}
    		image =new Image("File:photos.png",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
    		imageView.setImage(image);
    		//deskTop.open(image);
    	}
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
    	imageView.setImage(null);
    	
    }
    public void actualiser() {
    	data.clear();
    	congeMaladie();
    }
	public void enregistrer(ActionEvent event) {
		
		try {
			 String matricule1=textMatricule.getText().toString();
			String resultat1=textResultat.getValue();
			String etat1="Traite";
			 Connection con=ConnectionDatabase.connect();
			 String sql="update congemaladie set resultat='"+resultat1+"',etat='"+etat1+"'where idemploye='"+matricule1+"'";
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		congeMaladie();
		textEtat.getItems().addAll("Traite","Non Traite");
		textResultat.getItems().addAll("Accepte","Refuse");
		choix.getItems().addAll("Retour","Bienvenue","Deconnexion");
		
	}

}
