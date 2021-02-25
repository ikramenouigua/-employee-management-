package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Modele.Contrat;

public class ContratDAO {
	public static int enregistrer(Contrat C) {
		int x=0;
		try {
			   String sql ="INSERT INTO contrat(idemploye,idcontrat,datedebut,datefin,departement,poste,diplome,salaire ) VALUES (?,?,?,?,?,?,?,?)";
			   Connection con=ConnectionDatabase.connect();
			   PreparedStatement stat;
			   stat=con.prepareStatement(sql);
			   stat.setString(1, C.getIdEmploye());
			   stat.setString(2, C.getNumeroContrat());
			   stat.setString(3, C.getDateDebut());
			   stat.setString(4, C.getDateFin());
			   stat.setString(5, C.getDepartement());
			   stat.setString(6, C.getPoste());
			   stat.setString(7, C.getDiplome());
			   stat.setDouble(8, C.getSalaire());
			   
			   
			  x= stat.executeUpdate();
			   con.close();
		}catch(Exception e) {
			e.printStackTrace();
	}
		return x;
	}

}
