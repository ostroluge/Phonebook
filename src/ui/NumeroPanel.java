package ui;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class NumeroPanel extends AbstractPanel {

	public NumeroPanel() {
		setLabels();
		
		add(super.firstLabel, getFirstLabelConstraints());
		add(super.secondLabel, getSecondLabelConstraints());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			System.out.println("add button pressed");
		} else if (e.getSource() == deleteButton) {
			System.out.println("delete button pressed");
		} else if (e.getSource() == clearButton) {
			System.out.println("clear button pressed");
		}
	}

	@Override
	protected void setFirstLabel() {
		super.firstLabel = new JLabel();
		super.firstLabel.setText("Code :");
	}

	@Override
	protected void setSecondLabel() {
		super.secondLabel = new JLabel();
		super.secondLabel.setText("Valeur :");
	}

	private void setLabels() {
		setFirstLabel();
		setSecondLabel();
	}

	@Override
	protected void setJList() {
		
	}
}
