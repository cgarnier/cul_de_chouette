package game.gui;

public class History {
	
	protected int ID;
	protected int gameID;
	protected int playerID;
	protected int playerScore;
	
	public History(int ID, int gameID, int playerID, int playerScore){
		this.ID = ID;
		this.gameID = gameID;
		this.playerID = playerID;
		this.playerScore = playerScore;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public void setGameID(int ID){
		this.gameID = ID;
	}
	
	public void setPlayerID(int pID){
		this.playerID = pID;
	}

	public void setPlayerScore(int pScore){
		this.playerScore = pScore;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public int getGameID(){
		return this.gameID;
	}
	
	public int getPlayerID(){
		return this.playerID;
	}
	
	public int getPlayerScore(){
		return this.playerScore;
	}
}
