package modele;

public class CompteEmploye {
    private String matricule;
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;
    
    public CompteEmploye(){
    	
    }
    public CompteEmploye(String matricule,String prenom,String nom,String email,String motDePasse,String role) {
    	this.matricule=matricule;
    	this.prenom=prenom;
    	this.nom=nom;
    	this.email=email;
    	this.motDePasse=motDePasse;
    	this.role=role;
    	
    }
    
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMatricule() {
		return matricule;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public String getNom() {
		return nom;
	}
	public String getEmail() {
		return email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
    
    
}
