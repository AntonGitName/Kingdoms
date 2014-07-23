package view.units;

import model.units.UnitModel;

public abstract class UnitView {
	
	private final UnitModel unitModel;

	public UnitView(UnitModel unitModel) {
		super();
		this.unitModel = unitModel;
	}
	
	public abstract void draw();
	
}
