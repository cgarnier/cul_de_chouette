package game.gui;

import game.gui.playerlist.PlayerListModel;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

public class Games {
	
	protected int ID;
	protected Date date;
	protected PlayerListModel gamePlayersInfos;
	protected int winnerID;
	protected ArrayList<PlayerModel> players;
	
	public Games(){
	}
	
	public Games(PlayerListModel infos){
		date = new Date();
		this.gamePlayersInfos = infos;
		winnerID = gamePlayersInfos.getWinner().getPlayerID();
		players = gamePlayersInfos.getPlayers();
	}
	
	public int getID(){
		return this.ID;
	}

	public Date getDate(){
		return this.date;
	}
	
	public PlayerListModel getGamePlayersInfos(){
		return this.gamePlayersInfos;
	}
	
	public int getWinnerID(){
		return this.winnerID;
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
	
	public void setGamePlayersInfos(PlayerListModel infos){
		this.gamePlayersInfos = infos;
	}
	
	public void setPlayers(ArrayList<PlayerModel> players){
		this.players = players;
	}
	
	public void setWinnerID(int wID){
		this.winnerID = wID;
	}
	
	public void addPlayer(PlayerModel player){
		this.players.add(player);
	}
	
	public void putGameInHistory(){
		
	}
}
