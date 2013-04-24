package game.gui;

import game.gui.GameModel.GamePhase;
import game.gui.Interaction.Type;
import game.network.messages.NetPlayer;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;


public class GameControler {
	//private GameView view;
	
	private Random rand;
	private GameModel model;
	private GameHandler gh;
	private Gui view;
	public synchronized Gui getView() {
		return view;
	}

	public synchronized void setView(Gui view) {
		this.view = view;
		gh.setView(view);
	}

	public GameControler(GameModel gm) {
		rand = new Random();
		model = gm;
		gh = new GameHandler(model);
		
	}



	public void roll2Dice() {
		model.getDices().getD1().setFace(rand.nextInt(6)+1);
		model.getDices().getD2().setFace(rand.nextInt(6)+1);
		model.setPhase(GamePhase.ONEDICE);
		gh.service.sendGameStatus(model.getGameStatus());
		
		
		
		
	}

	public GameModel getModel() {
		return model;
	}

//	public void nextPhase() {
//		switch (model.getGamePhase()) {
//		case START:
//			model.setPhase(GameModel.GamePhase.TWODICES);
//			break;
//		case TWODICES:
//			model.setPhase(GameModel.GamePhase.ONEDICE);
//			break;
//		case ONEDICE:
//			// GO TO SCORING OR INTERACTION
//			model.setPhase(GameModel.GamePhase.SCORING);
//			break;
//		case INTERACTION:
//			// GO TO SCORING
//			model.setPhase(GameModel.GamePhase.SCORING);
//			break;
//		case SCORING:
//			// Next player or FINISH if max score is reached.
//			
//			model.setPhase(GameModel.GamePhase.TWODICES);
//			break;
//
//		default:
//			break;
//		}
//		
//	}
	
	public void connect(String login, String password) {
		
		Session session = this.model.getSession();
		
		session.beginTransaction();
		
		Query query = session.createQuery("from PlayerModel where login ='"+login+"'");                 
        PlayerModel player = (PlayerModel)query.uniqueResult();
        
        session.getTransaction().commit();
        
        if(player == null)System.out.println("Ce joueur n'existe pas");
        else {
        	if(player.getPlayerPassword().equals(password)){
				NetPlayer myId = new NetPlayer();
				myId.setNetId(gh.getService().getMyNetId());
				myId.setGlobalId(player.getPlayerID());
				//PlayerModel me = new PlayerModel(player.getPlayerLogin(), Color.GREEN);
				PlayerModel me = player;
				me.setPlayerColor(Color.GREEN);
				me.setNetId(myId);
				model.setMe(me);
				if(view != null) view.showMenu();
				System.out.println("Connecté en tant que "+login+".\n");
        	}
        	else {
        		System.out.println("Mauvais mot de passe.");
        		view.showLogin();
        	}
        }
	}
	
	public void createAccount(String login, String password, int age, char sex, String city) {
		
		this.model.getSession().beginTransaction();
		
		Query query = this.model.getSession().createQuery("from PlayerModel where login ='"+login+"'");                 
        PlayerModel player = (PlayerModel)query.uniqueResult();
        
        if(player == null){
        	// Il n'esxite pas déjà un joueur avec ce login dans la BDD
        	PlayerModel newPlayer = new PlayerModel(login, password, age, sex, city);
        	this.model.getSession().persist(newPlayer);
        	System.out.println("Joueur enregistré :");
        	System.out.println("login :"+newPlayer.getPlayerLogin());
        	System.out.println("password :"+newPlayer.getPlayerPassword());
        	System.out.println("age :"+newPlayer.getPlayerAge());
        	System.out.println("sex :"+newPlayer.getPlayerSex());
        	System.out.println("city :"+newPlayer.getPlayerCity());
        	if(view != null) view.showLogin();
        }
        // TODO Afficher l erreur
        else view.showError("login déjà utilisé.");
        
        this.model.getSession().getTransaction().commit();
        
	}
	
	public void deleteAccount(String login){
		
		this.model.getSession().beginTransaction();
		
		Query query = this.model.getSession().createQuery("from PlayerModel where login ='"+login+"'");                 
        PlayerModel player = (PlayerModel)query.uniqueResult();
        
        if(player == null) System.out.println("Joueur inexistant.");
        else{
        	this.model.getSession().delete(player);
        	System.out.println("Compte joueur supprimé.");
        }
        
        this.model.getSession().getTransaction().commit();
	}
	
	public void queryPlayers() {
	    	
		//Session session = this.model.getSession();
		this.model.getSession().beginTransaction();
		
	    Query query = this.model.getSession().createQuery("from PlayerModel");                 
	    List <PlayerModel>list = query.list();
	    java.util.Iterator<PlayerModel> iter = list.iterator();
	    while (iter.hasNext()) {
	
	        PlayerModel player = iter.next();
	        System.out.println("Player: " + player.getPlayerLogin() +", " + player.getPlayerPassword()+", " + player.getPlayerAge()+", " + player.getPlayerSex()+", " + player.getPlayerCity());
	
	    }
	
	    this.model.getSession().getTransaction().commit();
	
	}

	public void roll1Dice() {
		System.err.println("ROLL 1 DICE!!!");
		model.getDices().getD3().setFace(rand.nextInt(6)+1);
		model.setPhase(GamePhase.CHECKDICE);
		gh.service.sendGameStatus(model.getGameStatus());
		gh.checkDices(model.getGameStatus());
	}

	public void create() {
		model.setPhase(GamePhase.WAITING);
		model.getPlayersModel().reset();
		model.getPlayersModel().add(model.getMe());
		model.setCreator(model.getMe());
		view.showAvailable();
		refresh();
		
	}

	public void join() {
		model.setPhase(GamePhase.WAITING);
		gh.getService().sendWaiting(model.getMe().toNet());
		view.showWaiting();
		
	}
	public void cancel() {
		model.unsetCreator();
		model.setPhase(GamePhase.MENU);
		model.getPlayersModel().reset();
		view.showMenu();
	}

	public void refresh() {
		getModel().getAvailableModel().reset();
		gh.refresh();
		
	}

	public void invite(NetPlayer player) {
		gh.getService().invitPlayer(model.getMe().toNet(), player);
		
	}

	public void launch() {
		
		gh.lauchGame();
		
	}
	public void saySuite(){
		gh.interact(model.getMe().toNet(), Type.SUITE);
		gh.service.sendInteraction(model.getCreator().toNet(), model.getMe().toNet(), Type.SUITE);
	}
	public void sayChouetteVeloutte() {
		gh.interact(model.getMe().toNet(), Type.CHOUETTEVELOUTE);

		gh.service.sendInteraction(model.getCreator().toNet(), model.getMe().toNet(), Type.CHOUETTEVELOUTE);
	}

	public void cancelNewAccount() {
		model.unsetCreator();
		model.setPhase(GamePhase.MENU);
		view.showLogin();
		
	}

	public void cancelGame() {
		gh.service.sendCancel(model.getCreator().toNet());
		model.getPlayersModel().reset();
		model.unsetCreator();
		cancel();
		
	}

	public void cancelWaiting() {
		if(model.getCreator() == null)
			cancel();
		else {
			gh.service.sendLeave(model.getCreator().toNet(), model.getMe().toNet());
			cancel();
		}
		
	}
	
}
