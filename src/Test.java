import game.gui.GameControler;
import game.gui.GameModel;
import game.gui.PlayerModel;
import game.gui.SessionFactoryUtil;
import game.gui.PlayerModel.Sex;

import java.util.List;

import javax.swing.Spring;

import org.hibernate.Query;
import org.hibernate.Session;


public class Test {

    public static void main(String[] args) {
    	
    	// instanciation du GameModel et du GameControler
    	GameModel gm_test = new GameModel();
		GameControler gc_test = new GameControler(gm_test);
    	
		
    	///TEST AJOUT PLAYER

     /*  Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        //createPlayer(session, "clement", "toto", 99, 'M', "Pau");
        
        //createPlayer(session, "geoffrey", "titi", 23, 'M', "Pau");
        
        //deletePlayer(session, "Clement");
    */
    	
        
        // Affichage joueurs de la BDD
        gc_test.queryPlayers();
        

        /// TEST CONNECTION
		//gc_test.connect("clement","toto");
		

		/// TEST CREATION COMPTE
		gc_test.createAccount("alex", "tata", 22, 'M', "Pau");
		
		
		/// TEST SUPPRESSION COMPTE
		gc_test.deleteAccount("alex");
		
    }
}

