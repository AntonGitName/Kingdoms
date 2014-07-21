package model;

import java.util.Random;

public class GameField {
	
	private final Building[][] buildings;
	private final Unit[][] units;
	private final Square[][] map;
	
	private final Random rnd = new Random();
	
	public GameField(int width, int height) {
		buildings = new Building[height][width];
		units = new Unit[height][width];
		map = new Square[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < height; ++j) {
				map[i][j] = Square.values()[rnd.nextInt(Square.values().length)];
			}
		}
	}
	
	public void addUnit(Unit unit, int x, int y) throws GameFieldException {
		switch (map[y][x]) {
		case GRASS:
			units[y][x] = unit;
			break;
		case STONE:
		case WATER:
			throw new GameFieldException("Adding to not invalid square");
		}
		
	}
	
	private boolean checkMove(Move move) {
		switch (map[move.toY][move.toX]) {
		case STONE:
		case WATER:
			return false;
		default:
			break;
		}
		if (units[move.toY][move.toX] != null) {
			return false;
		}
		if (units[move.fromY][move.fromX] == null) {
			return false;
		}
		return true;
	}
	
	public void makeMove(Move move) {
		assert checkMove(move);
		units[move.toY][move.toX] = units[move.fromY][move.fromX];
		units[move.fromY][move.fromX] = null;
	}
}
