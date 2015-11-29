package ui;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class AbstractJListPanel extends JPanel {

	protected JList mJList;
	protected JScrollPane listScroller;
	
	protected abstract void setJList();
}
