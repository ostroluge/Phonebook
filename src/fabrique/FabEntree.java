package fabrique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbManager;
import domaine.Entree;

public class FabEntree {

	private static FabEntree INSTANCE;
	private List<Entree> entrees = new ArrayList<>();
	private Connection conn = DbManager.getInstance().getConnection();
	
	public static FabEntree getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FabEntree();
		}
		return INSTANCE;
	}

	public List<Entree> getAllEntrees() {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from entree");
			preparedStatement.clearParameters();

			ResultSet resultPreparedStatement = preparedStatement.executeQuery();

			while (resultPreparedStatement.next()) {
				int id = resultPreparedStatement.getInt(1);
				String nom = resultPreparedStatement.getString(2);
				String prenom = resultPreparedStatement.getString(3);
				this.entrees.add(new Entree(id, nom, prenom));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (this.entrees != null) {
			return this.entrees;
		}
		return null;
	}
	
	public Entree getEntreeByName(String nom) {
		Entree entree = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from entree where nom = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, nom);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String nomEntree = result.getString(2);
				String prenomEntree = result.getString(3);
				entree = new Entree(id, nomEntree, prenomEntree);
			}
			
			if (entree != null) {
				return entree;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int addEntree(String nom, String prenom) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"insert into entree (nom, prenom) values (?,?)" );
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
			
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deleteEntree(String nom) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"delete from entree where nom = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, nom);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
