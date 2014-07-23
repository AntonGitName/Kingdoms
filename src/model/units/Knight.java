package model.units;

public class Knight extends UnitModel {
	
	private static final int MAX_MOVES = 2; 
	
	public Knight(int x, int y, int team) {
		super(x, y, MAX_MOVES, team);
	}
}
