package game.gui;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

public class Games {
	
	protected int ID;
	protected Date date;
	protected PlayerModel winner;
	protected ArrayList<PlayerModel> players;
	
	public Games(){
		date = new Date();
		winner = null;
		players = new ArrayList<PlayerModel>();
	}
	
	public int getID(){
		return this.ID;
	}

	public Date getDate(){
		return this.date;
	}
	
	public PlayerModel getWinner(){
		return this.winner;
	}
	
	public ArrayList<PlayerModel> getPlayers(){
		return this.players;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public void setWinner(PlayerModel winner){
		this.winner = winner;
	}
	
	public void addPlayer(PlayerModel player){
		this.players.add(player);
	}
	
	public void putGameInHistory(){
		
	}
}
