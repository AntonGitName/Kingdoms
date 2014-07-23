package model.units;

public abstract class UnitModel {
	private int x;
	private int y;
	private final int maxMoves;
	private int moves;
	private int health;
	private int team;
	
	private static final int START_HEALTH = 10;
	
	public int getTeam() {
		return team;
	}
	
	public UnitModel(int x, int y, int maxMoves, int team) {
		super();
		this.x = x;
		this.y = y;
		this.maxMoves = maxMoves;
		this.team = team;
		this.health = START_HEALTH;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
}
