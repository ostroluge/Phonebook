package ui.panel;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class NumeroPanel extends AbstractPanel {

	public NumeroPanel() {
		setFirstLabel();
		setSecondLabel();
		
		add(firstLabel, getFirstLabelConstraints());
		add(secondLabel, getSecondLabelConstraints());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Un event s'est produit");
		if (e.getSource() == addButton) {
			System.out.println("Ajout d'un numero");
		} else if (e.getSource() == deleteButton) {
			System.out.println("Suppression d'un numero");
		} else if (e.getSource() == clearButton) {
			System.out.println("Clear d'un numero");
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

}
