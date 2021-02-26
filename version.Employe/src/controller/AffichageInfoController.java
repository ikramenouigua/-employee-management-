package controller;

import java.io.File;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AffichageInfoController  implements Initializable{
    @FXML private TextField matricule;
    @FXML private TextField nom;
    @FXML private TextField prenom;
    @FXML private TextField cin;
    @FXML private TextField nationalite;
    @FXML private TextField ville;
    @FXML private TextField adresse;
    @FXML private TextField telephone;
    @FXML private TextField email;
    @FXML private TextField sexe;
    @FXML private TextField dateNaiss;
    @FXML private TextField statut;
    @FXML private TextField idContrat;
    @FXML private TextField dateDebut;
    @FXML private TextField dateFin;
    @FXML private TextField departement;
    @FXML private TextField poste;
    @FXML private TextField diplome;
    @FXML private TextField salaire;
    @FXML private ImageView imageView;
    private Image image;
    
    @FXML private ComboBox<String> choix=new ComboBox<String>();
    
    public void afficherImage(String matricule) throws SQLException, IOException {
    	Connection con=ConnectionDatabase.connect();
    	PreparedStatement stat;
    	String sql="Select image from employe where matricule='"+matricule+"'";
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
    	}
    }
	
    public void afficherInfo() throws SQLException, IOException {
    	Connection con=ConnectionDatabase.connect();
    	PreparedStatement stat;
    	String sql="select employe.matricule,employe.nom,employe.prenom,employe.cin,employe.nationalite,employe.ville,employe.addresse,employe.telephone,employe.email,employe.sexe,employe.datenaissance,employe.statutsocial,contrat.idcontrat,contrat.datedebut,contrat.datefin,contrat.departement,contrat.poste,contrat.diplome,contrat.salaire from employe ,contrat,compteemploye,userempl where employe.matricule=contrat.idemploye and employe.matricule=compteemploye.matricule and compteemploye.email=userempl.login" ;
    	stat=con.prepareStatement(sql);
    	ResultSet result=stat.executeQuery();
    	String mat=" ";
    	while(result.next()) {
    		mat=result.getString(1);
    		matricule.setText(result.getString(1));
    		nom.setText(result.getString(2));
    		prenom.setText(result.getString(3));
    		cin.setText(result.getString(4));
    		nationalite.setText(result.getString(5));
    		ville.setText(result.getString(6));
    		adresse.setText(result.getString(7));
    		telephone.setText(result.getString(8));
    		email.setText(result.getString(9));
    		sexe.setText(result.getString(10));
    		dateNaiss.setText(result.getString(11));
    		statut.setText(result.getString(12));
    		idContrat.setText(result.getString(13));
    		dateDebut.setText(result.getString(14));
    		dateFin.setText(result.getString(15));
    		departement.setText(result.getString(16));
    		poste.setText(result.getString(17));
    		diplome.setText(result.getString(18));
    		salaire.setText(result.getString(19));
    		
    	}
    	afficherImage(mat);
    	
    }
    public void choixUtilisateur(ActionEvent event) throws IOException, SQLException{
    	Stage dialogStage;
    	Scene scene;
    	if(choix.getValue().toString().equalsIgnoreCase("Retour")) {
    		Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/BienvenueInterface3.fxml")));
        //GestionController.viewEmploye();
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
		
			try {
				afficherInfo();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		choix.getItems().addAll("Retour","Deconnexion");
	}

}
