package model;

import java.util.PriorityQueue;
import java.util.Random;

import model.buildings.BuildingModel;
import model.squares.Square;
import model.units.UnitModel;

public class GameField {
		private final int width;
	private final int height;;
	
	private final BuildingModel[][] buildings;
	private final UnitModel[][] units;
	private final Square[][] map;
	
	private final Random rnd = new Random();
	
	private final VisitNode visited[][];
	
	private int currentVisit = 1;
	
	public GameField(int width, int height) {
		this.width = width;
		this.height = height;
		buildings = new BuildingModel[height][width];
		units = new UnitModel[height][width];
		map = new Square[height][width];
		visited = new VisitNode[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < height; ++j) {
				map[i][j] = Square.values()[rnd.nextInt(Square.values().length)];
				visited[i][j] = new VisitNode(j, i);
			}
		}
	}

	public GameField(int width, int height, Square[][] map) {
		this.width = width;
		this.height = height;
		buildings = new BuildingModel[height][width];
		units = new UnitModel[height][width];
		this.map = map;
		map = new Square[height][width];
		visited = new VisitNode[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < height; ++j) {
				visited[i][j] = new VisitNode(j, i);
			}
		}
	}

	public void addBuilding(BuildingModel building, int x, int y) throws GameFieldException {
		if (!checkPosition(x, y)) {
			throw new GameFieldException(String.format("Accesing square with invalid position (%d, %d).", x, y));
		}
		switch (map[y][x]) {
		case GRASS:
			buildings[y][x] = building;
			break;
		case MOUNTAIN:
		case WATER:
			throw new GameFieldException("Adding to not invalid square");
		}
		
	}

	public void addUnit(UnitModel unit, int x, int y) throws GameFieldException {
		if (!checkPosition(x, y)) {
			throw new GameFieldException(String.format("Accesing square with invalid position (%d, %d).", x, y));
		}
		switch (map[y][x]) {
		case GRASS:
			units[y][x] = unit;
			break;
		case MOUNTAIN:
		case WATER:
			throw new GameFieldException("Adding to not invalid square");
		}
		
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
		case MOUNTAIN:
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
	
	private boolean checkPosition(int x, int y) {
		return (x >= 0) && (x < width) && (y >= 0) && (x < height);
	}
	
	public BuildingModel[][] getBuildings() {
		return buildings;
	}
	
	public int getHeight() {
		return height;
	}
	public Square[][] getMap() {
		return map;
	}
	
	public UnitModel getUnit(int x, int y) {
		return units[y][x];
	}
	
	public UnitModel[][] getUnits() {
		return units;
	}
	
	public int getWidth() {
		return width;
	}
	
	private boolean isVisited(int x, int y) {
		return visited[x][y].visitNum == currentVisit;
	}
	
	public void makeMove(int fromX, int fromY, int toX, int toY) {
		assert checkMove(new Move(fromX, fromY, toX, toY));
		units[toY][toX] = units[fromY][fromX];
		units[fromY][fromX] = null;
	}
	
	public void makeMove(Move move) {
		assert checkMove(move);
		units[move.toY][move.toX] = units[move.fromY][move.fromX];
		units[move.fromY][move.fromX] = null;
	}
	
	private int manhattanDist(int fromX, int fromY, int toX, int toY) {
		return Math.abs(fromX - toX) + Math.abs(fromY - toY);
	}
}
