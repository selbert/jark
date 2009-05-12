package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.BallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.VausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The game core class (inits and runs the model of the game)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class Game implements Constants {

	private final ArrayList<Ball> balls;
	private final ArrayList<Ball> bullets;
	private final ArrayList<Bonus> freeBonuses;
	private final ArrayList<Bonus> takenBonuses;
	
	private final ArrayList<VausSetListener> vausListeners;

	private final Random rnd;
	
	private Vaus vaus;
	private Player player;
	private Level level;
	
	public Game(final boolean isTest, final LevelManager levelManager) {

		//init
		rnd = new Random();
		vausListeners = new ArrayList<VausSetListener>();
		balls = new ArrayList<Ball>();
		bullets = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		takenBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - VAUS_WIDTH / 2);
		player = new Player("pippo", 3);
		
		if (!isTest) {
			setLevel(new Level(100, freeBonuses, vaus));
		} else {
			final Brick[][] field = levelManager.fieldFromArrays();
			setLevel(new Level(field, freeBonuses, vaus));
		}
		
		//starting balls
		for (int i = 0; i < 1; i++) {
			addRandomBall();
		}
		
	}
	
	//getters
	public final Brick[][] getBricks() {
		return level.getBricks();
	}
	public final ArrayList<Bonus> getBonuses() {
		return freeBonuses;
	}
	public final ArrayList<Ball> getBalls() {
		return balls;
	}
	public final int getPlayerLives() {
		return player.getLives();
	}
	public final Player getPlayer() {
		return player;
	}
	public final Vaus getVaus() {
		return vaus;
	}
	public Level getLevel() {
		return level;
	}
	public ArrayList<Ball> getBullets() {
		return bullets;
	}
	
	//setters
	private final void setLevel(final Level level) {
		this.level = level;
		level.addLevelListener(new LevelListener() {
			public void bonusReleased(Bonus bonus) {
				bonus.addBonusListener(new BonusListener() {
					public void bonusTaken(Bonus bonus) {
						addBonus(bonus, System.currentTimeMillis());
						bonus.removeBonusListener(this);
					}
				});
			}

			public void brickHit(Brick brick) {
				player.incrementScore(brick.getPoints());
			}
		});
		addVausListener(level);
	}
	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
		fireVausChanged();
	}
	
	
	/**
	 * Actions to do at each tick of the game
	 */
	public final void tick() {
		moveBalls();
		moveBonuses();
		moveBullets();
		checkTakenBonuses();
		vaus.move();
	}
	
	/**
	 * Add a ball to the game
	 * @param ball
	 */
	public final void addBall(final Ball ball) {
		balls.add(ball);
		addVausListener(ball);
	}
	public final void replaceBall(final Ball oldBall, final Ball newBall) {
		removeVausListener(oldBall);
		addVausListener(newBall);
		balls.set(balls.indexOf(oldBall),newBall);
	}
	private void removeBall(final Ball ball) {
		balls.remove(ball);
		removeVausListener(ball);
	}
	/**
	 * Add a bullet to the game
	 * @param bullet
	 */
	public final void addBullet(final Ball bullet) {
		bullets.add(bullet);
	}
	private void removeBullet(final Ball bullet) {
		bullets.remove(bullet);
		removeVausListener(bullet);
	}
	
	private void addBonus(Bonus bonus, long currentTimeMillis) {
		for (int i = 0 ; i < takenBonuses.size(); i++) {
			Bonus b = takenBonuses.get(i);
			if (((b instanceof BallBonus && bonus instanceof BallBonus)
					|| (b instanceof VausBonus && bonus instanceof VausBonus))
					&& (b.getBonusClass() == bonus.getBonusClass())) {
				freeBonuses.remove(bonus);
				bonus.apply(this);
				takenBonuses.set(i, bonus);
				return;
			}
		}
		bonus.apply(this);
		freeBonuses.remove(bonus);
		if (bonus.getLife() != 0) {
			takenBonuses.add(bonus);
		}
	}
	/**
	 * Move all the balls in the game
	 */
	public final void moveBalls() {
		for (int i = 0 ; i < balls.size();) {
			if (balls.get(i).isDead()) {
				this.removeBall(balls.get(i));
			} else {
				balls.get(i).move();
				i++;
			}
		}
	}
	
	/**
	 * Move all the bullets in the game
	 */
	public final void moveBullets() {
		for (int i = 0 ; i < bullets.size();) {
			if (bullets.get(i).isDead()) {
				this.removeBullet(bullets.get(i));
			} else {
				bullets.get(i).move();
				i++;
			}
		}
	}

	/**
	 * Move all the free bonuses in the game
	 */
	public final void moveBonuses() {
		for (int i = 0 ; i < freeBonuses.size();) {
			Bonus bonus = freeBonuses.get(i);
			if (bonus.isDead()) {
				freeBonuses.remove(i);
			} else {
				bonus.move();
				i++;
			}
		}
	}
	
	/**
	 * Check taken bonuses for expiration
	 */
	public final void checkTakenBonuses() {
		for (int i = 0 ; i < takenBonuses.size();) {
			Bonus bonus = takenBonuses.get(i);
			
			if (bonus.getLife() < Integer.MAX_VALUE) {
				bonus.decrementLife();
				if (bonus.getLife() < 0) {
					bonus.remove(this);
					takenBonuses.remove(i);
					continue;
				}
			}
			i++;
		}
	}
	/**
	 * remove all taken bonuses
	 */
	public final void removeTakenBonuses() {
		for (;0 < takenBonuses.size();) {
			takenBonuses.get(0).remove(this);
			takenBonuses.remove(0);
		}
	}
	
	/**
	 * Get a float number from -5 to 5
	 */
	private final float getRandomSpeed() {
		final float rndSpeed = rnd.nextFloat()*5;
		if (rnd.nextBoolean()) {
			return -1*rndSpeed;
		}
		return rndSpeed;
	}
	
	
	/**
	 * Add a ball at random x speed to the game
	 */
	public final void addRandomBall() {
		Ball newBall = new DefaultBall(vaus, level);
		newBall.setSpeedX(getRandomSpeed());
		newBall.setSpeedY(-3);
		addBall(newBall);
	}
	
	//vaus listeners
	public final void addVausListener(final VausSetListener li) {
		vausListeners.add(li);
	}
	public final void removeVausListener(final VausSetListener li) {
		vausListeners.remove(li);
	}
	private final void fireVausChanged() {
		for (VausSetListener li : vausListeners) {
			li.setVaus(vaus);
		}
	}
	
	public final void printBonuses() {
		for (int i = 0; i < takenBonuses.size() ; i++) {
			System.out.println(takenBonuses.get(i).toString());
		}
		System.out.println("---");
	}

	public void releaseBalls() {
		for (Ball b : balls) {
			b.release();
		}
	}
	
}

