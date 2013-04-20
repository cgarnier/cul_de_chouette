package game.gui;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleStart(GameStatus status) {
		// TODO Auto-generated method stub
		
	}
}
