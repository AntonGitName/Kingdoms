package view;

import java.util.ArrayList;
import java.util.List;

import model.GameModel;
import view.buildings.BuildingView;
import view.units.UnitView;

public class GameView {
	private final GameModel model;

	private final List<UnitView> units;
	private final List<BuildingView> buildings;
	
	public GameView(GameModel model) {
		super();
		this.model = model;

		units = new ArrayList<>();
		buildings = new ArrayList<>();
	}
}
