package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
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
	private final ArrayList<Bonus> freeBonuses;
	
	private final ArrayList<VausListener> vausListeners;

	private final Random rnd;
	
	private Vaus vaus;
	private Player player;
	private Level level;
	
	public Game() {
		rnd = new Random();
		
		//init
		vausListeners = new ArrayList<VausListener>();
		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - VAUS_WIDTH / 2);
		player = new Player("pippo", 3);
		
		setLevel(new Level(10, freeBonuses, vaus));
		addVausListener(level);
		
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
	
	//setters
	private final void setLevel(final Level level) {
		this.level = level;
		addVausListener(level);
	}
	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
		fireVausChanged();
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
	 * Move all the free bonuses in the game
	 */
	public final void moveBonuses() {
		for (int i = 0 ; i < freeBonuses.size();) {
			if (freeBonuses.get(i).isDead()) {
				freeBonuses.remove(freeBonuses.get(i));
			} else if (freeBonuses.get(i).isTaken()) {
				freeBonuses.get(i).apply(this);
				freeBonuses.remove(freeBonuses.get(i));
			} else {
				freeBonuses.get(i).move();
				i++;
			}
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
	public final void addVausListener(final VausListener li) {
		vausListeners.add(li);
	}
	public final void removeVausListener(final VausListener li) {
		vausListeners.remove(li);
	}
	private final void fireVausChanged() {
		for (VausListener li : vausListeners) {
			li.setVaus(vaus);
		}
	}

	

	
}

