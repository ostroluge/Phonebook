package ui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel implements ActionListener {

	protected JLabel firstLabel;
	protected JLabel secondLabel;
	
	protected JTextField firstTextField;
	protected JTextField secondTextField;
	
	protected JButton addButton;
	protected JButton deleteButton;
	protected JButton clearButton;
	
	
	public AbstractPanel() {
		setJButtons();
		setTextFields();
		setLayout(new GridBagLayout());
		
		add(firstTextField, getFirstTextFieldConstraints());
		add(secondTextField, getSecondTextFieldConstraints());
		
		add(addButton, getAddButtonConstraints());
		add(deleteButton, getDeleteButtonConstraints());
		add(clearButton, getClearButtonConstraints());
		
		setFirstLabel();
		setSecondLabel();
		
		add(firstLabel, getFirstLabelConstraints());
		add(secondLabel, getSecondLabelConstraints());
	}
	
	protected void setJButtons() {
		addButton = new JButton("Ajouter");
		deleteButton = new JButton("Supprimer");
		clearButton = new JButton("Clear");
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);
	}
	
	protected abstract void setFirstLabel();
	
	protected abstract void setSecondLabel();
	
	protected void setFirstTextField() {
		Document modelFirstTF = new PlainDocument();
		this.firstTextField = new JTextField(modelFirstTF, "", 8);
	}
	
	protected void setSecondTextField() {
		Document modelSecondTF = new PlainDocument();
		this.secondTextField = new JTextField(modelSecondTF, "", 8);
	}

	private void setTextFields() {
		setFirstTextField();
		setSecondTextField();
	}

	protected GridBagConstraints getJListConstraints() {
		GridBagConstraints mJListgbc = new GridBagConstraints();
		mJListgbc.gridx = 0;
		mJListgbc.gridy = 0;
		mJListgbc.insets = new Insets(0, 2, 15, 2);
		
		return mJListgbc;
	}
	
	protected GridBagConstraints getFirstLabelConstraints() {
		GridBagConstraints firstLabelgbc = new GridBagConstraints();
		firstLabelgbc.gridx = 0;
		firstLabelgbc.gridy = 1;
		firstLabelgbc.insets = new Insets(0, 2, 15, 2);
		
		return firstLabelgbc;
	}

	protected GridBagConstraints getFirstTextFieldConstraints() {
		GridBagConstraints firstTextFieldgbc = new GridBagConstraints();
		firstTextFieldgbc.gridx = 1;
		firstTextFieldgbc.gridy = 1;
		firstTextFieldgbc.insets = new Insets(0, 2, 15, 2);
		
		return firstTextFieldgbc;
	}
	
	protected GridBagConstraints getSecondLabelConstraints() {
		GridBagConstraints secondLabelgbc = new GridBagConstraints();
		secondLabelgbc.gridx = 0;
		secondLabelgbc.gridy = 2;
		secondLabelgbc.insets = new Insets(0, 2, 15, 2);
		
		return secondLabelgbc;
	}
	
	protected GridBagConstraints getSecondTextFieldConstraints() {
		GridBagConstraints secondTextFieldgbc = new GridBagConstraints();
		secondTextFieldgbc.gridx = 1;
		secondTextFieldgbc.gridy = 2;
		secondTextFieldgbc.insets = new Insets(0, 2, 15, 2);
		
		return secondTextFieldgbc;
	}
	
	protected GridBagConstraints getAddButtonConstraints() {
		GridBagConstraints addButtongbc = new GridBagConstraints();
		addButtongbc.gridx = 0;
		addButtongbc.gridy = 3;
		
		return addButtongbc;
	}
	
	protected GridBagConstraints getDeleteButtonConstraints() {
		GridBagConstraints deleteButtongbc = new GridBagConstraints();
		deleteButtongbc.gridx = 1;
		deleteButtongbc.gridy = 3;
		
		return deleteButtongbc;
	}
	
	protected GridBagConstraints getClearButtonConstraints() {
		GridBagConstraints clearButtongbc = new GridBagConstraints();
		clearButtongbc.gridx = 2;
		clearButtongbc.gridy = 3;
		
		return clearButtongbc;
	}
}
