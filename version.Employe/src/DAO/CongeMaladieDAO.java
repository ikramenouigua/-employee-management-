package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import modele.CongeMaladie;

public class CongeMaladieDAO {
	//private static  FileInputStream fis;
	//private static File file;
	
   public static int enregistrer(CongeMaladie C,FileInputStream fis,File file) {
	   int st=0;
	   try {
		   
		   String sql ="INSERT INTO congemaladie(idemploye,idcontrat,nom,prenom,departement,poste,datedebut,datefin,certificatmaladie,etat,resultat) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
		   
		   stat.setBinaryStream(9, fis, file.length());
		   stat.setString(10, C.getEtatTraitement());
		   stat.setString(11, C.getResultat());
		   
		   
	   st=stat.executeUpdate();
	   con.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return st;
}
}
