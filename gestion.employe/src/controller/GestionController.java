package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.ConnectionDatabase;
import DAO.ContratDAO;
import DAO.EmployeDAO;
import Modele.Contrat;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import Modele.Employe;

public class GestionController implements Initializable{
	Stage dialogStage = new Stage();
    Scene scene;
	@FXML private TableView<Employe> table;
	@FXML private TableColumn<Employe,String> Amatricule;
	@FXML private TableColumn<Employe,String> Anom;
	@FXML private TableColumn<Employe,String> Aprenom;
	@FXML private TableColumn<Employe,String> ACIN;
	@FXML private TableColumn<Employe,String> Anationalite;
	@FXML private TableColumn<Employe,String> Aville;
	@FXML private TableColumn<Employe,String> Aaddresse;
	@FXML private TableColumn<Employe,String> Atelephone;
	@FXML private TableColumn<Employe,String> Aemail;
	@FXML private TableColumn<Employe,String> Asexe;
	@FXML private TableColumn<Employe,String> Adate;
	@FXML private TableColumn<Employe,String> Astatut;
	
	@FXML  private ComboBox<String> sexe =new ComboBox<String>();
    @FXML private ComboBox<String> ville=new ComboBox<String>();

    @FXML private ComboBox<String> statut=new ComboBox<String>();
    @FXML private ComboBox<String> textDepartement=new ComboBox<String>();
    @FXML private ComboBox<String> nationalite=new ComboBox<String>();
    @FXML private TextField matricule;
    @FXML private TextField nom;
    @FXML private TextField prenom;
    @FXML private TextField cin;
    
    @FXML private TextField addresse;
    @FXML private TextField telephone;
    @FXML private TextField email;
   @FXML private DatePicker dateNaiss;
   
   @FXML private TextField idContrat;
	 @FXML private TextField mat;
	 @FXML private DatePicker dateDeb;
	 @FXML private DatePicker dateFin;
	 @FXML private TextField diplome;
	 @FXML private TextField poste;
	 @FXML private TextField salaire;
	 
	 @FXML private TextField filtrerField;
	
