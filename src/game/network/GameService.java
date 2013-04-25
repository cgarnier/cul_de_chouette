package game.network;

import game.gui.Interaction;
import game.gui.Interaction.Type;
import game.network.messages.CancelGameMessage;
import game.network.messages.GameStatusMessage;
import game.network.messages.InviteMessage;
import game.network.messages.JoinAnswerMessage;
import game.network.messages.KickPlayerMessage;
import game.network.messages.LeaveGameMessage;
import game.network.messages.PlayerInteractionMessage;
import game.network.messages.RefreshMessage;
import game.network.messages.StartMessage;
import game.network.messages.WaitingMessage;
import service.DistributedServicesMiddleware;
import service.IBroadcast;
import service.ICommunication;
import service.IDistributedServices;
import service.IIdentification;

import communication.CommunicationException;
import communication.FaultLevel;
import communication.Message;
import communication.ProcessIdentifier;
import communication.ReliabilitySetting;

/**
 * Game service class
 * 
 * Process messages from the network and forward their information to a game client.
 * Allow the game client to interact with other process on the network.
 * 
 * Use the diffusion API.
 * 
 * @author clement
 *
 */
public class GameService implements IGameService {
	IDistributedServices services;
	ICommunication commService;
	public IIdentification idService;
	IBroadcast broadcastService;

	Thread netListener;

	IGameClient client;
	/**
	 * Initializes the game service.
	 * Auth on the id service and setup the reliabilities settings.
	 */
	public void init() {

		// setting of the simulated system
		ReliabilitySetting setting = new ReliabilitySetting();
		setting.setTransmissionDelayLowerBound(FaultLevel.NONE);
		setting.setTransmissionDelayUpperBound(FaultLevel.NONE);
		setting.setPacketLostLevel(FaultLevel.NONE);
		setting.setCrashLevel(FaultLevel.NONE);
		setting.setReliable(true);
		setting.setDebugFault(true);

		// connection to the system
		services = new DistributedServicesMiddleware();
		try {
			services.connect(setting);
		} catch (CommunicationException e) {
			System.err.println("Impossible de se connecter : " + e);
		}
		// get the service access points
		commService = services.getCommunicationService();
		idService = services.getIdentificationService();
		broadcastService = services.getReliableBroadcastService();

		// as we are not directly informed when the process id has been
		// received, wait a short time
		// to be almost sure to have received it when printing the identifier
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}
		System.out.println("OK, connexion réalisée, je suis : "
				+ idService.getMyIdentifier() + "\n");

