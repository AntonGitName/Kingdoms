package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import view.buildings.BuildingView;
import view.units.UnitView;
import model.GameField;
import model.buildings.BuildingModel;
import model.squares.Square;
import model.units.UnitModel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -5090326710138990599L;

	private final GameView gameView;

	private static final int SQUARE_SIZE = 64;
	private static final int PREFERRED_SIZE = 640;

	private static final Color BACKGROUND_COLOR = new Color(51, 51, 51);

	public GamePanel(GameView gameView) {
		super();

		this.gameView = gameView;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREFERRED_SIZE, PREFERRED_SIZE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		GameField field = gameView.getField();

		int w = field.getWidth();
		int h = field.getHeight();

		Square[][] map = field.getMap();
		int x;
		int y;

		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, w * SQUARE_SIZE, h * SQUARE_SIZE);

		Image image = null;

		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				image = gameView.getSquareImage(map[i][j]);
				y = i * SQUARE_SIZE;
				x = j * SQUARE_SIZE;
				g.drawImage(image, x, y, SQUARE_SIZE, SQUARE_SIZE, null);
			}
		}
		
		for (UnitView unit : gameView.getUnits()) {
			g.drawImage(unit.getImage(), unit.getX() * SQUARE_SIZE, unit.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
		}
		
		for (BuildingView building : gameView.getBuildings()) {
			g.drawImage(building.getImage(), building.getX() * SQUARE_SIZE, building.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
		}
		
		g.setColor(Color.RED);
		g.drawRect(0, 0, w * SQUARE_SIZE, h * SQUARE_SIZE);
	}

}
