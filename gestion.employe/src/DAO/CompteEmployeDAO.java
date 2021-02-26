package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Modele.CompteEmploye;

public class CompteEmployeDAO {
	public static int enregistrer(CompteEmploye c) {
		   int st=0;
		   try {
			   String sql ="INSERT INTO compteemploye(matricule,prenom,nom,email,motdepasse,role) VALUES (?,?,?,?,?,?)";
			   Connection con=ConnectionDatabase.connect();
			   PreparedStatement stat;
			   stat=con.prepareStatement(sql);
			   stat.setString(1, c.getMatricule());
			   stat.setString(2, c.getPrenom());
			   stat.setString(3, c.getNom());
			   stat.setString(4, c.getEmail());
			   stat.setString(5, c.getMotDePasse());
			   stat.setString(6, c.getRole());
		   st=stat.executeUpdate();
		   con.close();
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   return st;
	   }
}


