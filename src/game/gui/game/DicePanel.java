package game.gui.game;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DicePanel extends JPanel implements Observer{

	
	private ArrayList<BufferedImage> images;
	private int diceFace;
	public DicePanel(int face) {
		this.setOpaque(false);
		this.diceFace = face;
		images = new ArrayList<BufferedImage>();
		for (int i = 1; i < 7; i++) {
			try {
				images.add( ImageIO.read(new File("Dices/"+i+".png")) );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		this.setPreferredSize(new Dimension(200, 200));
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		images.get(this.diceFace -1);
		g.drawImage(images.get(this.diceFace -1), 0, 0,70,70, null);
		g.dispose();
	}
	public void setFace(int face) {
		this.diceFace = face;
		this.repaint();
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0 instanceof DiceModel){
			this.setFace(((DiceModel)arg0).getFace());
		}
		
	}
	
}