	 @FXML private AnchorPane anchorPane;
	 private FileChooser fileChooser;
     private File file;
     private Stage stage;
     @FXML private ImageView imageView;
     private Image image;
     private FileInputStream fis;
	 @FXML  private ComboBox<String> choix =new ComboBox<String>();
	public ObservableList<Employe> data = FXCollections.observableArrayList();
	 public void parcourir(ActionEvent event )throws IOException {
   	  stage=(Stage) anchorPane.getScene().getWindow();
   	file=fileChooser.showOpenDialog(stage); 
   	//deskTop.open(file);
   	if(file !=null) {
   		System.out.println(""+file.getAbsolutePath());
   		image=new Image(file.getAbsoluteFile().toURL().toString(),imageView.getFitWidth(),imageView.getFitHeight(),true,true);
   		imageView.setImage(image);
   		imageView.setPreserveRatio(true);
   	}
   	
   		
     }
	public void choixUser(ActionEvent event) throws IOException{
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
	//charger les information des employes dans tableView
	@FXML
    public void viewEmploye() {
	try {
		Connection con=ConnectionDatabase.connect();
		String sql="SELECT * FROM employe";
		PreparedStatement stat=con.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		
		while(rs.next()) {
			data.add(new Employe(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
		}
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	Amatricule.setCellValueFactory(new PropertyValueFactory<Employe,String>("matricule"));
	Anom.setCellValueFactory(new PropertyValueFactory<Employe,String>("nom"));
	Aprenom.setCellValueFactory(new PropertyValueFactory<Employe,String>("prenom"));
	ACIN.setCellValueFactory(new PropertyValueFactory<Employe,String>("CIN"));
    Anationalite.setCellValueFactory(new PropertyValueFactory<Employe,String>("nationalite"));
	Aville.setCellValueFactory(new PropertyValueFactory<Employe,String>("ville"));
	Aaddresse.setCellValueFactory(new PropertyValueFactory<Employe,String>("addresse"));
	Atelephone.setCellValueFactory(new PropertyValueFactory<Employe,String>("telephone"));
	Aemail.setCellValueFactory(new PropertyValueFactory<Employe,String>("email"));
	Asexe.setCellValueFactory(new PropertyValueFactory<Employe,String>("sexe"));
	Adate.setCellValueFactory(new PropertyValueFactory<Employe,String>("dateNaissance"));
	Astatut.setCellValueFactory(new PropertyValueFactory<Employe,String>("statutSocial"));
    table.setItems(data);
   
    
}
	FilteredList<Employe> filtredData=new FilteredList<>(data,b->true);
	public void Actualiser() {
		data.clear();
		viewEmploye();
		
	}
	public  void ajouterEmploye(ActionEvent event) throws Exception {
		String mat1 = matricule.getText().toString();
		String nom1 = nom.getText().toString();
		String prenom1 = prenom.getText().toString();
		String CIN1= cin.getText().toString();
		String nationalite1 = nationalite.getValue();
		
		String addresse1 = addresse.getText().toString();
		String telephone1 = telephone.getText().toString();
		String email1 = email.getText().toString();
		LocalDate dateNaiss1=dateNaiss.getValue();
		String date=dateNaiss1.toString();
		
		String ville1 = ville.getValue();
		String sexee=sexe.getValue();
		String statut_social=statut.getValue();
		
			
		fis=new FileInputStream(file);
		//creation d'un employe
		Employe E=new Employe();
		E.setMatricule(mat1);
		E.setNom(nom1);
		E.setPrenom(prenom1);
		E.setNationalite(nationalite1);
		E.setCIN(CIN1);
		E.setVille(ville1);
		E.setAddresse(addresse1);
		E.setTelephone(telephone1);
		E.setEmail(email1);
		E.setSexe(sexee);
		E.setDateNaissance(date);
		E.setStatutSocial(statut_social);
		
	    int status=EmployeDAO.enregistrer(E,fis,file);
	    String numeroContrat=idContrat.getText().toString();
		 String idEmploye=mat.getText().toString();
		 LocalDate dateDebut=dateDeb.getValue();
		 String dateD=dateDebut.toString();
		 LocalDate dateFin1=dateFin.getValue();
		 String dateF=dateFin1.toString();
		 String departement=textDepartement.getValue();
		 String diplome1=diplome.getText().toString();
		 String poste1=poste.getText().toString();
		 String salair=salaire.getText().toString();
		 double salaire=Double.valueOf(salair);
		 
		 
		 Contrat C=new Contrat();
		 
		 C.setIdEmploye(idEmploye);
		 C.setNumeroContrat(numeroContrat);
		 C.setDateDebut(dateD);
		 C.setDateFin(dateF);
		 C.setDepartement(departement);
		 C.setDiplome(diplome1);
		 C.setPoste(poste1);
		 C.setSalaire(salaire);
		 int x=ContratDAO.enregistrer(C);
		 if(x!=0 && status !=0) {
	    	ControllerLogin.infoBox("les infos sont bien enregisterer cliquez ici pour continuer", null, "Successful");
		 }
		 else {
			 ControllerLogin.infoBox("l'employe n'est pas ajouter", null, "Failed");
		 }
	   Actualiser();
		}
	public void initialiser(ActionEvent event) {
		matricule.setText(" ");
		nom.setText(" ");
		prenom.setText(" ");
		cin.setText(" ");
		nationalite.setValue(" ");
		addresse.setText(" ");
		telephone.setText(" ");
		idContrat.setText(" ");
		mat.setText(" ");
		textDepartement.setValue(" ");
		diplome.setText(" ");
		poste.setText(" ");
		salaire.setText(" ");
		email.setText(" ");
		sexe.setValue(" ");
		statut.setValue(" ");
		ville.setValue(" ");
		dateDeb.setPromptText(" ");
		dateFin.setPromptText(" ");
		LocalDate dateD=dateDeb.getValue();
		LocalDate dateF=dateFin.getValue();
		if(dateD==null) {
			 dateDeb.setPromptText(null);
		 }
		else {
			dateDeb.setValue(null);
		}
		 if(dateF==null) {
			 dateFin.setPromptText(null);
		 }
		 else {
			 dateFin.setValue(null);
		 }
		 if(dateNaiss.getValue()==null) {
				dateNaiss.setPromptText(null);
			}
			else {
				dateNaiss.setValue(null);
			}
		 imageView.setImage(null);
	}
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
	
	@FXML
	void getSelected(MouseEvent event) throws SQLException, IOException {
		int index=table.getSelectionModel().getSelectedIndex();
		if(index <= -1){
			return;
	}
		String mat11=Amatricule.getCellData(index).toString();
		matricule.setText(Amatricule.getCellData(index).toString());
		nom.setText(Anom.getCellData(index).toString());
		prenom.setText(Aprenom.getCellData(index).toString());
		cin.setText(ACIN.getCellData(index).toString());
		nationalite.setValue(Anationalite.getCellData(index).toString());
		ville.setValue(Aville.getCellData(index).toString());
		addresse.setText(Aaddresse.getCellData(index).toString());
		telephone.setText(Atelephone.getCellData(index).toString());
		email.setText(Aemail.getCellData(index).toString());
		sexe.setValue(Asexe.getCellData(index).toString());
		dateNaiss.setPromptText(Adate.getCellData(index).toString());
		statut.setValue(Astatut.getCellData(index).toString());
		afficherImage(mat11);
		
		
		try {
			String mat1=matricule.getText().toString();
			Connection con=ConnectionDatabase.connect();
			String sql="SELECT * FROM contrat where idemploye = ? ";
			PreparedStatement stat=con.prepareStatement(sql);
			stat.setString(1, mat1);
			ResultSet rs=stat.executeQuery();
			System.out.println(rs);
			
		
			while(rs.next()) {
				mat.setText(rs.getString(1));
				idContrat.setText(rs.getString(2));
				dateDeb.setPromptText(rs.getString(3));
				dateFin.setPromptText(rs.getString(4));
				textDepartement.setValue(rs.getString(5));
				poste.setText(rs.getString(6));
				diplome.setText(rs.getString(7));
				
				salaire.setText(rs.getString(8));
				
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	public void supprimer() {
		Connection connection = ConnectionDatabase.connect();
	    PreparedStatement preparedStatement = null;
	    int resultSet ;
	    
	    

			String matsupprime=matricule.getText().toString();
			
			 String sql="DELETE FROM employe where matricule = ? ";
			 String sql2="DELETE FROM contrat where idemploye = ? ";
			try{
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, matsupprime);
	            resultSet = preparedStatement.executeUpdate();
	            
	            preparedStatement = connection.prepareStatement(sql2);
	            preparedStatement.setString(1, matsupprime);
	            resultSet = preparedStatement.executeUpdate();
	            if(resultSet >0){
	            	ControllerLogin.infoBox("employe est bien supprimer", null, "successful");
	                
	            }else {
	            	ControllerLogin.infoBox("Cette matricule est introuvable Verifiez", null, "Failed");
	            }
	           
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
			
	Actualiser();        
	}
	public void supprimerEmploye(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("voulez vous supprimer l'employe définitivement de la base de donnee?");
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	supprimer();
        	initialiser(event);
        }
        }
       
	
	public void modifierEmploye(ActionEvent event) {
		try {
			 Connection con=ConnectionDatabase.connect();
			String mat1 = matricule.getText().toString();
			String nom1 = nom.getText().toString();
			String prenom1 = prenom.getText().toString();
			String CIN1= cin.getText().toString();
			
			
			String addresse1 = addresse.getText().toString();
			String telephone1 = telephone.getText().toString();
			String email1 = email.getText().toString();
			//LocalDate dateNaiss1=dateNaiss.getPromptTe
			String date;
			String ville1,sexee,statut_social,nationalite1;
			if(dateNaiss.getValue()==null) {
				date=dateNaiss.getPromptText();
			}
			else {
				date=dateNaiss.getValue().toString();
			}
			if(ville.getValue()==null) {
				ville1=ville.getPromptText();
			}else {
				ville1=ville.getValue();
			}
			if(nationalite.getValue()==null) {
				nationalite1=nationalite.getPromptText();
			}else {
				nationalite1=nationalite.getValue();
			}
			if(sexe.getValue()==null) {
				sexee=sexe.getPromptText();
			}else {
				sexee=sexe.getValue();
			}
			if(statut.getValue()==null) {
				statut_social=statut.getPromptText();
			}else {
				statut_social=statut.getValue();
			}
			
			
			String sql="update employe set matricule= '"+mat1+"',nom='"+nom1+"',prenom='"+prenom1+"',cin='"+CIN1+"',nationalite='"+nationalite1+"',ville='"+ville1+"',addresse='"+addresse1+"',telephone='"+telephone1+"',email='"+email1+"',sexe='"+sexee+"',datenaissance='"+date+"',statutsocial='"+statut_social+"'where matricule='"+mat1+"'";
			PreparedStatement stat=con.prepareStatement(sql);
			stat.execute();
		    String numeroContrat=idContrat.getText().toString();
			 String idEmploye=mat.getText().toString();
			 String dateD;
			 String dateF;
			 String departement1=textDepartement.getValue();
			 String diplome1=diplome.getText().toString();
			 String poste1=poste.getText().toString();
			 String salair=salaire.getText().toString();
			 double salaire=Double.valueOf(salair);
			 if(dateDeb.getValue()==null) {
					dateD=dateDeb.getPromptText();
				}
				else {
					dateD=dateDeb.getValue().toString();
				}
			 
			 if(dateFin.getValue()==null) {
					dateF=dateFin.getPromptText();
				}
				else {
					dateF=dateFin.getValue().toString();
				}
			 String sql2="update contrat set idemploye= '"+idEmploye+"',idContrat='"+numeroContrat+"',datedebut='"+dateD+"',dateFin='"+dateF+"',departement='"+departement1+"',diplome='"+diplome1+"',poste='"+poste1+"',salaire= '"+salaire+"'where idemploye='"+idEmploye+"'";
			  stat=con.prepareStatement(sql2);
				stat.execute();
			 ControllerLogin.infoBox("les infos sont bien change", null, "Successful");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		initialiser(event);
		Actualiser();
	}
	
	@FXML
	public void search(KeyEvent event) {
		
		 filtrerField.textProperty().addListener((observable,oldValue,newValue)->{
			 filtredData.setPredicate(Employe ->{
				 if(newValue== null||newValue.isEmpty()) {
					 return true;
				 }
				 String lowerCaseFilter = newValue.toLowerCase();
				 if(Employe.getMatricule().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					 return true;
				 }else if(Employe.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
					 return true;
				 }
				 else {
					 return false;
				 }
			 });
			});
		 
		 SortedList<Employe> sortedData = new SortedList<>(filtredData);			
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			table.setItems(sortedData);
	
		
	
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
            viewEmploye();
        choix.getItems().addAll("Retour","Deconnexion");   
		sexe.getItems().addAll("Femme","Homme");
		 ville.getItems().addAll("AL HAJEB","AGADIR","AL HOCEIMA", "ASSA ZAG","AZILAL","BENI MELLAL","BENSLIMANE","BOUJDOUR","BOULEMANE","BERRECHID","CASABLANCA","CHEFCHAOUEN","CHTOUKA AIT BAHA","CHICHAOUA","EL JADIDA","EL KELAA DES SRAGHNAS","ERRACHIDIA","ESSAOUIRA","ES SEMARA","FES","FIGUIG","GUELMIM","IFRANE","KENITRA","KHEMISSET","KHOURIBGA","LAAYOUNE","LARACHE","MOHAMMEDIA","MARRAKECH","MEKNES","NADOR","OUARZAZATE","OUJDA","OUED EDDAHAB","RABAT","SALE","SKHIRAT TEMARA","SEFROU","SAFI","SETTAT","SIDI KACEM","TANGER","TAN TAN","TAOUNAT","TAROUDANNT","TATA","TAZA","TETOUAN","TIZNIT");
		nationalite.getItems().addAll("Sud-Africain","Afghan","Albanais","Algérien","Allemand","Americain","Andorran","Angolais","Saoudien","Argentin","Arménien","Australien","Autrichien","Azerbaïdjanais","Bahamien","Bahreïnien","Bangladais","Barbadien","Belge","Belizien","Béninois","Bissau-Guinéen","Bhoutanais","Bolivien","Bosniaque","Botswanais","Brésilien","Britannique","Bruneien","Bulgare","Burkinais","Burundais","Camerounais","Canadien","Cap-Verdien","Chilien","Chinois","Chypriote","Colombien","Comorien","Nord-Coréen","Sud-Coréen","Costaricain","Ivoirien","Croate","Cubain","Danois","Djiboutien","Dominiquais","Égyptien","Émirien","Équatorien","Équato-Guinéen","Érythréen","Espagnol","Estonien","Est-Timorais","Éthiopien","Fidjien","Finlandais","Français","Gabonais","Gambien","Géorgien","Ghanéen","Grec","Grenadien","Guatemaleque","Guinéen","Guyanais","Haïtien","Hondurien","Hongrois","Indien","Indonésien","Irakien","Iranien","Irlande","Islandais","Israëlien","Italien","Jamaïcain","Japonais","Jordanien","Kazakh","Kenyan","Kirghiz","Kiribatien","Koweïtien","Laotien","Lesothan","Letton","Libanais","Liberien","Libyen","Liechtensteinois","Lituanien","Luxembourgeois","Macédonien","Malgache","Malaisien","Malawien","Maldivien","Malien","Maltais","Marocain","Mauricien","Mauritanien","Mexicain","Monégasque","Monténégrin","Mozambicain","Namibien","Népalais","Néerlandais","Nicaraguayen","Nigerien","Nigerian","Norvègien","Néo-Zélandais","Omanais","Ougandais","Ouzbek","Pakistanais","Palestinien","Panaméen","Papouasien","Paraguayen","Péruvien","Philippin","Polognais","Portugais","Qatari","République tchèque","Roumanain","Russe","Rwandais","Saint-Vincentais","Salvadorien","Sénégalais","Serbe","Sierra Leonais","Singapourien","Slovaque","Slovéne","Somalien","Soudanais","Sri Lankais","Suèdois","Suisse","Surinamien","Syrien","Tadjik","Tanzanien","Tchèque","Tchadien","Thaïlandais","Timor oriental","Togolais","Tunisien","Turkméne","Turque","Tuvaluan","Ukrainien","Uruguayen","Vatican","Venezuelien","ViêtNamien","Yémenite","Zambien","Zimbabween");		   		 
		 statut.getItems().addAll("Celibataire","Marie");
		 textDepartement.getItems().addAll("direction et administration générale ","achat","finance et comptabilité","logistique","marketing et commerciale","production"," recherche et développement","ressources humaines");
		
		 
		 fileChooser=new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("AllFiles","* *"),
			        new FileChooser.ExtensionFilter("images","*png","*jpg","*gif"),
			        new FileChooser.ExtensionFilter("Text File","*txt"));
			
			
			dateDeb.setValue(LocalDate.now());
			  final Callback<DatePicker,DateCell> dayCellFactory= new Callback<DatePicker, DateCell>() {
					@Override
					public DateCell call(final DatePicker datePicker) {
						return new DateCell() {
							@Override
							public void updateItem(LocalDate item,boolean empty) {
								super.updateItem(item, empty);
								if(item.isBefore(dateDeb.getValue().plusDays(1))) {
									setDisable(true);
									setStyle("-fx-background-color:#EEEEEE;");
								}
							}
						};
					}
				};
				dateFin.setDayCellFactory(dayCellFactory);
				dateFin.setValue(dateDeb.getValue().plusDays(1));
		 
}
}
