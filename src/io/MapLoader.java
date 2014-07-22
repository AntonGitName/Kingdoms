package io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import model.GameField;
import model.GameFieldException;
import model.GameModel;
import model.buildings.Building;
import model.buildings.Castle;
import model.buildings.Mill;
import model.squares.Square;
import model.units.Archer;
import model.units.King;
import model.units.Knight;
import model.units.Peasant;
import model.units.Unit;

public class MapLoader {

	public static GameModel loadFromFile(String filename) throws MapLoaderException {
		try (Scanner scanner = new Scanner(new File(filename))) {
			
			GameField field = parseSquares(scanner);
			
			parseBuildings(field, scanner);
			
			parseUnits(field, scanner);
			
			return new GameModel(field);
			
		} catch (IOException e) {
			throw new MapLoaderException(e.getMessage());
		}
	}
	
	private static GameField parseSquares(Scanner scanner) throws MapLoaderException {
		int width = scanner.nextInt();
		int height = scanner.nextInt();
		
		if (!scanner.next().equals("SQUARES")) {
			throw new MapLoaderException("Squares section not found.");
		}
		
		Square[][] map = new Square[width][height];
		
		char squareChar;
		
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				squareChar = scanner.next().charAt(0);
				
				SquareTag tag = SquareTag.getName(squareChar);
				if (tag == null) {
					throw new MapLoaderException(String.format("Invalid character in SQUARES section at (%d, %d) position.", i, j));
				}
				
				switch (tag) {
				case GRASS:
					map[i][j] = Square.GRASS;
					break;
				case MOUNTAIN:
					map[i][j] = Square.MOUNTAIN;
					break;
				case WATER:
					map[i][j] = Square.WATER;
					break;
				}
			}
		}
		
		return new GameField(width, height, map);
	}
	
	private static void parseBuildings(GameField field, Scanner scanner) throws MapLoaderException {
		if (!scanner.next().equals("BUILDINGS")) {
			throw new MapLoaderException("Buildings section not found.");
		}
		
		char buildingChar;
		int x;
		int y;
		int numberOfBuildings = scanner.nextInt();
		for (int i = 0; i < numberOfBuildings; ++i) {
			buildingChar = scanner.next().charAt(0);
			BuildingTag tag = BuildingTag.getName(buildingChar);
			if (tag == null) {
				throw new MapLoaderException(String.format("Invalid character in BUILDINGS section at %d line.", i + 1));
			}
			
			x = scanner.nextInt() - 1;
			y = scanner.nextInt() - 1;
			Building building;
			switch (tag) {
			case CASTLE:
				building = new Castle();
				break;
			case MILL:
				building = new Mill();
				break;
			default:
				building = null;
			}
			try {
				field.addBuilding(building, x, y);
			} catch (GameFieldException e) {
				throw new MapLoaderException(e.getMessage());
			}
		}
	}
	
	private static void parseUnits(GameField field, Scanner scanner) throws MapLoaderException {
		if (!scanner.next().equals("UNITS")) {
			throw new MapLoaderException("Units section not found.");
		}
		
		int numberOfTeams = scanner.nextInt();
		
		for (int i = 0; i < numberOfTeams; ++i) {
			int teamNum = scanner.nextInt();
			if (teamNum != i + 1) {
				throw new MapLoaderException("Teams described in incorrect order.");
			}
			int numUnits = scanner.nextInt();
			int x;
			int y;
			char unitChar;
			for (int j = 0; j < numUnits; ++j) {
				unitChar = scanner.next().charAt(0);
				UnitTag tag = UnitTag.getName(unitChar);
				if (tag == null) {
					throw new MapLoaderException(String.format("Invalid character in UNITS section at %d line.", i + 1));
				}
				
				x = scanner.nextInt() - 1;
				y = scanner.nextInt() - 1;
				Unit unit;
				switch (tag) {
				case ARCHER:
					unit = new Archer();
					break;
				case KING:
					unit = new King();
					break;
				case KNIGHT:
					unit = new Knight();
					break;
				case PEASANT:
					unit = new Peasant();
					break;
				default:
					unit = null;
				}
				try {
					field.addUnit(unit, x, y);
				} catch (GameFieldException e) {
					throw new MapLoaderException(e.getMessage());
				}
			}
		}
	}
}
