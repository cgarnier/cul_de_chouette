package game.network;

import game.gui.Interaction;
import communication.ProcessIdentifier;

/**
 * Game service interface
 * @author clement
 *
 */
public interface IGameService {
	
	/**
	 * Create game request
	 */
	public void createGame();
	
	/**
	 * Send an invite request from creator to guest
	 * @param creator
	 * @param guest
	 */
	public void invitPlayer(NetPlayer creator, NetPlayer guest);


	/**
	 * Send a game status update
	 * @param status
	 */
	public void startGame(GameStatus status);
	
	/**
	 * Send a join game answer.
	 * Usually sent after a game invite request
	 * @param creator
	 * @param player
	 */
	public void joinGame(NetPlayer creator, NetPlayer player);
	
	
	/**
	 * Bind a game client to the service.
	 * @param client
	 */
	public void setGameClient(IGameClient client);
	
	/**
	 * Send a refresh request to everyone
	 */
	public void sendRefresh();
	
	/**
	 * Send a waiting notification
	 * Usually sent after received a refresh request or after entered in waiting for game  game phase.
	 * @param player
	 */
	public void sendWaiting(NetPlayer player);
	
	
	/**
	 * Send a game status update
	 * @param status
	 */
	public void sendGameStatus(GameStatus status);
	
	
	/**
	 * Send an interaction
	 * @param creator
	 * @param player
	 * @param type Can be SUITE or CHOUETTEVELUTE
	 */
	public void sendInteraction(NetPlayer creator, NetPlayer player, Interaction.Type type);
	
	/**
	 * 
	 * @return Process identifier
	 */
	public ProcessIdentifier getMyNetId();
	
	/**
	 * Kick a player from the game.
	 * Sent when creator want to kick someone or when party is full.
	 * @param net
	 * @param guest
	 */
	public void sendKick(NetPlayer net, NetPlayer guest);
	
	/**
	 * Send a cancel game notification.
	 * 
	 * @param net
	 */
	public void sendCancel(NetPlayer net);
	
	/**
	 * Send a player leave notification.
	 * Send when a player leave the party
	 * @param net
	 * @param net2
	 */
	public void sendLeave(NetPlayer net, NetPlayer net2);
	
	/**
	 * Disconnect from the id service
	 */
	public void disconnect();



}
