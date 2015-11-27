package ui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

	JPanel container = new JPanel();
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
		this.container.add(entreePanel);
		this.container.add(numeroPanel);
	}
}
