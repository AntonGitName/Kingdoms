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
	private final GameView game;

	private static final String mapFilename = "resources/map.txt";

	public MainFrame() {
		super("Kingdoms");

		this.nextTurnButton = new JButton("next turn");

		nextTurnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.nextTeamTurn();
			}
		});

		GameModel model = null;
		GameView game = null;
		try {
			model = MapLoader.loadFromFile(mapFilename);
			game = new GameView(model);
		} catch (MapLoaderException | GameViewException e) {
			// replace with showmessage dialog
			e.printStackTrace();
		}
		this.game = game;
		panel = new GamePanel(this.game);
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
