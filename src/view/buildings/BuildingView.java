package view.buildings;

import model.buildings.BuildingModel;
import view.Drawable;

public abstract class BuildingView implements Drawable {

	protected static final String IMAGES_FOLDER = "resources//images//buildings//";
	protected final BuildingModel buildingModel;

	public BuildingView(BuildingModel buildingModel) {
		super();
		this.buildingModel = buildingModel;
	}
	
	@Override
	public int getX() {
		return buildingModel.getX();
	}

	@Override
	public int getY() {
		return buildingModel.getY();
	}
}
