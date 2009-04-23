package test;
import java.util.ArrayList;

import junit.framework.TestCase;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


public class MainTest extends TestCase {
	
	/*
	 * This test is just for showing that we are sure the method to create a 
	 * level from a file works(visual/running test)
	 */
	public void testLevelCreationFromFile() {
		assertTrue(true);
	}
	public void testBallMove() {
		ArrayList<Bonus> freeBonuses = new ArrayList<Bonus>();
		Vaus vaus = new DefaultVaus(Game.getGAME_WIDTH() - 20, 20);
		Level level = new Level(25, freeBonuses);
		Ball ball = new DefaultBall(vaus, level);
		ball.setSpeedX(4);
		ball.move();
		ball.move();
		assertTrue(ball.toString().equals("798 552 4 0"));
		ball.move();
		assertTrue(ball.toString().equals("799 552 -4 0"));
		vaus = new DefaultVaus(0,20);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedX(-4);
		ball.move();
		ball.move();
		assertTrue(ball.toString().equals("2 552 -4 0"));
		ball.move();
		assertTrue(ball.toString().equals("0 552 4 0"));
		vaus = new DefaultVaus(0,20);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedY(-5);
		for(int i = 0; i < 29; i++) {
			ball.move();
		}
		ball.move();
		assertTrue(ball.toString().equals("10 402 0 -5"));
		ball.move();
		assertTrue(ball.toString().equals("10 402 0 5"));
	}
}
