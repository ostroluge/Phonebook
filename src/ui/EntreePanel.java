package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import domaine.Entree;
import fabrique.FabEntree;

public class EntreePanel extends AbstractPanel {

	public EntreePanel() {

//		setLabels();
		setJList();
		
		add(listScroller);
		
//		add(firstLabel, getFirstLabelConstraints());
//		add(secondLabel, getSecondLabelConstraints());
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
		super.firstLabel.setText("Nom :");
	}

	@Override
	protected void setSecondLabel() {
		super.secondLabel = new JLabel();
		super.secondLabel.setText("Prenom :");
	}

	private void setLabels() {
		setFirstLabel();
		setSecondLabel();
	}

	@Override
	protected void setJList() {
		DefaultListModel<Entree> listModel = new DefaultListModel<Entree>();
		List<Entree> entrees = FabEntree.getInstance().getAllEntrees();
		
		for (int i=0; i<entrees.size(); i++) {
			listModel.add(i, entrees.get(i));
		}
		
		mJList = new JList<Entree>(listModel);
		mJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mJList.setLayoutOrientation(JList.VERTICAL);
		mJList.setVisibleRowCount(-1);
		
		listScroller = new JScrollPane(mJList);
		listScroller.setPreferredSize(new Dimension(320,200));
	}
}
