package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class LobbyView extends JFrame {
	private JPanel pMain;
	private JPanel pPlayerList;
	private JPanel pAvailablePlayers;
	
	public LobbyView() {
		
		
		pMain = new JPanel();
		pMain.setBackground(Color.BLACK);

		
		
		this.setContentPane(pMain);
		pMain.setLayout(new BorderLayout(10, 10));
		
		pPlayerList = new JPanel();
		pPlayerList.setBackground(Color.gray);
		pPlayerList.setPreferredSize(new Dimension(280,600));
		pMain.add(pPlayerList, BorderLayout.LINE_END);
		
		pAvailablePlayers = new JPanel();
		pAvailablePlayers.setPreferredSize(new Dimension(500, 200));
		pAvailablePlayers.setBackground(Color.gray);
		
		pMain.add(pAvailablePlayers, BorderLayout.LINE_START);
		
		//pAvailablePlayers.setLayout(new GridLayout(0, 1));
		
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
	public static void main(String[] args) {
		new LobbyView();
	}
	class LobbyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
