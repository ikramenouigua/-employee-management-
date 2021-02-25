package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import modele.CongeAnnuel;

public class CongeAnnuelDAO {
	public static int enregistrer(CongeAnnuel C) {
		   int st=0;
		   try {
			   String sql ="INSERT INTO congeannuel(idemploye,idcontrat,nom,prenom,departement,poste,datedebut,datefin,etat,resultat) VALUES (?,?,?,?,?,?,?,?,?,?)";
			   Connection con=ConnectionDatabase.connect();
			   PreparedStatement stat;
			   stat=con.prepareStatement(sql);
			   stat.setString(1, C.getIdEmploye());
			   stat.setString(2, C.getIdContrat());
			   stat.setString(3, C.getNom());
			   stat.setString(4, C.getPrenom());
			   stat.setString(5, C.getDepartement());
			   stat.setString(6, C.getPoste());
			   stat.setString(7, C.getDateDebut());
			   stat.setString(8, C.getDateFin());
			   stat.setString(9, C.getEtatTraitement());
			   stat.setString(10, C.getResultat());
		   st=stat.executeUpdate();
		   con.close();
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   return st;
	   }
}
