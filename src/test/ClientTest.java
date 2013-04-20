package test;

import java.util.ArrayList;

import communication.ProcessIdentifier;

import game.network.GameService;
import game.network.IGameClient;
import game.network.IGameService;

import game.network.messages.GameStatus;
import game.network.messages.NetPlayer;

public class ClientTest implements IGameClient {

	GameService gameService;
	public ClientTest(GameService gs) {
		gameService = gs;
	}
	@Override
	public void updatePlayerList(IGameService theGame) {
//		ArrayList<ProcessIdentifier> players = ((GameService)theGame).getPlayerList();
//		System.out.println("Updated players:");
//		for (ProcessIdentifier id : players) {
//			System.out.println(" - " + id.getId());
//		}

	}

	@Override
	public void updateGameList(IGameService theGame) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGamePhase(IGameService theGame) {
//		if(this.gameService.getGamePhase() == GamePhase.TWODICES){
//			if (this.gameService.isMyTurn())
//				System.out.println("!!! A moi de jouer (2dés)!!!");
//			else
//				System.out.println("Au tour du joueur " + this.gameService.getTurn().getId());
//
//		}
//		if(this.gameService.getGamePhase() == GamePhase.ONEDICE){
//			if (this.gameService.isMyTurn())
//				System.out.println("!!! A moi de jouer (1dés)!!!");
//			else
//				System.out.println("Au tour du joueur " + this.gameService.getTurn().getId());
//
//		}
//		if(this.gameService.getGamePhase() == GamePhase.CHECKDICE){
//			// pass
//		}
		
		
			
	}
	@Override
	public void handleInvit(NetPlayer creator, NetPlayer guest) {
		// TODO Auto-generated method stub
		
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
