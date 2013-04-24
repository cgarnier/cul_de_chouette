package game.network;

import game.gui.Interaction;
import game.network.messages.GameStatus;
import game.network.messages.NetPlayer;

public interface IGameClient {
	
	public void handleInvit(NetPlayer creator, NetPlayer guest);
	public void handleJoinAnswer(NetPlayer creator, NetPlayer guest);
	public void handleStatus(GameStatus status);
	public void handleStart(GameStatus status);
	public void handleRefresh();
	public void handleWaitingNotification(NetPlayer player);
	public void handleInteraction(NetPlayer creator, NetPlayer player, Interaction.Type type);
	public void handleKick(NetPlayer c, NetPlayer p);
	public void handleCancelGame(NetPlayer c);
	public void handleLeaveGame(NetPlayer c, NetPlayer p);
}
