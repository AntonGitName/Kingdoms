package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javax.imageio.ImageIO;

import model.GameField;
import model.GameModel;
import model.buildings.BuildingModel;
import model.squares.Square;
import model.units.*;
import view.buildings.BuildingView;
import view.units.*;

public class GameView {
	private final GameModel model;
	private static final String SQUARE_IMAGES_FOLDER = "resources//images//squares//";
	private final EnumMap<Square, Image> squareImages;

	public List<UnitView> getUnits() {
		return units;
	}

	public List<BuildingView> getBuildings() {
		return buildings;
	}

	private final List<UnitView> units;

	private final List<BuildingView> buildings;

	public GameView(GameModel model) throws GameViewException {
		super();
		this.model = model;

		squareImages = new EnumMap<>(Square.class);

		String filename;
		for (Square s : Square.values()) {
			try {

				filename = SQUARE_IMAGES_FOLDER + s.toString().toLowerCase()
						+ ".png";
				squareImages.put(s, ImageIO.read(new File(filename)));
			} catch (IOException e) {
				throw new GameViewException("Cannot load square images."); 
			}
		}

		units = new ArrayList<>();
		buildings = new ArrayList<>();
		GameField field = model.getField();
		Square[][] map = field.getMap();
		UnitModel[][] units = field.getUnits();
		BuildingModel[][] buildings = field.getBuildings();
		int w = field.getWidth();
		int h = field.getHeight();
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {

				UnitModel unit = units[i][j];
				if (unit != null) {
					// not the perfect solution =(
					if (unit instanceof Archer) {
						this.units.add(new ArcherView(unit));
					} else if (unit instanceof King) {
						this.units.add(new KingView(unit));
					} else if (unit instanceof Knight) {
						this.units.add(new KnightView(unit));
					} else if (unit instanceof Peasant) {
						this.units.add(new PeasantView(unit));
					} else {
						throw new GameViewException("Unknown type stored at game field object.");
					}
				}

				BuildingModel building = buildings[i][j];
				if (building != null) {
				}
			}
		}
	}

	public GameField getField() {
		return model.getField();
	}

	public Image getSquareImage(Square square) {
		return squareImages.get(square);
	}

	public boolean isGameOver() {
		return model.isGameOver();
	}

	public void nextTeamTurn() {
		model.nextTeamTurn();
	}
}
