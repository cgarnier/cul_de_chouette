package game.gui;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(28, 85, 474, 470);
		contentPane.add(leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(28, 12, 758, 64);
		contentPane.add(topPanel);
		
		JLabel lblCulDeChouette = new JLabel("Cul de chouette P2P");
		topPanel.add(lblCulDeChouette);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(514, 85, 272, 470);
		contentPane.add(rightPanel);
		
		
		leftPanel.add(new AvailablePlayersPanel());
		rightPanel.add(new PlayerListPanel());
	}
}
