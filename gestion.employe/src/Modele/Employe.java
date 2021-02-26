package Modele;

import javafx.scene.image.Image;

public class Employe {
	private String matricule;
	private String nom;
	private String prenom;
	private String CIN;
	private String nationalite;
	private String ville;
	private String addresse;
	private String telephone;
	private String email;
	private String sexe;
	private String dateNaissance;
	private String statutSocial;
	private Image image;
	public Employe(String matricule,String nom,String prenom, String CIN,String nationalite,String ville,String addresse,String telephone,String email,String sexe,String dateNaissance,String statutSocial,Image image) {
		this.matricule=matricule;
		this.nom=nom;
		this.prenom=prenom;
		this.CIN=CIN;
		this.nationalite=nationalite;
		this.ville=ville;
		this.addresse=addresse;
		this.telephone=telephone;
		this.email=email;
		this.sexe=sexe;
		this.dateNaissance=dateNaissance;
		this.statutSocial=statutSocial;
		this.image=image;
	}
	public Employe(String matricule,String nom,String prenom, String CIN,String nationalite,String ville,String addresse,String telephone,String email,String sexe,String dateNaissance,String statutSocial) {
		this.matricule=matricule;
		this.nom=nom;
		this.prenom=prenom;
		this.CIN=CIN;
		this.nationalite=nationalite;
		this.ville=ville;
		this.addresse=addresse;
		this.telephone=telephone;
		this.email=email;
		this.sexe=sexe;
		this.dateNaissance=dateNaissance;
		this.statutSocial=statutSocial;
	
	}

	public Employe() {
		
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getMatricule() {
		return matricule;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getCIN() {
		return CIN;
	}

	public String getNationalite() {
		return nationalite;
	}

	public String getVille() {
		return ville;
	}

	public String getAddresse() {
		return addresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getEmail() {
		return email;
	}

	public String getSexe() {
		return sexe;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public String getStatutSocial() {
		return statutSocial;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void setStatutSocial(String statutSocial) {
		this.statutSocial = statutSocial;
	}
	
	
	

}
