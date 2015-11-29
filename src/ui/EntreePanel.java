package ui;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class EntreePanel extends AbstractPanel {

	public EntreePanel() {
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
		} else if (e.getSource() == deleteButton) {
			System.out.println("Suppression d'une entree");
		} else if (e.getSource() == clearButton) {
			System.out.println("Clear d'une entree");
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
}
