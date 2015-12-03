package ui.listPanel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui.listener.EntreeSelectedListener;
import ui.listener.NumeroChangeListener;
import ui.listener.NumeroSelectedListener;
import domaine.Entree;
import domaine.Numero;
import fabrique.FabNumero;

@SuppressWarnings("serial")
public class NumeroListPanel extends JPanel implements EntreeSelectedListener, NumeroChangeListener {

	protected Entree entree;
	
	private static List<NumeroSelectedListener> listeners = new ArrayList<>();
	
	protected JList<Numero> mJList;
	protected JScrollPane listScroller;
	
	protected DefaultListModel<Numero> listModel = new DefaultListModel<>();
	
	public NumeroListPanel() {
		FabNumero.getInstance().addListener(this);
		new EntreeListPanel().addListener(this);
		
		setJList();
		add(listScroller);
	}
	
	protected void setJList() {
		
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
							fireNumeroSelectedEvent(result);
						}
					}
				}
			}
		});
	}

	@Override
	public void onEntreeSelection(Entree entreeSelected) {
		if (entreeSelected.getId() != 0) {
			this.entree = entreeSelected;
			listModel.clear();
			List<Numero> numeros = FabNumero.getInstance().getNumerosByIdEntree(entree.getId());
			for (int i=0; i<numeros.size(); i++) {
				listModel.add(i, numeros.get(i));
			}
			mJList.setModel(listModel);
		}
	}

	@Override
	public void numeroHasChanged() {
		if (entree != null) {
			listModel.clear();
			List<Numero> numeros = FabNumero.getInstance().getNumerosByIdEntree(entree.getId());
			for (int i=0; i<numeros.size(); i++) {
				listModel.add(i, numeros.get(i));
			}
			mJList.setModel(listModel);
		}
	}

	public void addListener(NumeroSelectedListener listener) {
		listeners.add(listener);
	}
	
	private static void fireNumeroSelectedEvent(Numero numeroSelected) {
		for (NumeroSelectedListener listener : listeners) {
			listener.onNumeroSelection(numeroSelected);
		}
	}
}
