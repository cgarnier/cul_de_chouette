package game.gui;

import java.util.ArrayList;
import game.gui.GameModel.GamePhase;

import game.network.GameService;
import game.network.IGameClient;
import game.network.IGameService;
import game.network.messages.GameStatus;
import game.network.messages.NetPlayer;

/*
 * Filtre les données provenant du GameSerice et actualise le model.
 * 
 * 
 */
public class GameHandler implements IGameClient{
	protected GameModel model;
	
	protected IGameService service;
	
	public GameHandler(GameModel model) {
		this.model = model;
		this.service = new GameService();
		this.service.setGameClient(this);
	}



	@Override
	public void updatePlayerList(IGameService theGame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGameList(IGameService theGame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGamePhase(IGameService theGame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInvit(NetPlayer creator, NetPlayer guest) {
		System.out.println("Handle invite...");
		if(model.isInGame())
			return;
		if(!model.getMe().equals(guest))
			return;
		if(model.getGamePhase() != GamePhase.WAITING)
			return;
		
		model.setCreator(creator);
		service.joinGame(creator, guest);
	}

	@Override
	public void handleJoinAnswer(NetPlayer creator, NetPlayer guest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleStatus(GameStatus status) {
		if(this.model != null){
			if(this.model.getPlayers().size() < status.getPlayerList().size()){
				ArrayList<NetPlayer> tmplist = (ArrayList<NetPlayer>) status.getPlayerList().clone();
				tmplist.removeAll(this.model.getPlayers());
				for (NetPlayer np : tmplist) {
					this.model.addPlayer(np);
				}
				
				
			}
		}
		
	}


	@Override
	public void handleStart(GameStatus status) {
		
		
	}

	@Override
	public void handleRefresh() {
		if(model.getGamePhase() == GamePhase.WAITING && model.getCreator() == null){
			service.sendWaiting(model.getMe().toNet());
		}
		
	}


	public void refresh() {
		System.out.println("Sending refresh request...");
		service.sendRefresh();
		
	}
	@Override
	public void handleWaitingNotification(NetPlayer player) {
		System.out.println("Handle waiting notif...");
		if(model.getCreator() == null) return;
		if(model.getGamePhase() == GamePhase.WAITING &&
				model.getCreator().equals(model.getMe())){
			
			if(!model.getLobbyPlayers().contains(player)){
				model.addLobbyPlayer(player);
				System.out.println("Player added...");
			}
		}
		
	}
	public IGameService getService() {
		return service;
	}
}
