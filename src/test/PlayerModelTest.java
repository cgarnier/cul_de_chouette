package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import game.gui.PlayerModel;
import game.network.messages.NetPlayer;

import org.junit.Test;

import communication.ProcessIdentifier;

public class PlayerModelTest {

	@Test
	public void testEqualsObject() {
		NetPlayer np1 = new NetPlayer();
		np1.setGlobalId(41);
		np1.setNetId(new ProcessIdentifier(2));

		NetPlayer np2 = new NetPlayer();
		np2.setGlobalId(41);
		np2.setNetId(new ProcessIdentifier(2));
		
		PlayerModel pm = new PlayerModel(np1, Color.blue);
		PlayerModel pm1 = new PlayerModel(np2, Color.blue);
		
		assertTrue(pm.equals(np2));
		assertTrue(pm.equals(pm1));
		assertTrue(pm.equals(np1));
		assertTrue(pm.equals(pm));
		
		assertTrue(np1.equals(np2));
		assertTrue(np1.equals(pm1));
		assertTrue(np1.equals(np1));
		assertTrue(np1.equals(pm));
		
		ArrayList<PlayerModel> plList = new ArrayList<PlayerModel>();
		plList.add(pm1);
		plList.add(pm);
		
		assertTrue(plList.contains(pm1));
		assertTrue(plList.contains(np2));
		
		
		
		
		
		
		
	}

}
