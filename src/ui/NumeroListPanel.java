package ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import domaine.Numero;
import fabrique.FabNumero;

public class NumeroListPanel extends AbstractJListPanel {

	public NumeroListPanel() {
		setJList();
		add(listScroller);
	}
	
	@Override
	protected void setJList() {
		DefaultListModel<Numero> listModel = new DefaultListModel<Numero>();
		List<Numero> numeros = FabNumero.getInstance().getAllNumeros();
		
		for (int i=0; i<numeros.size(); i++) {
			listModel.add(i, numeros.get(i));
		}
		
		mJList = new JList<Numero>(listModel);
		mJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mJList.setLayoutOrientation(JList.VERTICAL);
		mJList.setVisibleRowCount(-1);
		
		listScroller = new JScrollPane(mJList);
		listScroller.setPreferredSize(new Dimension(320,200));
	}
}
