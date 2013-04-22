import game.gui.GameControler;
import game.gui.GameModel;
import game.gui.PlayerModel;
import game.gui.SessionFactoryUtil;
import game.gui.PlayerModel.Sex;

import java.util.List;

import javax.swing.Spring;

import org.hibernate.Query;
import org.hibernate.Session;


public class TestPlayersBDD {

    public static void main(String[] args) {
    	
    	///////////////////////////////////////////////////////
    	////////////////// TEST AJOUT PLAYER //////////////////
    	///////////////////////////////////////////////////////
    	
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        //createPlayer(session, "clement", "toto", 99, 'M', "Pau");
        
        //createPlayer(session, "geoffrey", "titi", 23, 'M', "Pau");

        //queryPerson(session);
        
//        deletePlayer(session, "Clement");
        
        
        // Affichage joueurs de la BDD
        queryPlayers(session);
        
        /////////////////////////////////////////////////////
        ////////////////// TEST CONNECTION //////////////////
        /////////////////////////////////////////////////////
        
        GameModel gm_test = new GameModel();
		GameControler gc_test = new GameControler(gm_test);
		
		//session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		//session = gm_test.getSession();
		//session.beginTransaction();
		gc_test.connect("clement","toto");
		
		//session.getTransaction().commit();
    }

    private static void queryPlayers(Session session) {
        Query query = session.createQuery("from PlayerModel");                 
        List <PlayerModel>list = query.list();
        java.util.Iterator<PlayerModel> iter = list.iterator();
        while (iter.hasNext()) {

            PlayerModel player = iter.next();
            System.out.println("Player: " + player.getPlayerLogin() +", " + player.getPlayerPassword()+", " + player.getPlayerAge()+", " + player.getPlayerSex()+", " + player.getPlayerCity());

        }

        session.getTransaction().commit();

    }

    public static void createPlayer(Session session, String login, String password, int age, char sex, String city) {
        PlayerModel player = new PlayerModel(login, password, age, sex, city);

        session.save(player);
    }
    
    public static void deletePlayer(Session session, String login) {
    	Query query = session.createQuery("from PlayerModel where login ='"+login+"'");
    	PlayerModel player = (PlayerModel)query.uniqueResult();
    	
    	session.delete(player);
    	
    }
}

