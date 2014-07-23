package view.buildings;

import model.buildings.BuildingModel;
import view.Drawable;

public abstract class BuildingView implements Drawable {

	private final BuildingModel buildingModel;

	public BuildingView(BuildingModel buildingModel) {
		super();
		this.buildingModel = buildingModel;
	}
}
