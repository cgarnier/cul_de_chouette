package game.gui;

import game.network.messages.NetPlayer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import communication.ProcessIdentifier;


public class PlayerModel extends Observable {

	public enum Sex {
	    M, F
	}
	
	protected int playerID;
	protected String playerLogin;
	protected String playerPassword;
	protected int playerAge;
	protected char playerSex;
	protected String playerCity;
	protected int playerScore;
	protected Color playerColor;
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	protected NetPlayer netId;
	
	public void setPlayerID(int ID) {
		this.playerID = ID;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerLogin(String login) {
		this.playerLogin = login;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerPassword(String password) {
		this.playerPassword = password;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerAge(int age) {
		this.playerAge = age;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerSex(char sex) {
		this.playerSex = sex;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerCity(String city) {
		this.playerCity = city;
		this.setChanged();
		this.notifyObservers();
	}

	public void setPlayerScore(int score) {
		this.playerScore = score;
		this.setChanged();
		this.notifyObservers();
	}

	public void setPlayerColor(int r, int g, int b) {
		this.playerColor = new Color(r, g, b);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerColor(Color color) {
		this.playerColor = color;
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getPlayerID(){
		return this.playerID;
	}
	
	public String getPlayerLogin() {
		return this.playerLogin;
	}
	
	public String getPlayerPassword() {
		return this.playerPassword;
	}
	
	public int getPlayerAge() {
		return this.playerAge;
	}
	
	public char getPlayerSex() {
		return this.playerSex;
	}
	
	public String getPlayerCity() {
		return this.playerCity;
	}

	public int getPlayerScore() {
		return this.playerScore;
	}

	public Color getPlayerColor() {
		return this.playerColor;
	}

	public void increasePlayerScore(int points) {
		this.playerScore += points;
		this.setChanged();
		this.notifyObservers();
	}

	public void decreasePlayerScore(int points) {
		if (this.playerScore <= points) {
			this.playerScore = 0;
		} else {
			this.playerScore -= points;
		}
		
		this.setChanged();
		this.notifyObservers();

	}
	
	public PlayerModel(){
		
	}
	
	public PlayerModel(String name, Color color) {
		this.setPlayerLogin(name);
		this.setPlayerScore(0);
		this.setPlayerColor(color);
	}
	public PlayerModel(NetPlayer creator, Color black) {
		this.netId = creator;
		this.setPlayerColor(black);
	}
	
	public PlayerModel(int ID, String login, String password, int age, char sex, String city){
		this.setPlayerID(ID);
		this.setPlayerLogin(login);
		this.setPlayerPassword(password);
		this.setPlayerAge(age);
		this.setPlayerSex(sex);
		this.setPlayerCity(city);
	}
	
	public PlayerModel(String login, String password, int age, char sex, String city){
		this.setPlayerLogin(login);
		this.setPlayerPassword(password);
		this.setPlayerAge(age);
		this.setPlayerSex(sex);
		this.setPlayerCity(city);
	}
	

	public NetPlayer toNet() {
		netId.setGlobalId(0);
		return netId;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NetPlayer){
			NetPlayer np = (NetPlayer) obj;
			return np.equals(this.toNet());
		}
		if(obj instanceof PlayerModel){
			PlayerModel np = (PlayerModel) obj;
			return np.equals(this.toNet());
		}
		
		return super.equals(obj);
	}

	public synchronized NetPlayer getNetId() {
		return netId;
	}

	public synchronized void setNetId(NetPlayer netId) {
		this.netId = netId;
	}

}
