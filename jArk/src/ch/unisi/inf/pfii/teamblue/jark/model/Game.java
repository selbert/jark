package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

import org.w3c.dom.Notation;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Console;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.*;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.GamePanel;
import ch.unisi.inf.pfii.teamblue.jark.view.GameFrame;
import ch.unisi.inf.pfii.teamblue.jark.view.MainPanel;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class Game implements Constants {

	private GamePanel fieldView;

	private ArrayList<Ball> balls;
	private ArrayList<Bonus> freeBonuses;

	private Vaus vaus;
	private Player player;
	private Level level;
	private Console console;
	private ExecutorService ex;

	private boolean running;
	private Random rnd;
	
	public Game(GamePanel fieldView) {
		rnd = new Random();
		this.fieldView = fieldView;

		console = new Console();
		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - 100 / 2, 20);
		player = new Player("pippo", 3);
		level = new Level(30, freeBonuses);
		for (int i = 0; i < 10; i++) {
			balls.add(new DefaultBall(vaus, level));
			balls.get(i).setSpeedX(-2);
			balls.get(i).setSpeedY(-2);
		}
		fieldView.setBricks(level.getBricks());
		fieldView.setBalls(balls);
		fieldView.setVaus(vaus);
		
		ex = Executors.newFixedThreadPool(1);
		running = false;

	}

	public void play() {
		if (!running) {
			running = true;
			fieldView.requestFocusInWindow();
			ex.execute(new GameStart());
		} else {
			running = false;
		}
	}

	class GameStart implements Runnable {
		public final void run() {
			while (running) {
				moveBalls();
				vaus.move();
				fieldView.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	};

	/**
	 * Move all the balls in the game
	 */
	private final void moveBalls() {
		for (int i = 0 ; i < balls.size();) {
			if (balls.get(i).isDead()) {
				balls.remove(balls.get(i));
			} else {
				balls.get(i).move();
				i++;
			}
		}
	}
	
	private final int getRandomSpeed() {
		final int rndSpeed = rnd.nextInt(5)+1;
		if (rnd.nextBoolean()) {
			return -1*rndSpeed;
		}
		return rndSpeed;
	}
	
	public final void addBall() {
		Ball newBall = new DefaultBall(vaus, level);
		newBall.setSpeedX(getRandomSpeed());
		newBall.setSpeedY(-3);
		balls.add(newBall);
	}
	
}
