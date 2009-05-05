package ch.unisi.inf.pfii.teamblue.jark.model;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Timer;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.UltraBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.GamePanel;
import ch.unisi.inf.pfii.teamblue.jark.view.InfoPanel;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Game implements Constants {

	private GamePanel gamePanel;
	private InfoPanel infoPanel;
	
	private ArrayList<Ball> balls;
	private ArrayList<Bonus> freeBonuses;

	private Vaus vaus;
	private Player player;
	private Level level;
	private ExecutorService ex;

	private boolean running;
	private Random rnd;
	
	final Timer tick;
	final ActionListener li;
	
	public Game(GamePanel gamePanel, InfoPanel infoPanel) {
		rnd = new Random();
		this.gamePanel = gamePanel;
		this.infoPanel = infoPanel;

		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - VAUS_WIDTH / 2, 20);
		player = new Player("pippo", 3);
		level = new Level(100, freeBonuses, vaus);
		
		for (int i = 0; i < 1; i++) {
			balls.add(new DefaultBall(vaus, level));
			balls.get(i).setSpeedX(getRandomSpeed());
			balls.get(i).setSpeedY(-3);
		}
		
		gamePanel.setBricks(level.getBricks());
		gamePanel.setBalls(balls);
		gamePanel.setBonuses(freeBonuses);
		gamePanel.setVaus(vaus);
		infoPanel.setLives(player.getLives());
		
		li = new TimerTickHandler(this, vaus, gamePanel);
        tick = new Timer(TICKS_PER_SECOND, li);

	}

	public void play() {
		if (!running) {
			running = true;
			gamePanel.requestFocusInWindow();
			tick.start();
		} else {
			running = false;
		}
	}


	/**
	 * Move all the balls in the game
	 */
	public final void moveBalls() {
		for (int i = 0 ; i < balls.size();) {
			if (balls.get(i).isDead()) {
				balls.remove(balls.get(i));
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
	
	public final void addBall() {
		Ball newBall = new DefaultBall(vaus, level);
		gamePanel.requestFocusInWindow();
		newBall.setSpeedX(getRandomSpeed());
		newBall.setSpeedY(-3);
		balls.add(newBall);
	}

	public Player getPlayer() {
		return player;
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}

	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
	
}
