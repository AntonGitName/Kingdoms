package model.units;

public class King extends UnitModel {
	
	private static final int MAX_MOVES = 1; 
	
	public King(int x, int y, int team) {
		super(x, y, MAX_MOVES, team);
	}
	
}
