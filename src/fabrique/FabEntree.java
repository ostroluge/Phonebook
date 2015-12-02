package fabrique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.listener.EntreeChangeListener;
import db.DbManager;
import domaine.Entree;

public class FabEntree {

	private static FabEntree INSTANCE;
	private List<Entree> entrees = new ArrayList<>();
	private Connection conn = DbManager.getInstance().getConnection();
	
	private static List<EntreeChangeListener> listeners = new ArrayList<>();
	
	public static FabEntree getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FabEntree();
		}
		return INSTANCE;
	}

	public List<Entree> getAllEntrees() {
		this.entrees.clear();
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
	
	public int addEntree(String nom, String prenom) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"insert into entree (nom, prenom) values (?,?)" );
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, prenom);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteEntree(String nom) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"delete from entree where nom = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, nom);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteAllEntrees() {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"delete from entree");
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addListener(EntreeChangeListener listener) {
		listeners.add(listener);
	}
	
	private static void fireModelChangeEvent() {
		for (EntreeChangeListener listener : listeners) {
			listener.entreeHasChanged();
		}
	}
}
