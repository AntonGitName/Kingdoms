package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
	
	private final GameField field;
	
	private int numberOfTeams;
	
	private int currentTeamTurn;
	
	public GameModel(int width, int height) {
		field = new GameField(width, height);
		numberOfTeams = 2;
		currentTeamTurn = 0;
	}
	
	public void makeMove(int fromX, int fromY, int toX, int toY) {
		
		Move move = new Move(fromX, fromY, toX, toY);
		
		// various checks here
		
	}
	
	public void endTurn() {
		
		currentTeamTurn = (currentTeamTurn + 1) % numberOfTeams;
		
	}
}
