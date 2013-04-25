package game.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import game.gui.GameModel.GamePhase;
import game.gui.Interaction.Type;

import game.network.GameService;
import game.network.GameStatus;
import game.network.IGameClient;
import game.network.IGameService;
import game.network.NetPlayer;

/*
 * Filtre les données provenant du GameSerice et actualise le modele.
 * 
 * 
 */

/**
 * Filter incoming events from the game service and update the game model.
 * @author clement
 *
 */
public class GameHandler implements IGameClient {
	protected GameModel model;
	
	protected IGameService service;
	Gui view;

	public GameHandler(GameModel model) {
		this.model = model;
		this.service = new GameService();
		this.service.setGameClient(this);
		
	}
	
	
	
	/**
	 * Set the view
	 * @param v
	 */
	public void setView(Gui v) {
		// TODO Create a view interface to allow multi view
		view = v;
	}

	



	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleInvit(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void handleInvit(NetPlayer creator, NetPlayer guest) {
		if(model.getMe() == null) return;
		if (model.isInGame())
			return;
		if (!model.getMe().equals(guest))
			return;

		if (model.getGamePhase() != GamePhase.WAITING)
			return;
		
		model.setCreator(creator);
		service.joinGame(creator, guest);
		view.slidePlayerList();
		
	}

	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleJoinAnswer(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void handleJoinAnswer(NetPlayer creator, NetPlayer guest) {
		if(model.getCreator() == null)
			return;
		if (!model.getCreator().equals(creator))
			return;
		if (!model.getMe().equals(creator))
			return;

		if(model.getPlayersModel().isLimitReached()){
			service.sendKick(model.getMe().toNet(), guest);
			return;
		}
		model.addPlayer(guest);
		service.sendGameStatus(model.getGameStatus());
		

	}

	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleStatus(game.network.messages.GameStatus)
	 */
	@Override
	public void handleStatus(GameStatus status) {
		if(model.getCreator() == null) return;
		if (!model.getCreator().equals(status.getCreator()))
			return;

		if (this.model != null) {

			updatePlayerList(status.getPlayerList());

			if (!model.getGamePhase().equals(status.getPhase())) {
				model.setPhase(status.getPhase());
				if (status.getPhase() == GamePhase.ONEDICE) {
					model.setDices(status.getDices());
					// model.setPhase(GamePhase.ONEDICE);

				}
				if (status.getPhase() == GamePhase.CHECKDICE) {
					checkDices(status);

				}

			}

		}
		System.err.println("+++ status received : " + status.getPhase());

	}

	/**
	 * Update the player list from the new list delivered by game service
	 * @param newlist
	 */
	private void updatePlayerList(ArrayList<NetPlayer> newlist) {
		// TODO Modify for remove player
		//model.getPlayersModel().reset();
		ArrayList<PlayerModel> TheNewOrder = new ArrayList<PlayerModel>();
		ArrayList<NetPlayer> toAdd = new ArrayList<NetPlayer>();
		for (NetPlayer p : newlist) {
			if (model.getPlayersModel().getPlayers().contains(p))
				TheNewOrder.add(model.getPlayersModel().getFromNet(p));
			else
				toAdd.add(p);

		}
		model.getPlayersModel().setPlayers(TheNewOrder);
		for (NetPlayer netPlayer : toAdd) {
			model.addPlayer(netPlayer);
		}
		
	}

	/**
	 * Check the dices combination. Detect and process new player interaction and calculate the turn score.
	 * @param status
	 */
	public void checkDices(GameStatus status) {
		System.err.println("checking dices ...");
		model.setDices(status.getDices());
		if (model.getDices().isSuite()) {
			model.getInteraction().expectSuite();
			model.setPhase(GamePhase.INTERACTION);
			return;
		} else if (model.getDices().isChouetteVelute()) {
			model.getInteraction().expectChouetteVeloute();
			model.setPhase(GamePhase.INTERACTION);
			return;
		} else {
			calculScores();
			checkScores();
		}

	}

	/**
	 * 
	 */
	private void checkScores() {
		for (PlayerModel p : model.getPlayersModel().getPlayers()) {
			if (p.getPlayerScore() >= 343) {
				model.getPlayersModel().setWinner(p);
				model.setPhase(GamePhase.FINISH);

				
				if(this.model.getMe().equals(this.model.getPlayersModel().getWinner())) {
					@SuppressWarnings("deprecation")
					Session session = (new Configuration().configure().buildSessionFactory()).openSession();	
					session.beginTransaction();
					Games game = new Games(this.model.getPlayersModel());
					session.persist(game);
					session.getTransaction().commit();

					List <PlayerModel>list = game.gamePlayersInfos.getPlayers();
					java.util.Iterator<PlayerModel> iter = list.iterator();
					History history;
				    while (iter.hasNext()) {
				        PlayerModel player = iter.next();
						session.beginTransaction();
						history = new History(game.getID(), player.getPlayerID(), player.getPlayerScore());
						session.persist(history);;
						session.getTransaction().commit();
					}
				}
				
				model.getPlayersModel().reset();
				model.unsetCreator();
				model.getMe().setPlayerScore(0);

				return;
			}

		}
		model.setPhase(GamePhase.TWODICES);

	}

	
	/**
	 * Calculate the dices combination score
	 */
	private void calculScores() {
		model.setOneGain(null, 0);
		System.err.println("calcul score");
		model.setPhase(GamePhase.SCORING);
		if (model.getDices().isChouetteVelute()) {
			model.getInteraction()
					.getPlayer()
					.setPlayerScore(
							model.getInteraction().getPlayer().getPlayerScore()
									+ model.getDices().getScore());
			model.setOneGain(model.getInteraction().getPlayer(),model.getDices().getScore());
			model.getInteraction().reset();
			
			
			return;
		}
		if (model.getDices().isSuite()) {
			model.getInteraction()
					.getPlayer()
					.setPlayerScore(
							model.getInteraction().getPlayer().getPlayerScore()
									+ model.getDices().getScore());
			model.setOneGain(model.getInteraction().getPlayer(),model.getDices().getScore());
			model.getInteraction().reset();
			
			return;
		}
		if (model.getDices().isCulDeChouette()) {
			model.getTurn().setPlayerScore(model.getTurn().getPlayerScore()
							+ model.getDices().getScore());
			model.setOneGain(model.getTurn(),model.getDices().getScore());
			
			return;
		}
		if (model.getDices().isChouette()) {
			model.getTurn().setPlayerScore(
					model.getTurn().getPlayerScore()
							+ model.getDices().getScore());
			model.setOneGain(model.getTurn(),model.getDices().getScore());
			return;
		}
		if (model.getDices().isVeloute()) {
			model.getTurn().setPlayerScore(
					model.getTurn().getPlayerScore()
							+ model.getDices().getScore());
			model.setOneGain(model.getTurn(),model.getDices().getScore());
			return;
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleStart(game.network.messages.GameStatus)
	 */
	@Override
	public void handleStart(GameStatus status) {
		handleStatus(status);

	}

	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleRefresh()
	 */
	@Override
	public void handleRefresh() {
		if (model.getGamePhase() == GamePhase.WAITING
				&& model.getCreator() == null) {
			service.sendWaiting(model.getMe().toNet());
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleInteraction(game.network.messages.NetPlayer, game.network.messages.NetPlayer, game.gui.Interaction.Type)
	 */
	@Override
	public void handleInteraction(NetPlayer creator, NetPlayer player, Type type) {
		System.err.println("Handle interacte, phase=" + model.getGamePhase());
		
		System.err.println("Player is in my game ?");
		System.err.println(model.getCreator().toNet() + " " + creator);
		if (!model.getCreator().toNet().equals(creator))
			return;
		System.err.println("expected interact ? ?");
		interact(player, type);

	}

	/**
	 * Verify the interaction received and end it if all players have said the expected sentence.
	 * @param player
	 * @param type
	 */
	public void interact(NetPlayer player, Type type) {
		if (!model.getGamePhase().equals(GamePhase.INTERACTION))
			return;
		if (type.equals(model.getInteraction().getExpected())) {
			model.getInteraction().addPlayer(
					model.getPlayersModel().getFromNet(player));

			if (model.getInteraction().interacCount() >= model
					.getPlayersModel().size()) {
				
				calculScores();
				checkScores();
			}

		}
		
	}





	/**
	 * Send a refresh request to the service
	 */
	public void refresh() {

		service.sendRefresh();

	}

	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleWaitingNotification(game.network.messages.NetPlayer)
	 */
	@Override
	public void handleWaitingNotification(NetPlayer player) {

		if (model.getCreator() == null)
			return;
		if (model.getGamePhase() == GamePhase.WAITING
				&& model.getCreator().equals(model.getMe())) {

			if (!model.getAvailableModel().getPlayers().contains(player)) {
				PlayerModel pm = new PlayerModel(player, Color.black);
				model.getAvailableModel().add(pm);

			}
		}

	}

	/**
	 * @return the game service
	 */
	public IGameService getService() {
		return service;
	}

	/**
	 * Shuffle the player list and send the start game request to the service
	 */
	public void lauchGame() {
		model.getPlayersModel().shuffle();

		model.setPhase(GamePhase.TWODICES);

		service.startGame(model.getGameStatus());

	}
	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleKick(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void handleKick(NetPlayer c, NetPlayer p) {
		if(model.getCreator() == null) return;
		if(model.getCreator().equals(c) && model.getMe().equals(p)){
			model.unsetCreator();
			model.getPlayersModel().reset();
			model.setPhase(GamePhase.WAITING);
		}
		
	}
	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleCancelGame(game.network.messages.NetPlayer)
	 */
	@Override
	public void handleCancelGame(NetPlayer c) {
		if(model.getCreator() == null) return;
		if(model.getCreator().equals(c)){
			model.unsetCreator();
			model.getPlayersModel().reset();
			model.setPhase(GamePhase.WAITING);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see game.network.IGameClient#handleLeaveGame(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void handleLeaveGame(NetPlayer c, NetPlayer p) {
		if(model.getCreator() == null) return;
		if(model.getCreator().equals(c)){
			model.getPlayersModel().remove(p);
		}
		
	}
}
