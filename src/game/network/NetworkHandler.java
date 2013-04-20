package game.network;

import game.gui.GameModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import service.DistributedServicesMiddleware;
import service.IBroadcast;
import service.ICommunication;
import service.IDistributedServices;
import service.IIdentification;

import communication.CommunicationException;
import communication.FaultLevel;
import communication.Message;
import communication.ReliabilitySetting;

public class NetworkHandler extends Observable{
	IDistributedServices services;
    ICommunication commService;
    IIdentification idService;
    IBroadcast broadcastService;
    
    GameModel model;

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

        // as we are not directly informed when the process id has been received, wait a short time
        // to be almost sure to have received it when printing the identifier
        try { Thread.sleep(200); } catch(Exception e) { }
        System.out.println("OK, connexion réalisée, je suis : " + idService.getMyIdentifier()+ "\n");
    }

    public void papoter() {
     
        String message = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // read the user entry
            System.out.println("Votre message ('end' pour finir) :");
            try {
                message = input.readLine();
            } catch (IOException ex) {
                System.err.println("Erreur pendant la lecture clavier : " + ex);
                System.exit(2);
            }

            // if end, disconnect from the system and exit the JVM
            if (message.equals("end")) {
                System.out.println("A la prochaine !");
                services.disconnect();
                System.exit(0);
            }

            // brodcast the message
            try {
                System.out.print(" --> Envoi message ... ");
                broadcastService.broadcast(message);
                System.out.println("done");
            } catch (CommunicationException ex) {
                System.err.println(" *** communication problem: " + ex);
            }
        }
    }
    public NetworkHandler(GameModel model) {
    	this.model = model;
		this.init();
	}

    public void notifyNetworkActivity(Message msg) {
		this.setChanged();
		this.notifyObservers(msg);
	}
    
    public void run() {
    	new Thread(){
    		@Override
    		public void run() {
    	        while (true) {
    	        	Message msg;
    	            msg = broadcastService.synchDeliver();
    	            NetworkHandler.this.notifyNetworkActivity(msg);
    	            System.out.println("[" + msg.getProcessId().getId() + "] " + msg.getData());
    	        }
    		}
    	};
     

    }

    
}
