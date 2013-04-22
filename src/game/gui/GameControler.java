package game.gui;

import game.gui.GameModel.GamePhase;
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
	private GuiTest view;
	public synchronized GuiTest getView() {
		return view;
	}

	public synchronized void setView(GuiTest view) {
		this.view = view;
	}

	public GameControler(GameModel gm) {
		rand = new Random();
		model = gm;
		gh = new GameHandler(model);
		
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
        	view.showLogin();
        }
        // TODO Afficher l erreur
        else System.out.println("login déjà utilisé.");
        
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
		// TODO Auto-generated method stub
		model.getDice3().setFace(rand.nextInt(6)+1);
	}

	public void create() {
		model.setPhase(GamePhase.WAITING);
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
		view.showMenu();
	}

	public void refresh() {
		getModel().getAvailableModel().reset();
		gh.refresh();
		
	}

	public void invite(NetPlayer player) {
		gh.getService().invitPlayer(model.getMe().toNet(), player);
		
	}
	
}
