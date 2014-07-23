package view.buildings;

import model.buildings.BuildingModel;

public abstract class BuildingView {

	private final BuildingModel buildingModel;

	public BuildingView(BuildingModel buildingModel) {
		super();
		this.buildingModel = buildingModel;
	}
	
	public abstract void draw();	
}
