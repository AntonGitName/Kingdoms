package model;

import java.util.List;

import model.units.UnitModel;

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

		// Move move = new Move(fromX, fromY, toX, toY);

		UnitModel unitToMove = field.getUnit(fromX, fromY);
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

	public boolean isAvailableUnit(int x, int y) {
		final UnitModel unit = field.getUnit(x, y);
		if (unit == null) {
			return false;
		}
		if (unit.getTeam() != currentTeamTurn) {
			return false;
		}
		return true;
	}
	
	public UnitModel getUnit(int x, int y) {
		return field.getUnit(x, y);
	}
	
	public List<Point> getAccessibleSquares(int x, int y) throws GameFieldException {
		return field.getAccessibleSquares(x, y);
	}
}
