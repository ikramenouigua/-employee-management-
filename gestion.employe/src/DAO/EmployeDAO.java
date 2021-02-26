package DAO;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Modele.Employe;
public class EmployeDAO {
   public static int enregistrer(Employe E,FileInputStream fis,File file) {
	   int st=0;
	   try {
		   String sql ="INSERT INTO employe(matricule,nom,prenom,cin,nationalite,ville,addresse,telephone,email,sexe,datenaissance,statutsocial,image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   Connection con=ConnectionDatabase.connect();
		   PreparedStatement stat;
		   stat=con.prepareStatement(sql);
		   stat.setString(1, E.getMatricule());
		   stat.setString(2, E.getNom());
		   stat.setString(3, E.getPrenom());
		   stat.setString(4, E.getCIN());
		   stat.setString(5, E.getNationalite());
		   stat.setString(6, E.getVille());
		   stat.setString(7, E.getAddresse());
		   stat.setString(8, E.getTelephone());
		   stat.setString(9, E.getEmail());
		   stat.setString(10, E.getSexe());
		   stat.setString(11, E.getDateNaissance());
		   stat.setString(12, E.getStatutSocial());
		   stat.setBinaryStream(13, fis, file.length());
	   st=stat.executeUpdate();
	   con.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return st;
   }
}
