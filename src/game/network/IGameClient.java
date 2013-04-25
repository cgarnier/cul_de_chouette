package game.network;

import game.gui.Interaction;

/**
 * Game Client Interface
 * Define what is a game client
 * @author clement
 *
 */
public interface IGameClient {
	
	/**
	 * Handle an invitation request
	 * @param creator
	 * @param guest
	 */
	public void handleInvit(NetPlayer creator, NetPlayer guest);
	
	
	/**
	 * Handle a join answer request
	 * @param creator
	 * @param guest
	 */
	public void handleJoinAnswer(NetPlayer creator, NetPlayer guest);
	
	/**
	 * Handle an update game status request
	 * @param status
	 */
	public void handleStatus(GameStatus status);
	
	/**
	 * Handle a start game request
	 * @param status
	 */
	public void handleStart(GameStatus status);
	
	/**
	 * Handle a refresh request
	 */
	public void handleRefresh();
	
	/**
	 * Handle a waiting notification
	 * @param player
	 */
	public void handleWaitingNotification(NetPlayer player);
	
	/**
	 * Handle a player interaction
	 * @param creator
	 * @param player
	 * @param type
	 */
	public void handleInteraction(NetPlayer creator, NetPlayer player, Interaction.Type type);
	
	/**
	 * Handle a kick request
	 * @param c
	 * @param p
	 */
	public void handleKick(NetPlayer c, NetPlayer p);
	
	/**
	 * Handle a cancel game notification
	 * @param c
	 */
	public void handleCancelGame(NetPlayer c);
	
	/**
	 * Handle a player leave notification
	 * @param c
	 * @param p
	 */
	public void handleLeaveGame(NetPlayer c, NetPlayer p);
}
