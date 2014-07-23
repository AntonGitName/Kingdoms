package view.units;

import model.units.UnitModel;
import view.Drawable;

public abstract class UnitView implements Drawable {
	
	private final UnitModel unitModel;
	
	public UnitView(UnitModel unitModel) {
		super();
		this.unitModel = unitModel;
	}
	
}
