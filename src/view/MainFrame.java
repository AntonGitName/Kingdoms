package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -2402809299451380136L;

	private final GamePanel panel;
	
	public MainFrame() {
		super("Kingdoms");
		
		panel = new GamePanel();
	}
	
	public void showGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panel.setFocusable(true);
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}

}
