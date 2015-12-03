package ui.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ui.listPanel.EntreeListPanel;
import ui.listPanel.NumeroListPanel;
import ui.listener.EntreeSelectedListener;
import ui.listener.NumeroSelectedListener;
import domaine.Entree;
import domaine.Numero;
import fabrique.FabNumero;

@SuppressWarnings("serial")
public class NumeroPanel extends AbstractPanel implements NumeroSelectedListener, EntreeSelectedListener {

	protected Numero numero;
	protected Entree entree;
	
	public NumeroPanel() {
		new NumeroListPanel().addListener(this);
		new EntreeListPanel().addListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!firstTextField.getText().trim().equals("") 
					&& !secondTextField.getText().trim().equals("")) {
				if (entree != null) {
					int result = 
							FabNumero.getInstance().addNumero(
									firstTextField.getText().trim(),
									secondTextField.getText().trim(),
									entree);
					if (result == 1) {
						JOptionPane.showMessageDialog(topFrame, "Ajout effectue avec succes");
					} else {
						JOptionPane.showMessageDialog(topFrame, "Echec de l'ajout, veuillez reessayer");
					}
				} else {
					JOptionPane.showMessageDialog(topFrame, "Veuillez d'abord selectionner une entree");
				}
			}
		} else if (e.getSource() == deleteButton) {
			if (entree != null && numero != null) {
				int result =
						FabNumero.getInstance().deleteNumero(numero.getValeur());
				if (result == 1) {
					JOptionPane.showMessageDialog(topFrame, "Suppression effectuee");
				} else {
					JOptionPane.showMessageDialog(topFrame, "Echec de la suppression, veuillez reessayer");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez selectionner un numero a supprimer");
			}
		} else if (e.getSource() == clearButton) {
			if (entree != null) {
				int result =
						FabNumero.getInstance().deleteAllNumerosEntree(entree);
				if (result != 0) {
					JOptionPane.showMessageDialog(topFrame, "Clear effectue");
				} else {
					JOptionPane.showMessageDialog(topFrame, "Echec du clear, veuillez reessayer");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez d'abord selectionner une entree");
			}
		}
	}

	@Override
	protected void setFirstLabel() {
		firstLabel = new JLabel();
		firstLabel.setText("Code :");
	}

	@Override
	protected void setSecondLabel() {
		secondLabel = new JLabel();
		secondLabel.setText("Valeur :");
	}

	@Override
	public void onNumeroSelection(Numero numero) {
		if (numero != null) {
			this.numero = numero;
			firstTextField.setText(numero.getCode());
			secondTextField.setText(numero.getValeur());
		}
	}

	@Override
	public void onEntreeSelection(Entree entreeSelected) {
		if (entreeSelected != null) {
			this.entree = entreeSelected;
		}
	}
}