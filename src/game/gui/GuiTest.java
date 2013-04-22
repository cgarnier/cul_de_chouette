package game.gui;

import game.gui.login.LoginPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class GuiTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GuiTest frame = new GuiTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiTest() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 820);
		contentPane = new ImagePanel("Dices/v1.jpg");
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel rightPanel2 = new JPanel();
		rightPanel2.setBounds(256, 84, 246, 691);
		contentPane.add(rightPanel2);
		rightPanel2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(24, 84, 139, 467);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(leftPanel);
		
		rightPanel2.setOpaque(false);
		leftPanel.setOpaque(false);
		
		
		//rightPanel2.add(new AvailablePlayersPanel());
		//rightPanel2.add(new LoginPanel());
		rightPanel2.add(new MenuPanel());
		//rightPanel2.add(new WaitingPanel());
		//rightPanel2.add(new GamePanel());
		//leftPanel.add(new PlayerListPanel());
	}
}
