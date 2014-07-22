package view;

import io.MapLoader;
import io.MapLoaderException;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.GameModel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -2402809299451380136L;

	private GamePanel panel;
	private GameModel model;
	
	private static final String mapFilename = "resources/map.txt";
	
	public MainFrame() {
		super("Kingdoms");
		try {
			this.model = MapLoader.loadFromFile(mapFilename);
			this.panel = new GamePanel(model);
		} catch (MapLoaderException e) {
			
			// replace with showmessage dialog
			e.printStackTrace();
		}
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
