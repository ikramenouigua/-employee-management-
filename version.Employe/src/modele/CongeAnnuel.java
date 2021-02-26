package modele;

public class CongeAnnuel {
	String idEmploye;
    String idContrat;
    String nom;
    String prenom;
    String departement;
    String poste;
    String dateDebut;
    String dateFin;
    String etatTraitement;
    String resultat;
    public CongeAnnuel(){
   	 
    }
    public CongeAnnuel(String idEmploye,String idContrat,String nom,String prenom,String departement,String poste,String dateDebut,String dateFin,String etatTraitement,String resultat){
   	 this.idEmploye=idEmploye;
   	 this.idContrat=idContrat;
   	 this.nom=nom;
   	 this.prenom=prenom;
   	 this.departement=departement;
   	 this.poste=poste;
   	 this.dateDebut=dateDebut;
   	 this.dateFin=dateFin;
   	 this.etatTraitement=etatTraitement;
   	 this.resultat=resultat;
    }
	public String getIdEmploye() {
		return idEmploye;
	}
	public String getIdContrat() {
		return idContrat;
	}
	public String getNom() {
		return nom;
	}
	public String getDepartement() {
		return departement;
	}
	public String getPoste() {
		return poste;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public String getEtatTraitement() {
		return etatTraitement;
	}
	public String getResultat() {
		return resultat;
	}
	public void setIdEmploye(String idEmploye) {
		this.idEmploye = idEmploye;
	}
	public void setIdContrat(String idContrat) {
		this.idContrat = idContrat;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public void setEtatTraitement(String etatTraitement) {
		this.etatTraitement = etatTraitement;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
