package game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class GameControler {
	//private GameView view;
	
	private Random rand;
	private GameModel model;
	public GameControler(GameModel gm) {
		rand = new Random();
		model = gm;
		
	}

	public void playPhase1() {
		int dice1 = rand.nextInt(6)+1;
		int dice2 = rand.nextInt(6)+1;
	}
	public void playPhase2(){
		
	}

	public void roll2Dice() {
		model.getDice1().setFace(rand.nextInt(6)+1);
		model.getDice2().setFace(rand.nextInt(6)+1);
		System.out.println("roll");
		
		
	}

	public GameModel getModel() {
		return model;
	}

	public void nextPhase() {
		switch (model.getGamePhase()) {
		case START:
			model.setPhase(GameModel.GamePhase.TWODICES);
			break;
		case TWODICES:
			model.setPhase(GameModel.GamePhase.ONEDICE);
			break;
		case ONEDICE:
			// GO TO SCORING OR INTERACTION
			model.setPhase(GameModel.GamePhase.SCORING);
			break;
		case INTERACTION:
			// GO TO SCORING
			model.setPhase(GameModel.GamePhase.SCORING);
			break;
		case SCORING:
			// Next player or FINISH if max score is reached.
			
			model.setPhase(GameModel.GamePhase.TWODICES);
			break;

		default:
			break;
		}
		
	}

	public void roll1Dice() {
		// TODO Auto-generated method stub
		model.getDice3().setFace(rand.nextInt(6)+1);
	}
	
}
