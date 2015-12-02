package ui.panel;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

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
		System.out.println("Un event s'est produit");
		if (e.getSource() == addButton) {
			System.out.println("Ajout d'un numero");
			if (!firstTextField.getText().trim().equals("") 
					&& !secondTextField.getText().trim().equals("")) {
				if (entree != null) {
					int result = 
							FabNumero.getInstance().addNumero(
									firstTextField.getText().trim(),
									secondTextField.getText().trim(),
									entree);
					if (result == 1) {
						System.out.println("Ajout effectue avec succes");
					} else {
						System.out.println("Echec de l'ajout, veuillez reessayer");
					}
				} else {
					System.out.println("Veuillez d'abord selectionner une entree");
				}
			}
		} else if (e.getSource() == deleteButton) {
			System.out.println("Suppression d'un numero");
			if (entree != null && numero != null) {
				int result =
						FabNumero.getInstance().deleteNumero(numero.getValeur());
				if (result == 1) {
					System.out.println("Suppression effectuee");
				} else {
					System.out.println("Echec de la suppression, veuillez reessayer");
				}
			} else {
				System.out.println("Veuillez selectionner un numero a supprimer");
			}
		} else if (e.getSource() == clearButton) {
			System.out.println("Clear l'ensemble des numeros d'une entree");
			if (entree != null) {
				int result =
						FabNumero.getInstance().deleteAllNumerosEntree(entree);
				if (result != 0) {
					System.out.println("Clear effectue");
				} else {
					System.out.println("Echec du clear, veuillez reessayer");
				}
			} else {
				System.out.println("Veuillez d'abord selectionner une entree");
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