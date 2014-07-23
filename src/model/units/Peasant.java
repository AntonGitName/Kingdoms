package model.units;

public class Peasant extends UnitModel {

	private static final int MAX_MOVES = 3;
	
	public Peasant(int x, int y, int team) {
		super(x, y, MAX_MOVES, team);
	}	
}
