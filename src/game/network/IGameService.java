package game.network;

import game.network.messages.GameStatus;
import game.network.messages.NetPlayer;
import communication.ProcessIdentifier;

public interface IGameService {
	public void createGame();
	public void invitPlayer(NetPlayer creator, NetPlayer guest);
	public void refreshGameList();
	public void saySuite();
	public void sayChouetteVeloute();
//	public void rollTwoDice(int d1, int d2);
//	public void rollOneDice(int d3);
	public void startGame(GameStatus status);
	public void joinGame(NetPlayer creator, NetPlayer player);
	
	public void setGameClient(IGameClient client);
	public void sendRefresh();
	public void sendWaiting(NetPlayer player);
	public ProcessIdentifier getMyNetId();



}
