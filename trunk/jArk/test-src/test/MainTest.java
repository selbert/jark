package test;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonuses;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.StickyBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


public class MainTest extends TestCase implements Constants {
	
	/*
	 * This test is just for showing that we are sure the method to create a 
	 * level from a file works(visual/running test)
	 */
	public void testLevelCreationFromFile() {
		assertTrue(true);
	}
	public void testBallMove() {
		ArrayList<Bonus> freeBonuses = new ArrayList<Bonus>();
		Vaus vaus = new DefaultVaus(GAME_WIDTH - 20);
		Level level = new Level(0, freeBonuses, vaus);
		DefaultBall ball = new DefaultBall(vaus, level);
		ball.setSpeedX(4);
		ball.move();
		ball.move();
		assertTrue(ball.toString().equals("778.2 543.0 -3.8 -0.0"));
		ball.move();
		assertTrue(ball.toString().equals("774.4 543.0 -3.8 -0.0"));
		vaus = new DefaultVaus(0);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedX(-4);
		ball.move();
		ball.move();
		assertTrue(ball.toString().equals("37.6 543.0 -0.4 -0.0"));
		ball.move();
		assertTrue(ball.toString().equals("37.199997 543.0 -0.4 -0.0"));
		vaus = new DefaultVaus(0);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedY(-5);
		for(int i = 0; i < 27; i++) {
			ball.move();
		}
		ball.move();
		assertTrue(ball.toString().equals("42.0 404.0 0.0 -5.0"));
		ball.move();
		assertTrue(ball.toString().equals("42.0 404.0 0.0 5.0"));
		//the block in 10,395 disappears.
	}
	
	public void testGetBonusProbability() {
		assertEquals(5,Bonuses.getProb(0));
	}
	
	//get a new bonus of the selected type
	public void testGetBonus() {
		Bonus b1 = Bonuses.getBonus(0);
		assertTrue(b1 instanceof StickyBallBonus);
	}
	
	//pixels to brick position
	public void testGetFieldPosition() {
		assertTrue(Arrays.equals(new int[] {0,0},Utils.getFieldPosition(0, 0)));
	}
	
	public void testBrickHasBallInside() {
		//TODO
	}
}
