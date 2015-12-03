package ui.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ui.listPanel.EntreeListPanel;
import ui.listener.EntreeSelectedListener;
import domaine.Entree;
import fabrique.FabEntree;

@SuppressWarnings("serial")
public class EntreePanel extends AbstractPanel implements EntreeSelectedListener {

	protected Entree entree;
	
	public EntreePanel() {
		new EntreeListPanel().addListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!firstTextField.getText().trim().equals("") 
					&& !secondTextField.getText().trim().equals("")) {
				int result = 
						FabEntree.getInstance().addEntree(
								firstTextField.getText().trim(),
								secondTextField.getText().trim());
				if (result == 1) {
					JOptionPane.showMessageDialog(topFrame, "Ajout effectue avec succes");
				} else {
					JOptionPane.showMessageDialog(topFrame, "Echec de l'ajout, veuillez reessayer");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == deleteButton) {
			if (entree != null) {
				int result = FabEntree.getInstance().deleteEntree(
						entree.getNom());
				if (result == 1) {
					JOptionPane.showMessageDialog(topFrame, "Suppression effectuee");
				} else {
					JOptionPane.showMessageDialog(topFrame, "Echec de la suppression, veuillez reessayer");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez selectionner une entree a supprimer");
			}
		} else if (e.getSource() == clearButton) {
			int result = FabEntree.getInstance().deleteAllEntrees();
			if (result != 0) {
				JOptionPane.showMessageDialog(topFrame, "Clear effectue");
			} else {
				JOptionPane.showMessageDialog(topFrame, "Echec du clear, veuillez reessayer");
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
	public void onEntreeSelection(Entree entreeSelected) {
		if (entreeSelected != null) {
			this.entree = entreeSelected;
			firstTextField.setText(entree.getNom());
			secondTextField.setText(entree.getPrenom());
		}
	}
}