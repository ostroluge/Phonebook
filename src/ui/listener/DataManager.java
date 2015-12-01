package ui.listener;

import domaine.Entree;

public class DataManager {

	private static DataManager INSTANCE;
	
	private Entree entreeSelected;
	
	public static DataManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataManager();
		}
		return INSTANCE;
	}

	public void setEntreeSelected(Entree entree) {
		this.entreeSelected = entree;
	}

	public Entree getEntreeSelected() {
		return entreeSelected;
	}
}
