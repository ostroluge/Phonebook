package ui.listPanel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui.listener.DataManager;
import ui.listener.EntreeSelectedListener;
import domaine.Numero;
import fabrique.FabNumero;

@SuppressWarnings("serial")
public class NumeroListPanel extends JPanel implements EntreeSelectedListener {

	protected JList<Numero> mJList;
	protected JScrollPane listScroller;
	
	protected DefaultListModel<Numero> listModel = new DefaultListModel<>();
	
	public NumeroListPanel() {
		EntreeListPanel entree = new EntreeListPanel();
		entree.addListener(this);
		setJList();
		add(listScroller);
	}
	
	protected void setJList() {
		List<Numero> numeros;
		
		mJList = new JList<Numero>(listModel);
		mJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mJList.setLayoutOrientation(JList.VERTICAL);
		mJList.setVisibleRowCount(-1);
		
		listScroller = new JScrollPane(mJList);
		listScroller.setPreferredSize(new Dimension(320,200));
	
		ListSelectionModel lsm = mJList.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (mJList.getSelectedValue() instanceof Numero) {
					if (mJList.getValueIsAdjusting()) {
						if (mJList.getSelectedValue() instanceof Numero) {
							Numero result = (Numero) mJList.getSelectedValue();
							System.out.println(result.getCode() + " number selected");
						}
					}
				}
			}
		});
	}

	@Override
	public void onEntreeSelection() {
		if (DataManager.getInstance().getEntreeSelected().getId() != 0) {
			int idEntree = DataManager.getInstance().getEntreeSelected().getId();
			listModel.clear();
			List<Numero> numeros = FabNumero.getInstance().getNumerosByIdEntree(idEntree);
			for (int i=0; i<numeros.size(); i++) {
				listModel.add(i, numeros.get(i));
			}
			mJList.setModel(listModel);
		}
	}
}
