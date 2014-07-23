package view.units;

import model.units.UnitModel;
import view.Drawable;

public abstract class UnitView implements Drawable {

	protected static final String IMAGES_FOLDER = "resources//images//units//";
	protected final UnitModel unitModel;

	public UnitView(UnitModel unitModel) {
		super();
		this.unitModel = unitModel;
	}

}
