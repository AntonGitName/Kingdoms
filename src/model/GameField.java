package model;

import java.util.PriorityQueue;
import java.util.Random;

public class GameField {
		private final int width;
	private final int height;;
	
	private final Building[][] buildings;
	private final Unit[][] units;
	private final Square[][] map;
	
	private final Random rnd = new Random();
	
	public GameField(int width, int height) {
		this.width = width;
		this.height = height;
		buildings = new Building[height][width];
		units = new Unit[height][width];
		map = new Square[height][width];
		visited = new VisitNode[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < height; ++j) {
				map[i][j] = Square.values()[rnd.nextInt(Square.values().length)];
				visited[i][j] = new VisitNode(j, i);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Building[][] getBuildings() {
		return buildings;
	}

	public Unit[][] getUnits() {
		return units;
	}
	
	public Square[][] getMap() {
		return map;
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
	
	public Unit getUnit(int x, int y) {
		return units[y][x];
	}
	
	private final VisitNode visited[][];
	private int currentVisit = 1;
	
	private boolean checkPosition(int x, int y) {
		return (x >= 0) && (x < width) && (y >= 0) && (x < height);
	}
	
	private boolean isVisited(int x, int y) {
		return visited[x][y].visitNum == currentVisit;
	}
	
	private int manhattanDist(int fromX, int fromY, int toX, int toY) {
		return Math.abs(fromX - toX) + Math.abs(fromY - toY);
	}
	
	// A* algorithm to find a path between two squares
	public boolean canGet(int fromX, int fromY, int toX, int toY) {
		// mark all squares as unvisited
		++currentVisit;
		
		// add first
		PriorityQueue<VisitNode> queue = new PriorityQueue<>(VisitNode.DISTANCE_ORDER);
		visited[fromY][fromX].distFunc = manhattanDist(fromX, fromY, toX, toY);
		queue.add(visited[fromY][fromX]);
		
		while (!queue.isEmpty()) {
			VisitNode curNode = queue.remove();
			int x = curNode.x;
			int y = curNode.y;
			
			if (x == toX && y == toY) {
				return true;
			}
			
			if (isVisited(x, y)) {
				continue;
			}
			// mark as visited
			curNode.visitNum = currentVisit;
			
			//checking neighbors
			if (checkPosition(x - 1, y) && isVisited(x - 1, y)) {
				visited[x - 1][y].distFunc = manhattanDist(x - 1, y, toX, toY) + curNode.distFunc;
				visited[x - 1][y].prev = curNode;
			}
		}
		
		return false;
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
	
	public void makeMove(int fromX, int fromY, int toX, int toY) {
		assert checkMove(new Move(fromX, fromY, toX, toY));
		units[toY][toX] = units[fromY][fromX];
		units[fromY][fromX] = null;
	}
}
