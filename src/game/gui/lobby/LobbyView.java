package game.gui.lobby;

import game.gui.ListedPlayerView;
import game.gui.OnePlayerModel;
import game.gui.OnePlayerPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LobbyView extends JFrame implements Observer {
	private JPanel pMain;
	private JPanel pPlayerList;
	
	// Listerner

	private LobbyListener listener;

	private LobbyControler controler;

	public LobbyView(LobbyControler gc) {
		players = new ArrayList<OnePlayerPanel>();
		lobbyPlayers = new ArrayList<ListedPlayerView>();
		controler = gc;
		controler.getModel().addObserver(this);
		listener = new LobbyListener();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pMain = new JPanel();
		pMain.setBackground(Color.BLACK);

		this.setContentPane(pMain);
		pMain.setLayout(new BorderLayout(10, 10));


		// pAvailablePlayers.setLayout(new GridLayout(0, 1));

		ListedPlayerView p1 = new ListedPlayerView();
		ListedPlayerView p2 = new ListedPlayerView();
		ListedPlayerView p3 = new ListedPlayerView();
		ListedPlayerView p4 = new ListedPlayerView();
		ListedPlayerView p5 = new ListedPlayerView();
		ListedPlayerView p6 = new ListedPlayerView();
		ListedPlayerView p7 = new ListedPlayerView();

		pAvailablePlayers.add(p1);
		pAvailablePlayers.add(p2);
		pAvailablePlayers.add(p3);
		pAvailablePlayers.add(p4);
		pAvailablePlayers.add(p5);
		pAvailablePlayers.add(p6);
		pAvailablePlayers.add(p7);

		this.setSize(800, 700);
		this.setVisible(true);
	}

	public LobbyControler getControler() {
		return controler;
	}

	class LobbyListener implements ActionListener, FocusListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btConnect) {
				controler.connect(tfName.getText());
			}

		}

		@Override
		public void focusGained(FocusEvent arg0) {
			tfName.selectAll();

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof LobbyModel){
			if ((Event) arg1 == LobbyModel.Event.CONNECTED){
				OnePlayerPanel p = new OnePlayerPanel(controler.getModel().getMe().getPlayerName(),
						controler.getModel().getMe().getPlayerScore(),
						controler.getModel().getMe().getPlayerColor());
				controler.getModel().getMe().addObserver(p);
				players.add(p);
				pPlayerList.add(p);
				pConnect.setVisible(false);
				pAvailablePlayers.setVisible(true);
				pPlayerList.setVisible(true);
			}
			if ((Event) arg1 == LobbyModel.Event.NEWPLAYER){
				OnePlayerModel m = controler.getModel().getPlayers().get(controler.getModel().getPlayers().size()-1);
				OnePlayerPanel p = new OnePlayerPanel(m.getPlayerName(), m.getPlayerScore(), m.getPlayerColor());
				m.addObserver(p);
				players.add(p);
				pPlayerList.add(p);
				
			}
			
		}
		
		
		
	}
}
