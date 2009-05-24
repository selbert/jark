package ch.unisi.inf.pfii.teamblue.jark.test;

import java.util.Arrays;

import junit.framework.TestCase;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.StringEncrypt;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.Player;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.ExplosionBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.GhostBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.RubberBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.StartBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.StickyBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.UltraBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.AddLifeBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonuses;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.CannonVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.DoubleBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.DoubleRifleVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.ExplosionBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.FalseBallsBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.FastBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.GhostBallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.LongVausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.RemoveLifeBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.ResetStatusBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.RifleVausBonus;
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
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.CannonVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DoubleRifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions.Bullet;

public class MainTest extends TestCase implements Constants {

	public void testUtils() {
		assertTrue((Utils.getPixelPosition(12, 10)[0] + " " + Utils
				.getPixelPosition(12, 10)[1]).equals("684 250"));
		assertTrue((Utils.getPixelPosition(-1, -1)[0] + " " + Utils
				.getPixelPosition(-1, 10)[1]).equals("-57 250"));
	}

	public void testGameAndPlayerCreation() {
		final LevelManager lm = new LevelManager();
		final Game g = new Game(false, lm);
		final Player p = new Player("pippo", 2);
		p.setLives(3);
		assertTrue(p.getLives() == 3);
		p.setScore(100);
		assertTrue(p.getScore() == 100);
		p.incrementScore(100);
		assertTrue(p.getScore() == 200);
		assertTrue(p.getName().equals("pippo"));
		p.incrementLives();
		assertTrue(p.getLives() == 4);
		assertTrue(true);
		p.decrementLives();
		assertTrue(p.getLives() == 3);
		final DefaultBall ball = new DefaultBall(g.getVaus(), g.getBalls().get(
				0).getLevel());
		g.addBall(ball);

		final Vaus vaus = new DefaultVaus(200);
		g.setVaus(vaus);

		assertSame(g.getVaus(), vaus);

		g.tick();

		final Ball oldBall = new DefaultBall(g.getVaus(), g.getLevel());
		final Ball newBall = new DefaultBall(g.getVaus(), g.getLevel());

		g.getBalls().clear();
		g.addBall(oldBall);
		g.replaceBall(oldBall, newBall);
		assertSame(g.getBalls().get(0), newBall);

		final Ball bullet = new Bullet(g.getVaus(), g.getLevel());

		g.getBullets().clear();
		g.addBullet(bullet);

		assertSame(g.getBullets().get(0), bullet);

	}

