package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import game.network.GameService;

public class main {

	GameService gs;
	ClientTest gt;

	public main() {
		gs = new GameService();
		gt = new ClientTest(gs);
		gs.init();
		gs.setGameClient(gt);
		commandPrompt();
	}

	public static void main(String[] args) {
		new main();

	}

	public void commandPrompt() {
		String message = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));

		while (true) {
			// read the user entry
			System.out.println("Votre command ('end' pour finir) :");
			try {
				message = input.readLine();
			} catch (IOException ex) {
				System.err.println("Erreur pendant la lecture clavier : " + ex);
				System.exit(2);
			}

			// if end, disconnect from the system and exit the JVM
			if (message.equals("end")) {
				System.out.println("A la prochaine !");
				gs.disconnect();
				System.exit(0);
			}

			// brodcast the message

//			System.out.print(" --> Envoi commande ... ");
//			if (message.equals("create"))
//				gs.createGame();
//			if (message.equals("invite"))
//				for (ProcessIdentifier id : gs.idService.getAllIdentifiers()) {
//					if (!id.equals(gs.idService.getMyIdentifier()))
//						gs.invitPlayer(id);
//				}
//			if(message.equals("start"))
//				gs.startGame();
//			if(message.equals("roll2d"))
//				gs.rollTwoDice(null, null);
//			if(message.equals("roll1d"))
//				gs.rollOneDice(null);
//			
//			System.out.println("done");

		}
	}

}
