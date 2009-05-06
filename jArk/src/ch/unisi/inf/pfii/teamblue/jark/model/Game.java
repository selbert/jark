package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
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
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Game implements Constants {

	private ArrayList<Ball> balls;
	private ArrayList<Bonus> freeBonuses;
	
	private ArrayList<VausListener> vausListeners;

	private Vaus vaus;
	private Player player;
	private Level level;

	private Random rnd;
	
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
			addBall(new DefaultBall(vaus, level));
			balls.get(i).setSpeedX(getRandomSpeed());
			balls.get(i).setSpeedY(-3);
		}
		
	}
	
	//getters
	public Brick[][] getBricks() {
		return level.getBricks();
	}
	public ArrayList<Bonus> getBonuses() {
		return freeBonuses;
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	public int getPlayerLives() {
		return player.getLives();
	}
	public Player getPlayer() {
		return player;
	}
	public Vaus getVaus() {
		return vaus;
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
	
	private final float getRandomSpeed() {
		final float rndSpeed = rnd.nextFloat()*5;
		if (rnd.nextBoolean()) {
			return -1*rndSpeed;
		}
		return rndSpeed;
	}
	
	public final void addBall(Ball ball) {
		balls.add(ball);
		addVausListener(ball);
	}
	
	public final void addRandomBall() {
		Ball newBall = new DefaultBall(vaus, level);
		newBall.setSpeedX(getRandomSpeed());
		newBall.setSpeedY(-3);
		addBall(newBall);
	}
	
	private void removeBall(Ball ball) {
		balls.remove(ball);
		removeVausListener(ball);
	}
	
	private final void setLevel(final Level level) {
		this.level = level;
		addVausListener(level);
	}
	
	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
		fireVausChanged();
	}
	
	public final void addVausListener(VausListener l) {
		vausListeners.add(l);
	}
	
	public final void removeVausListener(VausListener l) {
		vausListeners.remove(l);
		
	}
	
	private final void fireVausChanged() {
		for (VausListener l : vausListeners) {
			l.setVaus(vaus);
		}
	}

	public final void replaceBall(Ball oldBall, Ball newBall) {
		removeVausListener(oldBall);
		addVausListener(newBall);
		balls.set(balls.indexOf(oldBall),newBall);
	}
	
}

