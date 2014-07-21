package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
	
	private final GameField field;
	
	private int numberOfTeams;
	
	private List<Move> moves = new ArrayList<>();
	
	public GameModel(int width, int height) {
		field = new GameField(width, height);
		numberOfTeams = 2;
	}
	
	public void addMove(int fromX, int fromY, int toX, int toY) {
		
		// various checks here
		
		moves.add(new Move(fromX, fromY, toX, toY));
	}
	
	public void makeTurn() {
		for (Move move : moves) {
			field.makeMove(move);
		}
	}
}
