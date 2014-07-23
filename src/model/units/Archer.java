package model.units;

public class Archer extends UnitModel {

	private static final int MAX_MOVES = 2;

	public Archer(int x, int y, int team) {
		super(x, y, MAX_MOVES, team);
	}

}
