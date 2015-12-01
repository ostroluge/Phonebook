package ui.panel;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import ui.listPanel.EntreeListPanel;
import ui.listener.DataManager;
import ui.listener.EntreeSelectedListener;
import domaine.Entree;
import fabrique.FabEntree;

@SuppressWarnings("serial")
public class EntreePanel extends AbstractPanel implements EntreeSelectedListener {

	public EntreePanel() {
		EntreeListPanel entree = new EntreeListPanel();
		entree.addListener(this);
		setFirstLabel();
		setSecondLabel();
		
		add(firstLabel, getFirstLabelConstraints());
		add(secondLabel, getSecondLabelConstraints());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Un event s'est produit");
		if (e.getSource() == addButton) {
			System.out.println("Ajout d'une entree");
			if (!firstTextField.getText().trim().equals("") 
					&& !secondTextField.getText().trim().equals("")) {
				int result = 
						FabEntree.getInstance().addEntree(
								firstTextField.getText().trim(),
								secondTextField.getText().trim());
				if (result == 1) {
					System.out.println("Ajout effectue avec succes");
				} else {
					System.out.println("Echec de l'ajout, veuillez reessayer");
				}
			} else {
				System.out.println("Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == deleteButton) {
			System.out.println("Suppression d'une entree");
			if (DataManager.getInstance().getEntreeSelected() != null) {
				Entree entreeToDelete = DataManager.getInstance().getEntreeSelected();
				int result = FabEntree.getInstance().deleteEntree(
						entreeToDelete.getNom());
				if (result == 1) {
					System.out.println("Suppression effectuee");
				} else {
					System.out.println("Echec de la suppression, veuillez reessayer");
				}
			} else {
				System.out.println("Veuillez selectionner une entree a supprimer");
			}
		} else if (e.getSource() == clearButton) {
			System.out.println("Clear de l'ensemble des entrees");
			int result = FabEntree.getInstance().deleteAllEntrees();
			if (result != 0) {
				System.out.println("Clear effectue");
			} else {
				System.out.println("Echec du clear, veuillez reessayer");
			}
		}
	}

	@Override
	protected void setFirstLabel() {
		firstLabel = new JLabel();
		firstLabel.setText("Nom :");
	}

	@Override
	protected void setSecondLabel() {
		secondLabel = new JLabel();
		secondLabel.setText("Prenom :");
	}

	@Override
	public void onEntreeSelection() {
		if (DataManager.getInstance().getEntreeSelected() != null) {
			Entree entreeSelected = DataManager.getInstance().getEntreeSelected();
			firstTextField.setText(entreeSelected.getNom());
			secondTextField.setText(entreeSelected.getPrenom());
		}
	}
}
