package view;

import io.MapLoader;
import io.MapLoaderException;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.GameModel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -2402809299451380136L;

	private final GamePanel panel;
	private final JButton nextTurnButton;
	private final GameModel model;
	
	private static final String mapFilename = "resources/map.txt";
	
	public MainFrame() {
		super("Kingdoms");
		
		this.nextTurnButton = new JButton("next turn");
		
		nextTurnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.nextTeamTurn();				
			}
		});
		
		GameModel model = null;
		try {
			model = MapLoader.loadFromFile(mapFilename);
		} catch (MapLoaderException e) {
			// replace with showmessage dialog
			e.printStackTrace();
		}
		this.model = model;
		panel = new GamePanel(this.model);
	}
	
	public void showGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setLayout(new BorderLayout());
		panel.setFocusable(true);
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		add(nextTurnButton, BorderLayout.PAGE_END);
		
		pack();
		setVisible(true);
	}

}
