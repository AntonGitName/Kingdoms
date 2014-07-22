package model;

import model.units.Unit;


public class GameModel {
	
	private final GameField field;
	
	public GameField getField() {
		return field;
	}

	private int numberOfTeams;
	
	private int currentTeamTurn;
	
	public GameModel(GameField field) {
		this.field = field;
	}
	
	public GameModel(int width, int height) {
		field = new GameField(width, height);
		numberOfTeams = 2;
		currentTeamTurn = 0;
	}
	
	public void makeMove(int fromX, int fromY, int toX, int toY) {
		
		//Move move = new Move(fromX, fromY, toX, toY);
		
		Unit unitToMove = field.getUnit(fromX, fromY);
		if (unitToMove.getTeam() != currentTeamTurn) {
			return;
		}
		if (!field.canGet(fromX, fromY, toX, toY)) {
			return;
		}
		field.makeMove(fromX, fromY, toX, toY);
	}
	
	public void endTurn() {
		currentTeamTurn = (currentTeamTurn + 1) % numberOfTeams;	
	}
	
	public boolean isGameOver() {
		return false;
	}
}
