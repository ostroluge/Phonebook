package fabrique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbManager;
import domaine.Entree;
import domaine.Numero;

public class FabNumero {

	private static FabNumero INSTANCE;
	private List<Numero> numeros = new ArrayList<>();
	private Connection conn = DbManager.getInstance().getConnection();
	
	public static FabNumero getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FabNumero();
		}
		return INSTANCE;
	}
	
	public List<Numero> getAllNumeros() {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"select * from numero");
			preparedStatement.clearParameters();
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				String label = result.getString(1);
				String valeur = result.getString(2);
				int idEntree = result.getInt(3);
				numeros.add(new Numero(label, valeur, idEntree));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (numeros != null) {
			return numeros;
		}
		return null;
	}
	
	public List<Numero> getNumerosByIdEntree(int idEntree) {
		List<Numero> numerosByEntree = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"select * from numero where id_entree = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, idEntree);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				String code = result.getString(1);
				String valeur = result.getString(2);
				int id = result.getInt(3);
				numerosByEntree.add(new Numero(code, valeur, id));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		if (numerosByEntree != null) {
			return numerosByEntree;
		}
		return null;
	}
	
	public void addNumero(String label, String valeur, Entree entree) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"insert into numero values (?, ?, ?)");
			preparedStatement.clearParameters();
					
			preparedStatement.setString(1, label);
			preparedStatement.setString(2, valeur);
			preparedStatement.setInt(3, entree.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteNumero(String valeur) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"delete from numero where value = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, valeur);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
