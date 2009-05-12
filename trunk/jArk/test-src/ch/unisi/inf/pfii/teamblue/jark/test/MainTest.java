package ch.unisi.inf.pfii.teamblue.jark.test;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.Player;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.ExplosionBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.RubberBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.StickyBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.UltraBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.AddLifeBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonuses;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.DoubleBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.DoubleLaserVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.ExplosionBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.FalseBallsBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.FastBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.GhostBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.LaserVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.LongVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.MissileVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.RemoveLifeBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.ResetStatusBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.ShortVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.SlowBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.StickyBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.TheBoxBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.UltraBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.DefaultBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.PersistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.ResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.VeryResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DoubleRifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RocketLauncherVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


public class MainTest extends TestCase implements Constants {
	
	public void testGameAndPlayerCreation() {
		LevelManager lm = new LevelManager();
		Game g = new Game(false, lm);
		Player p = new Player("pippo", 2);
		p.setLives(3);
		assertTrue(p.getLives() == 3);
		p.setScore(100);
		assertTrue(p.getScore() == 100);
		assertTrue(p.getName().equals("pippo"));
		p.incrementLives();
		assertTrue(p.getLives() == 4);
		assertTrue(true);
		DefaultBall ball = new DefaultBall(g.getVaus() , g.getBalls().get(0).getLevel() );
		g.addBall(ball);
		
	}
	
	public void testBallCreation() {
		ArrayList<Bonus> freeBonuses = new ArrayList<Bonus>();
		Vaus vaus = new DefaultVaus(GAME_WIDTH - 20);
		Level level = new Level(0, freeBonuses, vaus);
		Ball testBall = new DefaultBall(vaus, level);
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("defaultBall"));
		testBall = new ExplosionBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("explosionBall"));
		testBall = new RubberBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("rubberBall"));
		testBall = new StickyBall(vaus, level);
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("stickyBall"));
		testBall = new UltraBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("ultraBall"));
		
	}
	
	public void testBonusCreation() {
		Bonus bonus = new AddLifeBonus();
		assertTrue(bonus.toString().equals("bonus_addlife"));
		bonus = new DoubleBallBonus();
		assertTrue(bonus.toString().equals("bonus_doubleball"));
		bonus = new ExplosionBallBonus();
		assertTrue(bonus.toString().equals("bonus_explosionball"));
		bonus = new FalseBallsBonus();
		assertTrue(bonus.toString().equals("malus_falseball"));
		bonus = new FastBallBonus();
		assertTrue(bonus.toString().equals("neutral_fastball"));
		bonus = new LaserVausBonus();
		assertTrue(bonus.toString().equals("bonus_laservaus"));
		bonus = new LongVausBonus();
		assertTrue(bonus.toString().equals("bonus_longvaus"));
		bonus = new MissileVausBonus();
		assertTrue(bonus.toString().equals("bonus_missilevaus"));
		bonus = new ResetStatusBonus();
		assertTrue(bonus.toString().equals("neutral_resetstatus"));
		bonus = new GhostBallBonus();
		assertTrue(bonus.toString().equals("malus_ghostball"));
		bonus = new RemoveLifeBonus();
		assertTrue(bonus.toString().equals("malus_removelife"));
		bonus = new ShortVausBonus();
		assertTrue(bonus.toString().equals("malus_shortvaus"));
		bonus = new SlowBallBonus();
		assertTrue(bonus.toString().equals("neutral_slowball"));
		bonus = new StickyBallBonus();
		assertTrue(bonus.toString().equals("neutral_stickyball"));
		bonus = new TheBoxBonus();
		assertTrue(bonus.toString().equals("bonus_box"));
		bonus = new UltraBallBonus();
		assertTrue(bonus.toString().equals("bonus_ultraball"));
		bonus = new DoubleLaserVausBonus();
		assertTrue(bonus.toString().equals("bonus_doublelaservaus"));
		
	}
	
	public void testBrickCreation() {
		Brick brick = new DefaultBrick();
		assertTrue(brick.toString().equals("defaultBrick"));
		brick = new PersistentBrick();
		assertTrue(brick.toString().equals("persistentBrick"));
		brick = new ResistentBrick();
		assertTrue(brick.toString().equals("resistentBrick"));
		brick = new VeryResistentBrick();
		assertTrue(brick.toString().equals("veryResistentBrick"));
	}
	
	public void testVausCreation() {
		Vaus vaus = new DefaultVaus(0);
		assertTrue(vaus.toString().equals("defaultVaus"));
		vaus.setX(5);
		assertTrue(vaus.getX() == 5);
		vaus = new RifleVaus(0);
		assertTrue(vaus.toString().equals("rifleVaus"));
		vaus = new DoubleRifleVaus(0);
		assertTrue(vaus.toString().equals("doubleRifleVaus"));
		vaus = new RocketLauncherVaus(0);
		assertTrue(vaus.toString().equals("rocketlauncherVaus"));
	}

	public void testBallMove() {
		ArrayList<Bonus> freeBonuses = new ArrayList<Bonus>();
		Vaus vaus = new DefaultVaus(GAME_WIDTH - 20);
		Level level = new Level(0, freeBonuses, vaus);
		DefaultBall ball = new DefaultBall(vaus, level);
		ball.setSpeedX(4);
		ball.move();
		ball.move();
		assertTrue(ball.toStringTest().equals("778.2 543.0 -3.8 -0.0"));
		ball.move();
		assertTrue(ball.toStringTest().equals("774.4 543.0 -3.8 -0.0"));
		vaus = new DefaultVaus(0);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedX(-4);
		ball.move();
		ball.move();
		assertTrue(ball.toStringTest().equals("37.6 543.0 -0.4 -0.0"));
		ball.move();
		assertTrue(ball.toStringTest().equals("37.199997 543.0 -0.4 -0.0"));
		vaus = new DefaultVaus(0);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedY(-5);
		for(int i = 0; i < 27; i++) {
			ball.move();
		}
		ball.move();
		assertTrue(ball.toStringTest().equals("42.0 404.0 0.0 -5.0"));
		ball.move();
		assertTrue(ball.toStringTest().equals("42.0 404.0 0.0 5.0"));
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
		ArrayList<Bonus> freeBonuses = new ArrayList<Bonus>();
		Vaus vaus = new DefaultVaus(GAME_WIDTH - 20);
		Level level = new Level(0, freeBonuses, vaus);
		assertFalse(level.brickHasBallInside(0f, 401f));
		assertTrue(level.brickHasBallInside(0f, 398f));
	}
}
