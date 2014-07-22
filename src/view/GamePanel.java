package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.GameField;
import model.GameModel;
import model.Square;

public class GamePanel extends JPanel {

	private final GameModel model;
	
	private static final int SQUARE_SIZE = 64;
	private static final int UNIT_SIZE = 32;
	private static final int PREFERRED_SIZE = 640;
	
	public GamePanel() {
		super();
		
		model = new GameModel(10, 10);
	}

	private static final Color BACKGROUND_COLOR = new Color(51, 51, 51);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		GameField field = model.getField();
		
		int w = field.getWidth();
		int h = field.getHeight();
		
		Square[][] map = field.getMap();
		
		int x;
		int y;
		
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, w * SQUARE_SIZE, h * SQUARE_SIZE);
		
		g.setColor(Color.RED);
		g.drawRect(0, 0, w * SQUARE_SIZE, h * SQUARE_SIZE);
		
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				switch (map[i][j]) {
				case GRASS:
					g.setColor(Color.GREEN);
					break;
				case STONE:
					g.setColor(Color.GRAY);
					break;
				case WATER:
					g.setColor(Color.BLUE);
					break;
				}
				y = i * SQUARE_SIZE;
				x = j * SQUARE_SIZE;
				g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
			}
		}
		g.setColor(Color.RED);
		g.drawRect(0, 0, w * SQUARE_SIZE, h * SQUARE_SIZE);
	}

	

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREFERRED_SIZE, PREFERRED_SIZE);
	}

}
