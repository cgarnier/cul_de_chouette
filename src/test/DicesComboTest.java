package test;

import static org.junit.Assert.*;
import game.network.DicesCombo;

import org.junit.Test;

public class DicesComboTest {

	@Test
	public void testIsVeloute() {
		DicesCombo c = new DicesCombo();
		c.getD1().setFace(4);
		c.getD2().setFace(2);
		c.getD3().setFace(6);
		assertTrue("Est une velute", c.isVeloute());
		assertTrue("Score ok", c.getScore() == 6*6);
		
		c.getD1().setFace(4);
		c.getD2().setFace(3);
		c.getD3().setFace(6);
		assertFalse("N'Est pas une velute", c.isVeloute());
		System.out.println("sss " + c.getScore());
		assertTrue("Score ok", c.getScore() == 0);
		
		c.getD1().setFace(5);
		c.getD2().setFace(2);
		c.getD3().setFace(3);
		assertFalse("N'Est pas une velute", c.isVeloute());
		assertTrue("Score ok", c.getScore() == 0);

		
		c.getD1().setFace(1);
		c.getD2().setFace(5);
		c.getD3().setFace(4);
		assertFalse("N'Est pas une velute", c.isVeloute());
		assertTrue("Score ok", c.getScore() == 0);

		
			
	}

	@Test
	public void testIsChouette() {
		DicesCombo c = new DicesCombo();
		c.getD1().setFace(2);
		c.getD2().setFace(2);
		c.getD3().setFace(6);
		assertTrue("Est une chouette", c.isChouette());
		assertTrue("Score ok", c.getScore() == 2*2);

		c.getD1().setFace(6);
		c.getD2().setFace(6);
		c.getD3().setFace(1);
		assertTrue("Est une chouette", c.isChouette());
		assertTrue("Score ok", c.getScore() == 6*6);
		
		c.getD1().setFace(4);
		c.getD2().setFace(3);
		c.getD3().setFace(6);
		assertFalse("N'Est pas une chouette", c.isChouette());
		assertTrue("Score ok", c.getScore() == 0);
		
		c.getD1().setFace(5);
		c.getD2().setFace(3);
		c.getD3().setFace(3);
		assertFalse("N'Est pas une chouette", c.isChouette());
		assertTrue("Score ok", c.getScore() == 0);

		
		c.getD1().setFace(1);
		c.getD2().setFace(5);
		c.getD3().setFace(4);
		assertFalse("N'Est pas une chouette", c.isChouette());
		assertTrue("Score ok", c.getScore() == 0);
	}

	@Test
	public void testIsCulDeChouette() {
		DicesCombo c = new DicesCombo();
		c.getD1().setFace(2);
		c.getD2().setFace(2);
		c.getD3().setFace(2);
		assertTrue("Est une chouette", c.isCulDeChouette());
		
		assertTrue("Score ok", c.getScore() == 60);

		c.getD1().setFace(1);
		c.getD2().setFace(1);
		c.getD3().setFace(1);
		assertTrue("Est une CulDeChouette", c.isCulDeChouette());
		assertTrue("Score ok", c.getScore() == 50);
		
		c.getD1().setFace(6);
		c.getD2().setFace(6);
		c.getD3().setFace(6);
		assertTrue("Est pas une CulDeChouette", c.isCulDeChouette());
		assertTrue("Score ok", c.getScore() == 100);
		
		c.getD1().setFace(5);
		c.getD2().setFace(3);
		c.getD3().setFace(3);
		assertFalse("N'Est pas une CulDeChouette", c.isCulDeChouette());
		assertTrue("Score ok", c.getScore() == 0);

		
		c.getD1().setFace(1);
		c.getD2().setFace(5);
		c.getD3().setFace(4);
		assertFalse("N'Est pas une CulDeChouette", c.isCulDeChouette());
		assertTrue("Score ok", c.getScore() == 0);
	}

	@Test
	public void testIsSuite() {
		DicesCombo c = new DicesCombo();
		c.getD1().setFace(2);
		c.getD2().setFace(2);
		c.getD3().setFace(2);
		
		c.getD1().setFace(1);
		c.getD2().setFace(2);
		c.getD3().setFace(3);
		assertTrue("Est une Suite", c.isSuite());
		assertTrue("Score ok", c.getScore() == -10);
		
		c.getD1().setFace(5);
		c.getD2().setFace(3);
		c.getD3().setFace(4);
		assertTrue("Est une Suite", c.isSuite());
		assertTrue("Score ok", c.getScore() == -10);
		
		c.getD1().setFace(6);
		c.getD2().setFace(4);
		c.getD3().setFace(5);
		assertTrue("Est une Suite", c.isSuite());
		assertTrue("Score ok", c.getScore() == -10);

		c.getD1().setFace(1);
		c.getD2().setFace(3);
		c.getD3().setFace(4);
		assertFalse("Est une Suite", c.isSuite());
		assertTrue("Score ok", c.getScore() == 4*4);

		
	}

	@Test
	public void testIsChouetteVeloute() {
		DicesCombo c = new DicesCombo();
		c.getD1().setFace(2);
		c.getD2().setFace(2);
		c.getD3().setFace(4);
		assertTrue("Est une chouette", c.isChouetteVelute());
		assertTrue("Score ok", c.getScore() == 16);
		
		
		c.getD1().setFace(3);
		c.getD2().setFace(3);
		c.getD3().setFace(6);
		assertTrue("Est une chouette", c.isChouetteVelute());
		assertTrue("Score ok", c.getScore() == 36);

		
		c.getD1().setFace(6);
		c.getD2().setFace(3);
		c.getD3().setFace(3);
		assertTrue("N'Est une chouette", !c.isChouetteVelute());
		assertTrue("Score ok", c.getScore() == 0);


	}



}
