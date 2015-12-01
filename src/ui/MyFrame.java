package ui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.listPanel.EntreeListPanel;
import ui.listPanel.NumeroListPanel;
import ui.panel.EntreePanel;
import ui.panel.NumeroPanel;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	JPanel container = new JPanel();
	EntreeListPanel entreeListPanel = new EntreeListPanel();
	NumeroListPanel numeroListPanel = new NumeroListPanel();
	EntreePanel entreePanel = new EntreePanel();
	NumeroPanel numeroPanel = new NumeroPanel();
	
	public MyFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Annuaire");
		setLocationRelativeTo(null);
		
		initContainer();
		
		setContentPane(container);
	}

	public void initContainer() {
		this.container.setLayout(new GridLayout(2,2));
		this.container.add(entreeListPanel);
		this.container.add(numeroListPanel);
		this.container.add(entreePanel);
		this.container.add(numeroPanel);
	}
}
