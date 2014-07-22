package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.GameField;
import model.GameModel;
import model.buildings.Building;
import model.squares.Square;
import model.units.Unit;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -5090326710138990599L;

	private final GameModel model;
	
	private static final int SQUARE_SIZE = 64;
	private static final int UNIT_SIZE = 32;
	private static final int BUILDING_SIZE = 48;
	private static final int PREFERRED_SIZE = 640;
	
	public GamePanel(GameModel model) {
		super();
		
		this.model = model;
	}

	private static final Color BACKGROUND_COLOR = new Color(51, 51, 51);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		GameField field = model.getField();
		
		int w = field.getWidth();
		int h = field.getHeight();
		
		Square[][] map = field.getMap();
		Unit[][] units = field.getUnits();
		Building[][] buildings = field.getBuildings();
		
		int x;
		int y;
		
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, w * SQUARE_SIZE, h * SQUARE_SIZE);
		
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				switch (map[i][j]) {
				case GRASS:
					g.setColor(Color.GREEN);
					break;
				case MOUNTAIN:
					g.setColor(Color.GRAY);
					break;
				case WATER:
					g.setColor(Color.BLUE);
					break;
				}
				y = i * SQUARE_SIZE;
				x = j * SQUARE_SIZE;
				g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				g.setColor(Color.RED);
				g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				
				Unit unit = units[i][j];
				if (unit != null) {
					x += (SQUARE_SIZE - UNIT_SIZE) / 2;
					y += (SQUARE_SIZE - UNIT_SIZE) / 2;
					g.setColor(Color.ORANGE);
					g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
				}
				
				Building building = buildings[i][j];
				if (building != null) {
					x += (SQUARE_SIZE - BUILDING_SIZE) / 2;
					y += (SQUARE_SIZE - BUILDING_SIZE) / 2;
					g.setColor(Color.PINK);
					g.fillRect(x, y, BUILDING_SIZE, BUILDING_SIZE);
				}
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
