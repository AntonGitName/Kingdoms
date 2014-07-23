package model;

import model.units.Unit;


public class GameModel {
	
	private final GameField field;
	
	private final int numberOfTeams;

	private int currentTeamTurn;
	
	public GameModel(GameField field) {
		this.field = field;
		numberOfTeams = 2;
	}
	
	public GameField getField() {
		return field;
	}
	
	public boolean isGameOver() {
		return false;
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
	
	public void nextTeamTurn() {
		currentTeamTurn = (currentTeamTurn + 1) % numberOfTeams;	
	}

}