		netListener = new Thread() {
			@Override
			public void run() {
				Message msg;
				while (true) {
					msg = broadcastService.synchDeliver();
					GameService.this.handleMessage(msg);
				}
			}
		};
		netListener.start();
	}

	public GameService() {
		init();
	}

	@Override
	public void createGame() {
		this.idService.getMyIdentifier();

	}

	@Override
	public void invitPlayer(NetPlayer creator, NetPlayer guest) {
		try {
			this.broadcastService.broadcast(new InviteMessage(creator, guest));
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * Handle and process the different type of message incoming from the network liestener thread.
	 * @param msg Network message
	 */
	private void handleMessage(Message msg) {

		/*
		 * Invit
		 */
		if (msg.getData() instanceof InviteMessage) {
			InviteMessage invit = (InviteMessage) msg.getData();
			this.client.handleInvit(invit.getCreator(), invit.getGuest());
			return;
		}

		/*
		 * JoinAnswer
		 */
		if (msg.getData() instanceof JoinAnswerMessage) {
			JoinAnswerMessage answer = (JoinAnswerMessage) msg.getData();
			this.client.handleJoinAnswer(answer.getCreator(),
					answer.getPlayer());
			return;
		}
		/*
		 * Status
		 */
		if (msg.getData() instanceof GameStatusMessage) {
			GameStatus status = ((GameStatusMessage) msg.getData()).getStatus();
			this.client.handleStatus(status);
			return;
		}
		/*
		 * Start
		 */
		if (msg.getData() instanceof StartMessage) {
			GameStatus status = ((StartMessage) msg.getData()).getStatus();

			this.client.handleStart(status);
			return;
		}
		/*
		 * Refresh
		 */
		if (msg.getData() instanceof RefreshMessage) {

			this.client.handleRefresh();
			return;
		}
		/*
		 * Waiting notif
		 */
		if (msg.getData() instanceof WaitingMessage) {
			NetPlayer p = ((WaitingMessage) msg.getData()).getPlayer();

			this.client.handleWaitingNotification(p);
			return;
		}
		
		/*
		 * Intercation
		 * 
		 */
		if (msg.getData() instanceof PlayerInteractionMessage) {
			NetPlayer p = ((PlayerInteractionMessage) msg.getData()).getPlayer();
			NetPlayer c = ((PlayerInteractionMessage) msg.getData()).getCreator();
			
			Interaction.Type t = 
					((PlayerInteractionMessage) msg.getData()).getType();
			this.client.handleInteraction(c,p, t);
			return;
		}
		
		/*
		 * Kick
		 * 
		 */
		if (msg.getData() instanceof KickPlayerMessage) {
			NetPlayer p = ((KickPlayerMessage) msg.getData()).getPlayer();
			NetPlayer c = ((KickPlayerMessage) msg.getData()).getCreator();
			
			
			this.client.handleKick(c,p);
			return;
		}
		
		/*
		 * Cancel game
		 * 
		 */
		if (msg.getData() instanceof CancelGameMessage) {
			
			NetPlayer c = ((CancelGameMessage) msg.getData()).getCreator();
			
			
			this.client.handleCancelGame(c);
			return;
		}
		
		/*
		 * Leave game
		 * 
		 */
		if (msg.getData() instanceof LeaveGameMessage) {
			
			NetPlayer c = ((LeaveGameMessage) msg.getData()).getCreator();
			NetPlayer p = ((LeaveGameMessage) msg.getData()).getPlayer();
			
			
			this.client.handleLeaveGame(c,p);
			return;
		}
		
		
		

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#sendGameStatus(game.network.messages.GameStatus)
	 */
	public void sendGameStatus(GameStatus status) {
		try {
			GameStatusMessage msg = new GameStatusMessage(status);

			this.broadcastService.broadcast(msg);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#joinGame(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void joinGame(NetPlayer creator, NetPlayer me) {

		try {
			this.broadcastService.broadcast(new JoinAnswerMessage(me, creator));
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#setGameClient(game.network.IGameClient)
	 */
	@Override
	public void setGameClient(IGameClient client) {

		this.client = client;

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#disconnect()
	 */
	@Override
	public void disconnect() {
		services.disconnect();

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#startGame(game.network.messages.GameStatus)
	 */
	@Override
	public void startGame(GameStatus status) {

		StartMessage msg = new StartMessage(status);
		try {
			this.broadcastService.broadcast(msg);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#sendRefresh()
	 */
	@Override
	public void sendRefresh() {
		try {
			this.broadcastService.broadcast(new RefreshMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#sendWaiting(game.network.messages.NetPlayer)
	 */
	@Override
	public void sendWaiting(NetPlayer player) {
		System.out.println("Sending waiting ...");
		try {
			this.broadcastService.broadcast(new WaitingMessage(player));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#getMyNetId()
	 */
	@Override
	public ProcessIdentifier getMyNetId() {
		// TODO Auto-generated method stub
		return idService.getMyIdentifier();
	}

	/* (non-Javadoc)
	 * @see game.network.IGameService#sendInteraction(game.network.messages.NetPlayer, game.network.messages.NetPlayer, game.gui.Interaction.Type)
	 */
	@Override
	public void sendInteraction(NetPlayer creator, NetPlayer player, Type type) {
		System.out.println("Sending interact ...");
		try {
			this.broadcastService.broadcast(new PlayerInteractionMessage(creator, player, type));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	/* (non-Javadoc)
	 * @see game.network.IGameService#sendKick(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void sendKick(NetPlayer net, NetPlayer guest) {
		try {
			this.broadcastService.broadcast(new KickPlayerMessage(net, guest));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/* (non-Javadoc)
	 * @see game.network.IGameService#sendCancel(game.network.messages.NetPlayer)
	 */
	@Override
	public void sendCancel(NetPlayer net) {
		
		try {
			this.broadcastService.broadcast(new CancelGameMessage(net));
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	/* (non-Javadoc)
	 * @see game.network.IGameService#sendLeave(game.network.messages.NetPlayer, game.network.messages.NetPlayer)
	 */
	@Override
	public void sendLeave(NetPlayer net, NetPlayer net2) {
		// TODO Auto-generated method stub
		try {
			this.broadcastService.broadcast(new LeaveGameMessage(net, net2));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}



}
