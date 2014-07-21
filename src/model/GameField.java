package model;

import java.util.Random;

public class GameField {
	
	private final Unit[][] field;
	private final Square[][] map;
	
	private final Random rnd = new Random();
	
	public GameField(int width, int height) {
		field = new Unit[height][width];
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
			field[y][x] = unit;
			break;
		case STONE:
		case WATER:
			throw new GameFieldException("Adding to not invalid square");
		}
		
	}
}