	public void testBallCreation() {
		final LevelManager lm = new LevelManager();
		final Game g = new Game(false, lm);
		final Level level = g.getLevel();
		final Vaus vaus = g.getVaus();
		Ball testBall = new DefaultBall(vaus, level);
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("defaultBall"));
		Ball ballCopy;
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);
		testBall = new ExplosionBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("explosionBall"));
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);
		testBall = new RubberBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("rubberBall"));
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);
		testBall = new StartBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("startBall"));
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);
		testBall = new StickyBall(vaus, level);
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("stickyBall"));
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);
		testBall = new UltraBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("ultraBall"));
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);
		testBall = new GhostBall(vaus, level);
		testBall.setX(50);
		testBall.setY(401);
		testBall.setSpeedY(-5);
		testBall.move();
		assertTrue(testBall instanceof Ball);
		assertTrue(testBall.toString().equals("ghostBall"));
		ballCopy = testBall.copy();
		assertNotSame(testBall, ballCopy);

	}

	public void testBonusCreation() {
		final LevelManager lm = new LevelManager();
		final Game g = new Game(false, lm);
		final Ball b = new DefaultBall(g.getVaus(), g.getLevel());
		g.addBall(b);
		Bonus bonus = new AddLifeBonus();
		assertTrue(bonus.toString().equals("bonus_addlife"));
		int i = g.getPlayerLives();
		bonus.apply(g);
		assertEquals(i, g.getPlayerLives() - 1);
		bonus = new DoubleBallBonus();
		assertTrue(bonus.toString().equals("bonus_doubleball"));
		bonus.apply(g);
		assertEquals(g.getBalls().size(), 4);
		bonus = new ExplosionBallBonus();
		assertTrue(bonus.toString().equals("bonus_explosionball"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0) instanceof ExplosionBall);
		bonus.remove(g);
		assertTrue(g.getBalls().get(0) instanceof DefaultBall);
		bonus = new FalseBallsBonus();
		assertTrue(bonus.toString().equals("malus_falseball"));
		bonus.apply(g);
		bonus.remove(g);
		assertEquals(g.getBalls().size(), 16);
		bonus = new FastBallBonus();
		assertTrue(bonus.toString().equals("neutral_fastball"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0).getSpeedMod() == 1.5f);
		bonus.remove(g);
		assertTrue(g.getBalls().get(0).getSpeedMod() == 1.0f);
		bonus = new RifleVausBonus();
		assertTrue(bonus.toString().equals("bonus_riflevaus"));
		i = bonus.getY();
		bonus.move();
		assertTrue(bonus.getY() == i + BONUS_SPEED);
		bonus.apply(g);
		assertTrue(g.getVaus() instanceof RifleVaus);
		bonus.remove(g);
		assertTrue(g.getVaus() instanceof DefaultVaus);
		bonus = new LongVausBonus();
		assertTrue(bonus.toString().equals("bonus_longvaus"));
		i = bonus.getLife();
		bonus.decrementLife();
		assertTrue(bonus.getLife() == i - TICKS_PER_SECOND);
		bonus.apply(g);
		assertTrue(g.getVaus().getWidth() == LONGVAUS_WIDTH);
		bonus.remove(g);
		assertTrue(g.getVaus().getWidth() == VAUS_WIDTH);
		bonus = new CannonVausBonus();
		assertTrue(bonus.toString().equals("bonus_cannonvaus"));
		bonus.apply(g);
		assertTrue(g.getVaus() instanceof CannonVaus);
		bonus.remove(g);
		assertTrue(g.getVaus() instanceof DefaultVaus);
		bonus = new ResetStatusBonus();
		assertTrue(bonus.toString().equals("neutral_resetstatus"));
		bonus.apply(g);
		bonus = new GhostBallBonus();
		assertTrue(bonus.toString().equals("malus_ghostball"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0) instanceof GhostBall);
		bonus.remove(g);
		assertTrue(g.getBalls().get(0) instanceof DefaultBall);
		bonus = new RemoveLifeBonus();
		assertTrue(bonus.toString().equals("malus_removelife"));
		i = g.getPlayerLives();
		bonus.apply(g);
		assertEquals(i, g.getPlayerLives() + 1);
		bonus = new ShortVausBonus();
		assertTrue(bonus.toString().equals("malus_shortvaus"));
		bonus.apply(g);
		assertTrue(g.getVaus().getWidth() == SHORTVAUS_WIDTH);
		bonus.remove(g);
		assertTrue(g.getVaus().getWidth() == VAUS_WIDTH);
		bonus = new SlowBallBonus();
		assertTrue(bonus.toString().equals("neutral_slowball"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0).getSpeedMod() == 0.5f);
		bonus.remove(g);
		assertTrue(g.getBalls().get(0).getSpeedMod() == 1.0f);
		bonus = new StickyBallBonus();
		assertTrue(bonus.toString().equals("neutral_stickyball"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0) instanceof StickyBall);
		bonus.remove(g);
		assertTrue(g.getBalls().get(0) instanceof DefaultBall);
		bonus = new TheBoxBonus();
		assertTrue(bonus.toString().equals("bonus_box"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0).getBoxEnabled());
		bonus.remove(g);
		assertFalse(g.getBalls().get(0).getBoxEnabled());
		assertTrue(g.getBalls().get(0).getSpeedMod() == 1.0f);
		bonus = new UltraBallBonus();
		assertTrue(bonus.toString().equals("bonus_ultraball"));
		bonus.apply(g);
		assertTrue(g.getBalls().get(0) instanceof UltraBall);
		bonus.remove(g);
		assertTrue(g.getBalls().get(0) instanceof DefaultBall);
		bonus = new DoubleRifleVausBonus();
		assertTrue(bonus.toString().equals("bonus_doubleriflevaus"));
		bonus.apply(g);
		assertTrue(g.getVaus() instanceof DoubleRifleVaus);
		bonus.remove(g);
		assertTrue(g.getVaus() instanceof DefaultVaus);

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
		final LevelManager lm = new LevelManager();
		final Game g = new Game(false, lm);
		Vaus vaus = new DefaultVaus(0);
		assertTrue(vaus.toString().equals("defaultVaus"));
		vaus.setX(5);
		assertTrue(vaus.getX() == 5);
		vaus = new RifleVaus(0);
		assertTrue(vaus.toString().equals("rifleVaus"));
		vaus.shoot(g);
		assertTrue(g.getBullets().size() == 1);
		vaus = new DoubleRifleVaus(0);
		assertTrue(vaus.toString().equals("doubleRifleVaus"));
		vaus.shoot(g);
		assertTrue(g.getBullets().size() == 2);
		vaus = new CannonVaus(0);
		assertTrue(vaus.toString().equals("cannonVaus"));
		vaus.shoot(g);
		assertTrue(g.getBullets().size() == 3);
		while (g.getBullets().size() > 0) {
			g.moveBullets();
		}
	}

	public void testBallMove() {
		Vaus vaus = new DefaultVaus(GAME_WIDTH - 20);
		final LevelManager lm = new LevelManager();
		final Game g = new Game(false, lm);
		final Level level = g.getLevel();
		DefaultBall ball = new DefaultBall(vaus, level);
		ball.setSpeedX(4);
		ball.move();
		ball.move();
		assertTrue(ball.toStringTest().equals("779.0 543.0 -3.8 -0.0"));
		ball.move();
		assertTrue(ball.toStringTest().equals("776.0 543.0 -3.8 -0.0"));
		vaus = new DefaultVaus(0);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedX(-4);
		ball.move();
		ball.move();
		assertTrue(ball.toStringTest().equals("36.0 543.0 -0.3 -0.0"));
		ball.move();
		assertTrue(ball.toStringTest().equals("33.0 543.0 -0.3 -0.0"));
		vaus = new DefaultVaus(0);
		ball = new DefaultBall(vaus, level);
		ball.setSpeedY(-5);
		for (int i = 0; i < 27; i++) {
			ball.move();
		}
		ball.move();

		assertTrue(ball.toStringTest().equals("42.0 460.0 0.0 -5.0"));
		ball.move();
		assertTrue(ball.toStringTest().equals("42.0 457.0 0.0 -5.0"));
		// the block in 10,395 disappears.
	}

	public void testGetBonusProbability() {
		assertEquals(5, Bonuses.getProb(0));
	}

	// get a new bonus of the selected type
	public void testGetBonus() {
		final Bonus b1 = Bonuses.getBonus(0);
		assertTrue(b1 instanceof StickyBallBonus);
	}

	// pixels to brick position
	public void testGetFieldPosition() {
		assertTrue(Arrays.equals(new int[] { 0, 0 }, Utils.getFieldPosition(0,
				0)));
	}

	public void testBrickHasBallInside() {
		final LevelManager lm = new LevelManager();
		final Game g = new Game(false, lm);
		final Level level = g.getLevel();
		assertFalse(level.brickHasBallInside(0f, 401f));
		assertFalse(level.brickHasBallInside(0f, 398f));
	}

	public void testEncryptDecrypt() {
		assertTrue(StringEncrypt.decrypt(
				StringEncrypt.encrypt("A$das dasd", 5), 5).equals("A$das dasd"));
	}
}
