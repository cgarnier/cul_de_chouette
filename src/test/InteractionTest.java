package test;

import static org.junit.Assert.*;
import game.gui.Interaction;
import game.gui.PlayerModel;
import game.network.messages.NetPlayer;

import java.awt.Color;

import org.junit.Test;

import communication.ProcessIdentifier;

public class InteractionTest {

	@Test
	public void testAddPlayer() {
		NetPlayer np1 = new NetPlayer();
		np1.setGlobalId(41);
		np1.setNetId(new ProcessIdentifier(2));

		NetPlayer np2 = new NetPlayer();
		np2.setGlobalId(41);
		np2.setNetId(new ProcessIdentifier(2));
		
		PlayerModel pm = new PlayerModel(np1, Color.blue);
		NetPlayer np3 = new NetPlayer();
		np3.setGlobalId(42);
		np3.setNetId(new ProcessIdentifier(3));
		
		PlayerModel pm3 = new PlayerModel(np3, Color.blue);
		PlayerModel pm1 = new PlayerModel(np2, Color.blue);
		
		Interaction in = new Interaction();
		in.expectChouetteVeloute();
		in.addPlayer(pm);
		in.addPlayer(pm1);
		in.addPlayer(pm3);
		assertTrue(in.interacCount() == 2);
	}

	@Test
	public void testInteracCount() {
		fail("Not yet implemented");
	}

}
