package model;

public class GameModel {
	
	private final GameField field;
	
	private int numberOfTeams;
	
	public GameModel(int width, int height) {
		field = new GameField(width, height);
		numberOfTeams = 2;
	}
	
	public void makeTurn() {
		
	}
}
