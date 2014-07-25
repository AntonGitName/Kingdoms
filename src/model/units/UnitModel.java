package model.units;

public abstract class UnitModel {
	private int x;

	private int y;

	private final int maxMoves;

	private int moves;

	public int getMoves() {
		return moves;
	}

	private int health;
	private int team;
	private static final int START_HEALTH = 10;

	public UnitModel(int x, int y, int maxMoves, int team) {
		super();
		this.x = x;
		this.y = y;
		this.maxMoves = maxMoves;
		this.team = team;
		this.health = START_HEALTH;
		this.moves = maxMoves;
	}

	public int getTeam() {
		return team;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
