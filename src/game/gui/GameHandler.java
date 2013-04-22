package game.gui;

import java.awt.Color;
import java.util.ArrayList;
import game.gui.GameModel.GamePhase;
import game.gui.Interaction.Type;

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
public class GameHandler implements IGameClient {
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
		if (model.isInGame())
			return;
		if (!model.getMe().equals(guest))
			return;
		System.out.println(guest);
		System.out.println(model.getMe().toNet());
		if (model.getGamePhase() != GamePhase.WAITING)
			return;
		System.out.println("game joined");
		model.setCreator(creator);
		service.joinGame(creator, guest);
	}

	@Override
	public void handleJoinAnswer(NetPlayer creator, NetPlayer guest) {
		if (!model.getCreator().equals(creator))
			return;
		if (!model.getMe().equals(creator))
			return;

		System.out.println("!!! Join answer received !!!");
		model.addPlayer(guest);
		service.sendGameStatus(model.getGameStatus());

	}

	@Override
	public void handleStatus(GameStatus status) {
		if (!model.getCreator().equals(status.getCreator()))
			return;

		if (this.model != null) {

			model.getPlayersModel().reset();
			for (NetPlayer p : status.getPlayerList()) {
				if (model.getMe().equals(p))
					model.addPlayer(model.getMe());
				else
					model.addPlayer(p);

			}

			if (!model.getGamePhase().equals(status.getPhase())) {
				if (status.getPhase() == GamePhase.ONEDICE) {
					model.setDices(status.getDices());
					model.setPhase(GamePhase.ONEDICE);
				}
				if (status.getPhase() == GamePhase.CHECKDICE) {
					model.setDices(status.getDices());
					if (model.getDices().isSuite()) {
						model.getInteraction().expectSuite();
						model.setPhase(GamePhase.INTERACTION);
						return;
					}
					if (model.getDices().isChouetteVeloute()) {
						model.getInteraction().expectChouetteVeloute();
						model.setPhase(GamePhase.INTERACTION);
						return;
					}
					calculScores();
					checkScores();

				}

				model.setPhase(status.getPhase());

			}

		}

	}

	private void checkScores() {
		for (PlayerModel p : model.getPlayersModel().getPlayers()) {
			if(p.getPlayerScore() >= 343){
				model.setWinner(p);
				model.setPhase(GamePhase.FINISH);
				return;
			}
				
		}
		model.nextTurn();
		model.setPhase(GamePhase.TWODICES);
		
		
	}

	private void calculScores() {
		model.setPhase(GamePhase.SCORING);
		if (model.getDices().isChouetteVeloute()) {
			model.getInteraction()
					.getPlayer()
					.setPlayerScore(
							model.getInteraction().getPlayer().getPlayerScore()
									+ model.getDices().getScore());
			return;
		}
		if (model.getDices().isSuite()) {
			model.getInteraction()
					.getPlayer()
					.setPlayerScore(
							model.getInteraction().getPlayer().getPlayerScore()
									+ model.getDices().getScore());
			return;
		}
		if (model.getDices().isCulDeChouette()) {
			model.getTurn().setPlayerScore(
					model.getTurn().getPlayerScore()
							+ model.getDices().getScore());
			return;
		}
		if (model.getDices().isChouette()) {
			model.getTurn().setPlayerScore(
					model.getTurn().getPlayerScore()
							+ model.getDices().getScore());
			return;
		}
		if (model.getDices().isVeloute()) {
			model.getTurn().setPlayerScore(
					model.getTurn().getPlayerScore()
							+ model.getDices().getScore());
			return;
		}

	}

	@Override
	public void handleStart(GameStatus status) {
		handleStatus(status);

	}

	@Override
	public void handleRefresh() {
		if (model.getGamePhase() == GamePhase.WAITING
				&& model.getCreator() == null) {
			service.sendWaiting(model.getMe().toNet());
		}

	}
	@Override
	public void handleInteraction(NetPlayer player, Type type) {
		if(!model.getGamePhase().equals(GamePhase.INTERACTION)) return;
		if(!model.getPlayersModel().getPlayers().contains(player)) return;
		if(type.equals(model.getInteraction().getExpected())){
			model.getInteraction().addPlayer(model.getPlayersModel().getFromNet(player));
			
			if(model.getInteraction().interacCount() >= model.getPlayersModel().size()){
				calculScores();
				checkScores();
			}
			
		}
		
		
	}

	public void refresh() {
		System.out.println("Sending refresh request...");
		service.sendRefresh();

	}

	@Override
	public void handleWaitingNotification(NetPlayer player) {
		System.out.println("Handle waiting notif...");
		if (model.getCreator() == null)
			return;
		if (model.getGamePhase() == GamePhase.WAITING
				&& model.getCreator().equals(model.getMe())) {

			if (!model.getAvailableModel().getPlayers().contains(player)) {
				PlayerModel pm = new PlayerModel(player, Color.black);
				model.getAvailableModel().add(pm);
				System.out.println("Player added...");
			}
		}

	}

	public IGameService getService() {
		return service;
	}
}
