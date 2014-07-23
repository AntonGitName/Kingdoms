package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javax.imageio.ImageIO;

import model.GameField;
import model.GameModel;
import model.squares.Square;
import view.buildings.BuildingView;
import view.units.UnitView;

public class GameView {
	private final GameModel model;
	private static final String SQUARE_IMAGES_FOLDER = "resources//images//squares//";
	private final EnumMap<Square, Image> squareImages;

	public GameField getField() {
		return model.getField();
	}

	public boolean isGameOver() {
		return model.isGameOver();
	}

	public void nextTeamTurn() {
		model.nextTeamTurn();
	}

	private final List<UnitView> units;
	private final List<BuildingView> buildings;

	public GameView(GameModel model) {
		super();
		this.model = model;

		squareImages = new EnumMap<>(Square.class);

		String filename;
		for (Square s : Square.values()) {
			try {

				filename = SQUARE_IMAGES_FOLDER + s.toString().toLowerCase() + ".png";
				squareImages.put(s, ImageIO.read(new File(filename)));
			} catch (IOException e) {
				System.out.println("bad news");
				System.out.println(e.getMessage());
			}
		}

		units = new ArrayList<>();
		buildings = new ArrayList<>();
	}
	
	public Image getSquareImage(Square square) {
		return squareImages.get(square);
	}
}
