package game.gui;

import game.gui.GameModel.GamePhase;
import game.gui.Interaction.Type;
import game.network.NetPlayer;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Main controler of the game
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
/**
 * @author clement
 *
 */
public class GameControler {
	//private GameView view;
	
	private Random rand;
	private GameModel model;
	private GameHandler gh;
	private Gui view;
	private boolean cheat;
	public Gui getView() {
		return view;
	}

	public void setView(Gui view) {
		this.view = view;
		gh.setView(view);
	}

	public GameControler(GameModel gm) {
		rand = new Random();
		model = gm;
		gh = new GameHandler(model);
		
	}



	/**
	 * Random the Chouette dices values
	 */
	public void roll2Dice() {
		if(cheat){
			model.getDices().getD1().setFace(6);
			model.getDices().getD2().setFace(6);
		}else {
		model.getDices().getD1().setFace(rand.nextInt(6)+1);
		model.getDices().getD2().setFace(rand.nextInt(6)+1);
		}
		model.setPhase(GamePhase.ONEDICE);
		gh.service.sendGameStatus(model.getGameStatus());
		
		
		
		
	}

	public GameModel getModel() {
		return model;
	}


	
	/**
	 * Allow a player to login in game
	 * @param login
	 * @param password
	 */
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
				
				PlayerModel me = player;
				me.setPlayerColor(Color.GREEN);
				me.setNetId(myId);
				model.setMe(me);
				if(view != null) view.showMenu();
				
        	}
        	else {
        		view.showError("Mauvais mot de passe.");
        		view.showLogin();
        	}
        }
	}
	
	
	/**
	 * Allow someone to create a new account. Called from the CreateAccountView.
	 * @param login
	 * @param password
	 * @param age
	 * @param sex
	 * @param city
	 */
	public void createAccount(String login, String password, int age, char sex, String city) {
		// TODO mv this method to GameService
		this.model.getSession().beginTransaction();
		
		Query query = this.model.getSession().createQuery("from PlayerModel where login ='"+login+"'");                 
        PlayerModel player = (PlayerModel)query.uniqueResult();
        
        if(player == null){
        	// Il n'esxite pas déjà un joueur avec ce login dans la BDD
        	PlayerModel newPlayer = new PlayerModel(login, password, age, sex, city);
        	this.model.getSession().persist(newPlayer);

        	// TODO Remove this test
        	if(view != null) view.showLogin();
        }
        
        else view.showError("login déjà utilisé.");
        
        this.model.getSession().getTransaction().commit();
        
	}
	
	
	/**
	 * Delete a player account
	 * @param login
	 */
	public void deleteAccount(String login){
		// TODO mv this method to GameService
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
	
	@Deprecated
	public void queryPlayers() {
	    	
		//Session session = this.model.getSession();
		this.model.getSession().beginTransaction();
		
	    Query query = this.model.getSession().createQuery("from PlayerModel");                 
	    @SuppressWarnings("unchecked")
		List <PlayerModel>list = query.list();
	    java.util.Iterator<PlayerModel> iter = list.iterator();
	    while (iter.hasNext()) {
	
	        PlayerModel player = iter.next();
	        System.out.println("Player: " + player.getPlayerLogin() +", " + player.getPlayerPassword()+", " + player.getPlayerAge()+", " + player.getPlayerSex()+", " + player.getPlayerCity());
	
	    }
	
	    this.model.getSession().getTransaction().commit();
	
	}

	
	/**
	 * Randomize the cul dice
	 */
	public void roll1Dice() {
		if(cheat) {model.getDices().getD3().setFace(6);
		cheat = false;
		}
		else
		model.getDices().getD3().setFace(rand.nextInt(6)+1);
		model.setPhase(GamePhase.CHECKDICE);
		gh.service.sendGameStatus(model.getGameStatus());
		gh.checkDices(model.getGameStatus());
	}

	
	/**
	 * Create a new game. Called from 'go' button in the game view
	 */
	public void create() {
		model.setPhase(GamePhase.WAITING);
		model.getPlayersModel().reset();
		model.getPlayersModel().add(model.getMe());
		model.setCreator(model.getMe());
		view.showAvailable();
		refresh();
		
	}

	
	/**
	 * Put the game in the waiting invite phase
	 */
	public void join() {
		model.setPhase(GamePhase.WAITING);
		gh.getService().sendWaiting(model.getMe().toNet());
		view.showWaiting();
		
	}
	
	
	/**
	 * Cancel action
	 */
	public void cancel() {
		model.unsetCreator();
		model.setPhase(GamePhase.MENU);
		model.getPlayersModel().reset();
		view.showMenu();
	}

	
	/**
	 * Refresh the 'waiting for game' players
	 */
	public void refresh() {
		getModel().getAvailableModel().reset();
		gh.refresh();
		
	}

	
	/**
	 * Invite a player to the current game
	 * @param player
	 */
	public void invite(NetPlayer player) {
		gh.getService().invitPlayer(model.getMe().toNet(), player);
		
	}

	
	/**
	 * Start the current game
	 */
	public void launch() {
		gh.lauchGame();
		
		// TODO ??? -_-' useless
		model.setGame(new Games());
		model.getGame().setPlayers(model.getPlayersModel().getPlayers());
	}
	
	
	/**
	 * Say 'Grelotte ça picote !' in the player interaction phase
	 */
	public void saySuite(){
		gh.interact(model.getMe().toNet(), Type.SUITE);
		gh.service.sendInteraction(model.getCreator().toNet(), model.getMe().toNet(), Type.SUITE);
	}
	
	/**
	 * Say 'Pas mou le caillou!' in the player interaction phase
	 */
	public void sayChouetteVeloutte() {
		gh.interact(model.getMe().toNet(), Type.CHOUETTEVELOUTE);

		gh.service.sendInteraction(model.getCreator().toNet(), model.getMe().toNet(), Type.CHOUETTEVELOUTE);
	}

	/**
	 * Create new player action.
	 */
	public void cancelNewAccount() {
		model.unsetCreator();
		model.setPhase(GamePhase.MENU);
		view.showLogin();
		
	}

	/**
	 * Cancel game action
	 */
	public void cancelGame() {
		gh.service.sendCancel(model.getCreator().toNet());
		model.getPlayersModel().reset();
		model.unsetCreator();
		cancel();
		
	}

	/**
	 * Cancel waiting for game action.
	 */
	public void cancelWaiting() {
		if(model.getCreator() == null)
			cancel();
		else {
			gh.service.sendLeave(model.getCreator().toNet(), model.getMe().toNet());
			cancel();
		}
		
	}

	
	/**
	 * Cheat code activation
	 */
	public void cheat() {
		this.cheat = true;
		System.err.println(" six six six ftw");
		
	}
	
}
