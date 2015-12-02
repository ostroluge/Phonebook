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

import ui.listener.EntreeChangeListener;
import ui.listener.EntreeSelectedListener;
import domaine.Entree;
import fabrique.FabEntree;

@SuppressWarnings("serial")
public class EntreeListPanel extends JPanel implements EntreeChangeListener {

	private static List<EntreeSelectedListener> listeners = new ArrayList<>();
	
	protected JList<Entree> mJList;
	protected JScrollPane listScroller;
	protected DefaultListModel<Entree> listModel = new DefaultListModel<>();
	
	protected Entree entreeSelected;
	
	public EntreeListPanel() {
		FabEntree.getInstance().addListener(this);
		
		setJList();
		add(listScroller);
	}
	
	public void setJList() {
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
				if (mJList.getSelectedValue() instanceof Entree) {
					if (mJList.getValueIsAdjusting()) {
						entreeSelected = (Entree) mJList.getSelectedValue();
						fireEntreeSelectedEvent(entreeSelected);
						System.out.println(entreeSelected.getNom() + " selected");
					}
				}
			}
		});
		
		listScroller = new JScrollPane(mJList);
		listScroller.setPreferredSize(new Dimension(320,200));
	}


	@Override
	public void entreeHasChanged() {
		listModel.clear();
		List<Entree> entrees = FabEntree.getInstance().getAllEntrees();
		for (int i=0; i<entrees.size(); i++) {
			listModel.add(i, entrees.get(i));
		}
		mJList.setModel(listModel);
	}

	public void addListener(EntreeSelectedListener listener) {
		listeners.add(listener);
	}
	
	private static void fireEntreeSelectedEvent(Entree entreeSelected) {
		for (EntreeSelectedListener listener : listeners) {
			listener.onEntreeSelection(entreeSelected);
		}
	}
}
