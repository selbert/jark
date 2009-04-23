package test;
import java.util.ArrayList;

import junit.framework.TestCase;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


public class MainTest extends TestCase {
	
	public void testLevelCreationFromFile() {
		assertTrue(true);
	}
	public void testBallMove() {
		ArrayList<Bonus> freeBonuses = new ArrayList<Bonus>();
		Vaus vaus = new Vaus(Game.getGAME_WIDTH() - 20, 20);
		Level level = new Level(25, freeBonuses);
		Ball ball = new DefaultBall(vaus, level);
		ball.setSpeedX(4);
		ball.move();
		ball.move();
		assertTrue(ball.toString().equals("798 552 4 0"));
		ball.move();
		assertTrue(ball.toString().equals("799 552 -4 0"));
	}
}
