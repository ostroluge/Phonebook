package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domaine.Entree;
import fabrique.FabEntree;

public class EntreeListPanel extends AbstractJListPanel {

	public EntreeListPanel() {
		setJList();
		add(listScroller);
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
		
		ListSelectionModel lsm = mJList.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("Un event list s'est produit");
				System.out.println(e.getFirstIndex());
			}
		});
		
		listScroller = new JScrollPane(mJList);
		listScroller.setPreferredSize(new Dimension(320,200));
	}
}
