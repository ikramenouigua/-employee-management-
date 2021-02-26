package Modele;

public class Contrat {
	private String idEmploye;
     private String numeroContrat ;
     
     private String dateDebut;
     private String dateFin;
     private String departement ;
     private String poste;
     private String diplome;
     private double salaire;
     
     public Contrat() {
    	 
     }
     public Contrat(String numeroContrat,String idEmploye,String dateDebut,String dateFin,String poste,String departement ,String diplome,double salaire) {
    	 this.idEmploye=idEmploye;
    	 this.numeroContrat=numeroContrat;
    	 
    	 this.dateDebut=dateDebut;
    	 this.dateFin=dateFin;
    	 this.departement=departement;
    	 this.poste=poste;
    	 this.diplome=diplome;
    	 this.salaire=salaire;
    	 
    	 
    	 
     }
     
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getNumeroContrat() {
		return numeroContrat;
	}
	public String getIdEmploye() {
		return idEmploye;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public String getPoste() {
		return poste;
	}
	public String getDiplome() {
		return diplome;
	}
	public double getSalaire() {
		return salaire;
	}
	
	public void setNumeroContrat(String numeroContrat) {
		this.numeroContrat = numeroContrat;
	}
	public void setIdEmploye(String idEmploye) {
		this.idEmploye = idEmploye;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	
     
}
