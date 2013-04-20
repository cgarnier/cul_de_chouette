package game.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import game.gui.GameModel;
import game.network.messages.GameStatus;
import game.network.messages.GameStatusMessage;
import game.network.messages.InvitMessage;
import game.network.messages.JoinAnswerMessage;
import game.network.messages.NetPlayer;
import game.network.messages.RefreshMessage;
import game.network.messages.StartMessage;
import game.network.messages.WaitingPlayerMessage;
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

public class GameService implements IGameService {
	IDistributedServices services;
	ICommunication commService;
	public IIdentification idService;
	IBroadcast broadcastService;


	Thread netListener;

	IGameClient client;
	private ProcessIdentifier gameId;
	

	

	
	




	
	







	public void init() {

		// setting of the simulated system
		ReliabilitySetting setting = new ReliabilitySetting();
		setting.setTransmissionDelayLowerBound(FaultLevel.MEDIUM);
		setting.setTransmissionDelayUpperBound(FaultLevel.MEDIUM);
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
		this.gameId = this.idService.getMyIdentifier();

	}

	@Override
	public void invitPlayer(NetPlayer creator, NetPlayer guest) {
		try {
			this.broadcastService.broadcast(new InvitMessage(creator,guest));
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void refreshGameList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saySuite() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sayChouetteVeloute() {
		// TODO Auto-generated method stub

	}



	/*
	 * 
	 * 
	 * HandleMessage
	 */
	private void handleMessage(Message msg) {

		/*
		 * Invit
		 */
		if (msg.getData() instanceof InvitMessage) {
			InvitMessage invit = (InvitMessage) msg.getData();
			this.client.handleInvit(invit.getCreator(), invit.getGuest());
			return;
		}

		/*
		 * JoinAnswer
		 */
		if (msg.getData() instanceof JoinAnswerMessage) {
			JoinAnswerMessage answer = (JoinAnswerMessage) msg.getData();
			this.client.handleJoinAnswer(answer.getCreator(), answer.getPlayer());
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
		if (msg.getData() instanceof WaitingPlayerMessage) {
			NetPlayer p = ((WaitingPlayerMessage) msg.getData()).getPlayer();
			
			this.client.handleWaitingNotification(p);
			return;
		}

		

	}

	


	public void sendGameStatus(GameStatus status) {
		try {
			GameStatusMessage msg = new GameStatusMessage(status);
			
			this.broadcastService.broadcast(msg);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean imCreator() {
		return gameId.equals(this.idService.getMyIdentifier());
	}

	@Override
	public void joinGame(NetPlayer creator, NetPlayer me) {
		
		try {
			this.broadcastService.broadcast(new JoinAnswerMessage(me,creator));
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public void setGameClient(IGameClient client) {

		this.client = client;

	}



	public void disconnect() {
		services.disconnect();

	}

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

	@Override
	public void sendRefresh() {
		try {
			this.broadcastService.broadcast(new RefreshMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Override
	public void sendWaiting(NetPlayer player) {
		System.out.println("Sending waiting ...");
		try {
			this.broadcastService.broadcast(new WaitingPlayerMessage(player));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
@Override
public ProcessIdentifier getMyNetId() {
	// TODO Auto-generated method stub
	return idService.getMyIdentifier();
}



}
